package complaints.management.system.dto.complaint;

import java.time.LocalDateTime;

import complaints.management.system.model.Complaint;
import complaints.management.system.model.ComplaintPriority;
import complaints.management.system.model.ComplaintStatus;

public record ComplaintDto(Long id, String title, String description, ComplaintStatus status, ComplaintPriority priority,LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
    public ComplaintDto(Complaint complaint){
        this(complaint.getId(), complaint.getTitle(), complaint.getDescription(), complaint.getStatus(),complaint.getPriority(),complaint.getCreatedAt(), complaint.getUpdatedAt(), complaint.getDeletedAt());
    }
}
