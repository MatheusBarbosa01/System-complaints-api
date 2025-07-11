package complaints.management.system.dto.complaint;

import complaints.management.system.model.ComplaintStatus;

public record ComplaintUpdateDto(
    String description,
    ComplaintStatus status
) {}
