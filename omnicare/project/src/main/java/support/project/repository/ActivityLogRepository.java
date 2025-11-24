package support.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import support.project.entity.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
