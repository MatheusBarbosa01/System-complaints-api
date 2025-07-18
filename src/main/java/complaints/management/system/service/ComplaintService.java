package complaints.management.system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import complaints.management.system.dto.complaint.ComplaintCreateDto;
import complaints.management.system.dto.complaint.ComplaintDetailDto;
import complaints.management.system.dto.complaint.ComplaintDto;
import complaints.management.system.dto.complaint.ComplaintListDeletedDto;
import complaints.management.system.dto.complaint.ComplaintListDto;
import complaints.management.system.dto.complaint.ComplaintUpdateDto;
import complaints.management.system.model.Complaint;
import complaints.management.system.model.User;
import complaints.management.system.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintDto createComplaint(ComplaintCreateDto data, User user){
        
        Complaint complaint = Complaint.builder()
            .title(data.title())
            .description(data.description())
            .priority(data.priority())
            .cpf(user.getCpf())
            .createdAt(LocalDateTime.now())
            .user(user)
            .build();
        
        complaintRepository.save(complaint);
        return new ComplaintDto(complaint);
    }

    public Page<ComplaintListDto> listUserComplaints(User user, Pageable pageable) {
        return complaintRepository.findByUserAndDeletedAtIsNull(user, pageable)
            .map(c -> new ComplaintListDto(
                c.getId(),
                c.getTitle(),
                resumo(c.getDescription(), 30),
                c.getStatus(),
                c.getPriority(),
                c.getCreatedAt()
            ));
    }

    public Page<ComplaintListDeletedDto> listUserComplaintsDeleted(User user, Pageable pageable) {
        return complaintRepository.findByUserAndDeletedAtIsNotNull(user, pageable)
            .map(c -> new ComplaintListDeletedDto(
                c.getId(),
                c.getTitle(),
                resumo(c.getDescription(), 30),
                c.getStatus(),
                c.getPriority(),
                c.getCreatedAt(),
                c.getDeletedAt()
            ));
    }

    public Page<ComplaintListDto> listUserComplaintsFilter(User user, ComplaintDto data, Pageable pageable) {
        return complaintRepository.findByUserAndPriorityAndDeletedAtIsNull(user, data.priority(), pageable)
            .map(c -> new ComplaintListDto(
                c.getId(),
                c.getTitle(),
                resumo(c.getDescription(), 30),
                c.getStatus(),
                c.getPriority(),
                c.getCreatedAt()
            ));
    }

    public ComplaintDetailDto getComplaintDetail(Long id, User user) {
        Complaint complaint = complaintRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reclamação não encontrada"));

        if (!complaint.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Acesso negado");
        }
        
        return new ComplaintDetailDto(
            complaint.getTitle(),
            complaint.getDescription(),
            complaint.getCreatedAt(),
            complaint.getUpdatedAt(),
            complaint.getStatus(),
            complaint.getPriority(),
            complaint.getUser().getCpf(),
            complaint.getUser().getEmail()
        );
    }

    public ComplaintDto updateComplaint(Long id, User user, ComplaintUpdateDto data){
        Complaint complaint = complaintRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reclamação não encontrada"));

        if (!complaint.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Acesso negado");
        }

        complaint.setDescription(data.description());
        complaint.setStatus(data.status());
        complaint.setUpdatedAt(LocalDateTime.now());

        complaintRepository.save(complaint);

        return new ComplaintDto(complaint);
    }
    
    public void deleteComplaint(Long id, User user) {
        var complaint = complaintRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reclamação não encontrada"));
    
        if (!complaint.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Acesso negado");
        }
    
        complaint.softDelete();
        complaintRepository.save(complaint);
    }

    private String resumo(String text, int limit) {
        return text.length() > limit ? text.substring(0, limit) + "..." : text;
    }
    

}
