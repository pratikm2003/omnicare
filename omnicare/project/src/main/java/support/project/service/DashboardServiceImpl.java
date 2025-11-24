package support.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.project.entity.Ticket;
import support.project.repository.TicketRepository;
import support.project.service.DashboardService;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final TicketRepository repo;

    @Override
    public Map<String, Object> getYearData(int year) {

        List<Ticket> tickets = repo.findAll();

        String[] monthNames = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

        Map<String, Map<String, Integer>> months = new LinkedHashMap<>();

        // Initialize stats for each month
        for (String m : monthNames) {
            Map<String, Integer> stats = new HashMap<>();
            stats.put("total", 0);
            stats.put("resolved", 0);
            stats.put("pending", 0);
            stats.put("temporary", 0);
            months.put(m, stats);
        }

        // Process each ticket
        for (Ticket t : tickets) {
            LocalDate date = t.getDate();
            if (date == null || date.getYear() != year) {
                continue;
            }

            String monthName = monthNames[date.getMonthValue() - 1];
            Map<String, Integer> stats = months.get(monthName);

            // Always increment total
            stats.put("total", stats.get("total") + 1);

            // Normalize values: lowercase + trim + null-safe
            String status = Optional.ofNullable(t.getStatus())
                    .orElse("")
                    .trim()
                    .toLowerCase();

            String solType = Optional.ofNullable(t.getSolution_type())
                    .orElse("")
                    .trim()
                    .toLowerCase();

            // =============================
            // FINAL PRIORITY LOGIC (Strict Order)
            // =============================

            // Step 1: status = "pending" → Pending (Highest Priority)
            if ("pending".equals(status)) {
                stats.put("pending", stats.get("pending") + 1);
                continue;
            }

            // Step 2: solution_type = "temporary" → Temporary
            if ("temporary".equals(solType)) {
                stats.put("temporary", stats.get("temporary") + 1);
                continue;
            }

            // Step 3 & 4: solution_type = "permanent" OR status = "solved"/"resolved" → Resolved
            if ("permanent".equals(solType) ||
                "solved".equals(status) ||
                "resolved".equals(status)) {
                stats.put("resolved", stats.get("resolved") + 1);
                continue;
            }

            // If no condition matches → only total is counted (already done)
        }

        // Build final result
        Map<String, Object> result = new HashMap<>();
        result.put("year", year);
        result.put("months", months);

        return result;
    }
}