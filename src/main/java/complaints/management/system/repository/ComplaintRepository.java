package complaints.management.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import complaints.management.system.model.Complaint;
import complaints.management.system.model.ComplaintPriority;
import complaints.management.system.model.User;

public interface ComplaintRepository extends JpaRepository<Complaint,Long>{
    Page<Complaint> findByUserAndDeletedAtIsNull(User user, Pageable pageable);
    Page<Complaint> findByUserAndDeletedAtIsNotNull(User user, Pageable pageable);
    Page<Complaint> findByUserAndPriorityAndDeletedAtIsNull(User user, ComplaintPriority priority, Pageable pageable);

}
