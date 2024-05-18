package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.user.*;
import pl.jorgX.services.UserService;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@CrossOrigin
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @PostMapping
    public UserInfoDTO registerUser(@RequestBody @Valid UserCreateDTO user) {
        log.debug("Register user {}", user);
        UserDAO toCreate = userMapper.userCreateDTO2UserDAO(user);
        UserDAO createdUser = userService.create(toCreate);
        return log.traceExit(userMapper.userDAO2UserInfoDTO(createdUser));
    }
}
