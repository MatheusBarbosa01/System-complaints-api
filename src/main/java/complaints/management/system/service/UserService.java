package complaints.management.system.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import complaints.management.system.dto.user.UserDto;
import complaints.management.system.dto.user.UserRegisterDto;
import complaints.management.system.model.User;
import complaints.management.system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto registerUser(UserRegisterDto data){
        if (userRepository.findByEmail(data.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        if (userRepository.findByCpf(data.cpf()).isPresent()) {
            throw new IllegalArgumentException("Cpf já cadastrado!");
        }
        String password = passwordEncoder.encode(data.password());
        User user = User.builder()
        .name(data.name())
        .cpf(data.cpf())
        .email(data.email())
        .password(password)
        .build();
        userRepository.save(user);
        return new UserDto(user);
    }

}
