package com.efernandez.rossano.controller.view;

import com.efernandez.rossano.dto.DataTableResponse;
import com.efernandez.rossano.dto.UserDTO;
import com.efernandez.rossano.service.RolService;
import com.efernandez.rossano.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private RolService rolService;


    public UserController(UserService userService, RolService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }

    @GetMapping()
    public String usersPage(Model model) {
        model.addAttribute("roles", rolService.findAll());
        return "users/users";
    }

    @GetMapping("/search")
    @ResponseBody
    public DataTableResponse<UserDTO> searchUsers(@RequestParam("draw") int draw,
                                                  @RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam(name = "nameFilter", required = false) String nameFilter,
                                                  @RequestParam(name = "emailFilter", required = false) String emailFilter,
                                                  @RequestParam(name = "rolFilter", required = false) String rolFilter,
                                                  @RequestParam(name = "documentoFilter", required = false) String documentoFilter
                                                    ) {
        return new DataTableResponse<>(draw, userService.searchUsers(start, length, nameFilter, emailFilter, rolFilter, documentoFilter));
    }

    @PostMapping("/save")
    public String saveNewUser(UserDTO userInfo) {
        userService.saveNewUser(userInfo);
        return "redirect:/users?saved";
    }

}
