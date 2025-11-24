package support.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "activity_log")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;    
    private String action;      

    @Column(columnDefinition = "TEXT")
    private String details;     

    private String page;        
    private String ipAddress;   
    private LocalDateTime timestamp = LocalDateTime.now();
}
