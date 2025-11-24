package support.project.controlleer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import support.project.entity.Ticket;
import support.project.repository.TicketRepository;
import support.project.service.DashboardService;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;

    @GetMapping("/year/{year}")
    public Map<String, Object> getYearData(@PathVariable int year) {
        return dashboardService.getYearData(year);
    }
}
