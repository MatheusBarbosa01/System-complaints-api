package complaints.management.system.dto.complaint;

import java.time.LocalDateTime;

import complaints.management.system.model.ComplaintPriority;
import complaints.management.system.model.ComplaintStatus;

public record ComplaintListDeletedDto(
    Long id,
    String title,
    String resumedDescription,
    ComplaintStatus status,
    ComplaintPriority priority,
    LocalDateTime createdAt,
    LocalDateTime deletedAt
) {
    
}
