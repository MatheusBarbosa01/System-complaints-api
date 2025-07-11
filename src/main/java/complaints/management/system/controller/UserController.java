package complaints.management.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import complaints.management.system.dto.user.UserDto;
import complaints.management.system.dto.user.UserRegisterDto;
import complaints.management.system.model.User;
import complaints.management.system.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("register")
    @Transactional
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegisterDto data, UriComponentsBuilder uriBuilder){
        var user = userService.registerUser(data);
        var uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.id()).toUri();
        var userDetail = new UserDto(user.id(), user.name(), user.cpf(), user.email());
        return ResponseEntity.created(uri).body(userDetail);
        
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
    
        var dto = new UserDto(user.getId(), user.getName(), user.getCpf(), user.getEmail());
        return ResponseEntity.ok(dto);
    }
}
