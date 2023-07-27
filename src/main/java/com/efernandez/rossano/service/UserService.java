package com.efernandez.rossano.service;

import com.efernandez.rossano.dao.UserInfo;
import com.efernandez.rossano.dto.UserDTO;
import com.efernandez.rossano.jdbc.UserJdbc;
import com.efernandez.rossano.repository.UserRepository;
import com.efernandez.rossano.utils.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserJdbc userJdbc;
    private final PasswordGenerator passwordGenerator;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository,
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

    public Page<UserDTO> searchUsers(int start, int length,
                                     String nameFilter, String emailFilter, String rolFilter, String documentoFilter) {
        Page<UserDTO> users = userJdbc.searchUsers(start, length, nameFilter, emailFilter, rolFilter, documentoFilter);
        return users;
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
}
