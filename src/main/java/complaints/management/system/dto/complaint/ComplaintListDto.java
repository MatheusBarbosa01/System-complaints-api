package complaints.management.system.dto.complaint;

import java.time.LocalDateTime;

import complaints.management.system.model.ComplaintStatus;

public record ComplaintListDto(    
    Long id,
    String title,
    String resumedDescription,
    ComplaintStatus status,
    LocalDateTime createdAt) {
    
}
