package support.project.controlleer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import support.project.entity.Dtos.ActivityLogDTO;
import support.project.service.ActivityLogService;

@RestController
@RequestMapping("/api/logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService logService;

    @PostMapping("/save")
    public String saveActivity(@RequestBody ActivityLogDTO dto) {
        logService.saveLog(dto);
        return "Activity Logged Successfully";
    }
}
