package complaints.management.system.dto.complaint;

import jakarta.validation.constraints.NotBlank;

public record ComplaintCreateDto(
    @NotBlank String title,
    @NotBlank String description
    ) 
    {
    
}
