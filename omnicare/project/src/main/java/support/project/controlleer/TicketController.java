package support.project.controlleer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import support.project.entity.Ticket;
import support.project.entity.Dtos.ActivityLogDTO;
import support.project.service.TicketService;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import support.project.service.ActivityLogService;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ActivityLogService logService;

    @PostMapping("/submit")
    public Ticket submitTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/solution")
    public List<Ticket> getSolution(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String bankName,
            @RequestParam(required = false) String cms,
            @RequestParam(required = false) String problem,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String program,
            @RequestParam(required = false) String username,  // ⭐ Added
            HttpServletRequest request
    ) {

        LocalDate from = parseOrNull(fromDate);
        LocalDate to = parseOrNull(toDate);

        // ⭐ Username from frontend (Not Session)
        String currentUser = (username != null && !username.trim().isEmpty())
                ? username
                : "Unknown";

        // ⭐ Save log entry
        ActivityLogDTO dto = new ActivityLogDTO();
        dto.setUsername(currentUser);
        dto.setAction("SEARCH");
        dto.setDetails("Filters: " +
                fromDate + ", " + toDate + ", " +
                bankName + ", " + cms + ", " +
                problem + ", " + product + ", " + program);
        dto.setPage("/api/tickets/solution");
        dto.setIp(request.getRemoteAddr());
        logService.saveLog(dto);

        boolean noFilters = (from == null && to == null &&
                isEmpty(bankName) && isEmpty(cms) &&
                isEmpty(problem) && isEmpty(product) && isEmpty(program));

        if (noFilters) {
            return List.of();  // no get-all
        }

        return ticketService.searchByFilters(from, to, bankName, cms, problem, product, program);
    }

    private LocalDate parseOrNull(String dateStr) {
        try {
            return (dateStr == null || dateStr.isEmpty()) ? null : LocalDate.parse(dateStr.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isEmpty(String str) {
        return (str == null || str.trim().isEmpty());
    }
}
