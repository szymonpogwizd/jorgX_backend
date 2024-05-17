package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.user.*;
import pl.jorgX.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/dashboard/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @PostMapping
    public UserInfoDTO createUser(@RequestBody @Valid UserCreateDTO user) {
        log.debug("Create user {}", user);
        UserDAO toCreate = userMapper.userCreateDTO2UserDAO(user);
        UserDAO createdUser = userService.create(toCreate);
        return log.traceExit(userMapper.userDAO2UserInfoDTO(createdUser));
    }

    @PutMapping("{id}")
    public UserInfoDTO updateUser(@RequestBody @Valid UserUpdateDTO user, @PathVariable UUID id) {
        log.debug("Update user {}: {}", id, user);
        UserDAO updatedUser = userService.update(id, userMapper.userUpdateDTO2UserDAO(user));
        return log.traceExit(userMapper.userDAO2UserInfoDTO(updatedUser));
    }

    @GetMapping
    public List<UserInfoDTO> getAll() {
        log.debug("Getting all users");
        return log.traceExit(
                userService.getAll()
                        .stream()
                        .map(userMapper::userDAO2UserInfoDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/userTypes")
    public List<UserType> getUserType() {
        log.debug("Getting all user types");
        return log.traceExit(
                new ArrayList<>(userService.getAllUserTypes())
        );
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable UUID id) {
        log.debug("Deleting user {}", id);
        userService.delete(id);
    }

    @GetMapping("/currentUser")
    public UserDAO getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
