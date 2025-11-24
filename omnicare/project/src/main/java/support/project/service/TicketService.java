package support.project.service;

import support.project.entity.Ticket;
import java.time.LocalDate;
import java.util.List;

public interface TicketService {

    Ticket saveTicket(Ticket ticket);

    List<Ticket> searchByFilters(
            LocalDate fromDate,
            LocalDate toDate,
            String bankName,
            String cms,
            String problem,
            String product,
            String program
    );
}
