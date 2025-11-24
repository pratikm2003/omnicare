package support.project.service;

import java.time.LocalDate;
import java.util.Map;

public interface DashboardService {
	 Map<String, Object> getYearData(int year);
}
