package support.project.entity.Dtos;

import lombok.Data;

@Data
public class ActivityLogDTO {

    private String username;
    private String action;
    private String details;
    private String page;
    private String ip;
}
