package complaints.management.system.dto.complaint;

import java.time.LocalDateTime;

import complaints.management.system.model.ComplaintPriority;
import complaints.management.system.model.ComplaintStatus;

public record ComplaintDetailDto(    
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    ComplaintStatus status,
    ComplaintPriority priority,
    String cpf,
    String email) {
    
}
