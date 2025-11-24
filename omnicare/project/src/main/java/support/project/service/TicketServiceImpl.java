package support.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import support.project.entity.Ticket;
import support.project.repository.TicketRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> searchByFilters(LocalDate fromDate, LocalDate toDate,
                                        String bankName, String cms,
                                        String problem, String product,
                                        String program) {

        // blank string as null
        bankName = clean(bankName);
        cms = clean(cms);
        problem = clean(problem);
        product = clean(product);
        program = clean(program);

        return ticketRepository.searchByFilters(fromDate, toDate, bankName, cms, problem, product, program);
    }

    private String clean(String val) {
        return (val == null || val.trim().isEmpty()) ? null : val.trim();
    }
}
