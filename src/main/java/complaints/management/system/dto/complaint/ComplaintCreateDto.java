package complaints.management.system.dto.complaint;

import complaints.management.system.model.ComplaintPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComplaintCreateDto(
    @NotBlank String title,
    @NotBlank String description,
    @NotNull ComplaintPriority priority
    ) 
    {
    
}
