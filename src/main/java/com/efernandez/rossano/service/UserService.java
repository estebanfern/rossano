package com.efernandez.rossano.service;

import com.efernandez.rossano.dao.UserInfo;
import com.efernandez.rossano.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserInfo findUserByUsername(String username);
    Page<UserDTO> searchUsers(int start, int length, String nameFilter, String emailFilter, String rolFilter, String documentoFilter);
    void saveNewUser(UserDTO userdto);
    void updatePassword(String authname, String originalPassword, String newPassword);
}
