package com.efernandez.rossano.controller.view;

import com.efernandez.rossano.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String profilePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userName", userService.findUserByUsername(auth.getName()).getName());
        return "profile/profile";
    }

    @PostMapping("/update-password")
    public ResponseEntity<Object> updatePassword(String originalPassword, String newPassword) {
        String authname = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(String.format("Updating password for user with email: %s", authname));
        userService.updatePassword(authname, originalPassword, newPassword);
        return ResponseEntity.ok().build();
    }

}
