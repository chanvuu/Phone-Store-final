package bus;

import java.util.ArrayList;

import dao.StatisticsDAO;
import dto.StatisticsDTO;

public class StatisticsBUS {
	StatisticsDAO statisticsDAO = new StatisticsDAO();

    public StatisticsBUS() {

    }
    
    public ArrayList<StatisticsDTO> getStatisticsBy7Day(){
        return statisticsDAO.getStatisticsBy7Day();
    }
}
