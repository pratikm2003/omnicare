package support.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.project.entity.ActivityLog;
import support.project.entity.Dtos.ActivityLogDTO;
import support.project.repository.ActivityLogRepository;
import support.project.service.ActivityLogService;

import java.time.LocalDateTime;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository repo;

    @Override
    public void saveLog(ActivityLogDTO logDTO) {

        ActivityLog log = new ActivityLog();
        log.setUsername(logDTO.getUsername());
        log.setAction(logDTO.getAction());
        log.setDetails(logDTO.getDetails());
        log.setPage(logDTO.getPage());
        log.setIpAddress(logDTO.getIp());
        log.setTimestamp(LocalDateTime.now());

        repo.save(log);
    }
}
