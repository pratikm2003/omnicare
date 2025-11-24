package support.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import support.project.entity.Ticket;
import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE " +
            "(:fromDate IS NULL OR :toDate IS NULL OR (t.date BETWEEN :fromDate AND :toDate)) AND " +
            "(:bankName IS NULL OR LOWER(t.bankName) LIKE LOWER(CONCAT('%', :bankName, '%'))) AND " +
            "(:cms IS NULL OR LOWER(t.cms) LIKE LOWER(CONCAT('%', :cms, '%'))) AND " +
            "(:problem IS NULL OR LOWER(t.problem) LIKE LOWER(CONCAT('%', :problem, '%'))) AND " +
            "(:product IS NULL OR LOWER(t.product) LIKE LOWER(CONCAT('%', :product, '%'))) AND " +
            "(:program IS NULL OR " +
            " LOWER(REPLACE(REPLACE(REPLACE(t.program, '-', ''), '(', ''), ')', '')) LIKE " +
            " LOWER(CONCAT('%', REPLACE(REPLACE(REPLACE(:program, '-', ''), '(', ''), ')', ''), '%')))")
    List<Ticket> searchByFilters(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("bankName") String bankName,
            @Param("cms") String cms,
            @Param("problem") String problem,
            @Param("product") String product,
            @Param("program") String program
    );
    
    
 // Fetch all tickets of a particular year
    @Query("SELECT t FROM Ticket t WHERE YEAR(t.date) = :year")
    List<Ticket> findByYear(int year);

    // Fetch all tickets of a particular month in a year
    @Query("SELECT t FROM Ticket t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month")
    List<Ticket> findByYearAndMonth(int year, int month);
}
