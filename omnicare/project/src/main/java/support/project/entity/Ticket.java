package support.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String bankName;
    private String cms;
    private String problem;
    private String product;
    
    private String program;
    private String solution;
    
    private String status;
    private String solution_type;
}
