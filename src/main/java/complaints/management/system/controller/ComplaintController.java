package complaints.management.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import complaints.management.system.dto.complaint.ComplaintCreateDto;
import complaints.management.system.dto.complaint.ComplaintDetailDto;
import complaints.management.system.dto.complaint.ComplaintDto;
import complaints.management.system.dto.complaint.ComplaintListDeletedDto;
import complaints.management.system.dto.complaint.ComplaintListDto;
import complaints.management.system.dto.complaint.ComplaintUpdateDto;
import complaints.management.system.model.User;
import complaints.management.system.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ComplaintDto> createComplaint (@RequestBody @Valid ComplaintCreateDto data, @AuthenticationPrincipal User user ){
        var complaintCreated = complaintService.createComplaint(data, user);
        return ResponseEntity.ok().body(complaintCreated);
    }

    @GetMapping
    public ResponseEntity<List<ComplaintListDto>> listComplaint (@AuthenticationPrincipal User user){
        var complaintList = complaintService.listUserComplaints(user);
        return ResponseEntity.ok(complaintList);
    }
    @GetMapping("/deleted")
    public ResponseEntity<List<ComplaintListDeletedDto>> listComplaintDeleted (@AuthenticationPrincipal User user){
        var complaintList = complaintService.listUserComplaintsDeleted(user);
        return ResponseEntity.ok(complaintList);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ComplaintListDto>> listComplaintByFilter (@AuthenticationPrincipal User user, @Valid @RequestBody ComplaintDto data){
        var complaintList = complaintService.listUserComplaintsFilter(user, data);
        return ResponseEntity.ok(complaintList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDetailDto> detailComplaint(@PathVariable Long id, @AuthenticationPrincipal User user){
        var complaintDetails = complaintService.getComplaintDetail(id, user);
        return ResponseEntity.ok(complaintDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintDto> updateComplaint(@PathVariable Long id, @RequestBody @Valid ComplaintUpdateDto data, @AuthenticationPrincipal User user) {
        ComplaintDto complaintUpdated = complaintService.updateComplaint(id, user, data);
        return ResponseEntity.ok(complaintUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id,@AuthenticationPrincipal User user) {
        complaintService.deleteComplaint(id, user);
        return ResponseEntity.noContent().build();
    }

}
