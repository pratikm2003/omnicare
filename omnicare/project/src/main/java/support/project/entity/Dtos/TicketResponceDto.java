package support.project.entity.Dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponceDto {
	
	private Long id;
	private LocalDate date;
    private String bankName;
    private String cms;
    private String problem;
    private String product;
    private String status;
	

}
