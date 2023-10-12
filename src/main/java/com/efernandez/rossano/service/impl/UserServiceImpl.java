package com.efernandez.rossano.service.impl;

import com.efernandez.rossano.dao.UserInfo;
import com.efernandez.rossano.dto.UserDTO;
import com.efernandez.rossano.jdbc.UserJdbc;
import com.efernandez.rossano.repository.UserRepository;
import com.efernandez.rossano.service.MailService;
import com.efernandez.rossano.service.UserService;
import com.efernandez.rossano.utils.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserJdbc userJdbc;
    private final PasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository,
                           UserJdbc userJdbc,
                           PasswordGenerator passwordGenerator,
                           PasswordEncoder passwordEncoder,
                           MailService mailService){
        this.userRepository = userRepository;
        this.userJdbc = userJdbc;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Username with email %s not found", username))
                );
    }

    public UserInfo findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Username with email %s not found", username))
                );
    }

    public Page<UserDTO> searchUsers(int start, int length,
                                     String nameFilter, String emailFilter, String rolFilter, String documentoFilter) {
        return userJdbc.searchUsers(start, length, nameFilter, emailFilter, rolFilter, documentoFilter);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveNewUser(UserDTO userdto) {
        UserInfo userInfo = new UserInfo();
        if (userdto.getName() != null && !userdto.getName().isEmpty()) {
            userInfo.setName(userdto.getName());
        }
        if (userdto.getDocumento() != null && !userdto.getDocumento().isEmpty()) {
            userInfo.setDocumento(userdto.getDocumento());
        }
        if (userdto.getEmail() != null && !userdto.getEmail().isEmpty()) {
            userInfo.setEmail(userdto.getEmail());
        }
        userInfo.setRol(userdto.getRol());
        String password = passwordGenerator.generate();
        userInfo.setPassword(passwordEncoder.encode(password));
        userInfo.setEnabled(Boolean.TRUE);
        userRepository.save(userInfo);
        mailService.sendNewUserEmail(userdto.getEmail(), userdto.getName(), userdto.getEmail(), password);
        logger.info(String.format("Created User with email %s and generated password %s", userdto.getEmail(), password));
    }

    public void updatePassword(String authname, String originalPassword, String newPassword) throws IllegalArgumentException{
        UserInfo user = userRepository.findByEmail(authname).orElseThrow(() -> new IllegalArgumentException("Usuario inexistente."));
        if (!passwordEncoder.matches(originalPassword, user.getPassword())) {
            throw new IllegalArgumentException("Contraseña actual inválida.");
        }
        if (newPassword.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 6 carácteres.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        logger.info(String.format("Updated password for user with email %s", authname));
    }

}
