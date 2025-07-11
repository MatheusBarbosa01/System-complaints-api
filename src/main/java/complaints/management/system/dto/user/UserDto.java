package complaints.management.system.dto.user;

import complaints.management.system.model.User;

public record UserDto(Long id, String name, String cpf, String email) {
    public UserDto(User user){
        this(user.getId(),user.getName(), user.getCpf(), user.getEmail());
    }
}
