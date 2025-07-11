package complaints.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import complaints.management.system.model.Complaint;
import complaints.management.system.model.ComplaintPriority;
import complaints.management.system.model.User;

public interface ComplaintRepository extends JpaRepository<Complaint,Long>{
    List<Complaint> findByUserAndDeletedAtIsNull(User user);
    List<Complaint> findByUserAndPriority(User user, ComplaintPriority complaintPriority);
}
