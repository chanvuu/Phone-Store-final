package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import database.JDBCmySQL;
import dto.StatisticsDTO;

public class StatisticsDAO {
    public static StatisticsDAO getInstance() {
        return new StatisticsDAO();
    }
    
    public ArrayList<StatisticsDTO> getStatisticsByDay(int month, int year) {
        ArrayList<StatisticsDTO> result = new ArrayList<>();
        try {
            String day = year + "-" + month + "-" + "01";
			Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT \n"
                    + "  dates.date AS ngay, \n"
                    + "  COALESCE(SUM(receiptdetail.purchasePrice), 0) AS purchasePrice, \n"
                    + "  COALESCE(SUM(invoice.sellingPrice), 0) AS sellingPrice\n"
                    + "FROM (\n"
                    + "  SELECT DATE('" + day + "') + INTERVAL c.number DAY AS date\n"
                    + "  FROM (\n"
                    + "    SELECT 0 AS number\n"
                    + "    UNION ALL SELECT 1\n"
                    + "    UNION ALL SELECT 2\n"
                    + "    UNION ALL SELECT 3\n"
                    + "    UNION ALL SELECT 4\n"
                    + "    UNION ALL SELECT 5\n"
                    + "    UNION ALL SELECT 6\n"
                    + "    UNION ALL SELECT 7\n"
                    + "    UNION ALL SELECT 8\n"
                    + "    UNION ALL SELECT 9\n"
                    + "    UNION ALL SELECT 10\n"
                    + "    UNION ALL SELECT 11\n"
                    + "    UNION ALL SELECT 12\n"
                    + "    UNION ALL SELECT 13\n"
                    + "    UNION ALL SELECT 14\n"
                    + "    UNION ALL SELECT 15\n"
                    + "    UNION ALL SELECT 16\n"
                    + "    UNION ALL SELECT 17\n"
                    + "    UNION ALL SELECT 18\n"
                    + "    UNION ALL SELECT 19\n"
                    + "    UNION ALL SELECT 20\n"
                    + "    UNION ALL SELECT 21\n"
                    + "    UNION ALL SELECT 22\n"
                    + "    UNION ALL SELECT 23\n"
                    + "    UNION ALL SELECT 24\n"
                    + "    UNION ALL SELECT 25\n"
                    + "    UNION ALL SELECT 26\n"
                    + "    UNION ALL SELECT 27\n"
                    + "    UNION ALL SELECT 28\n"
                    + "    UNION ALL SELECT 29\n"
                    + "    UNION ALL SELECT 30\n"
                    + "  ) AS c\n"
                    + "  WHERE DATE('" + day + "') + INTERVAL c.number DAY <= LAST_DAY('" + day + "')\n"
                    + ") AS dates\n"
                    + "LEFT JOIN invoice ON DATE(invoice.date) = dates.date\n"
                    + "LEFT JOIN invoicedetail ON invoicedetail.invoiceId = invoice.invoiceId\n"
                    + "LEFT JOIN receipt ON DATE(receipt.date) = dates.date\\n"
                    + "LEFT JOIN receiptDetail ON receiptDetail.receiptId = receipt.receiptId\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("ngay");
                int price = rs.getInt("chiphi");
                int revenue = rs.getInt("doanhthu");
                int profit = revenue - price;
                StatisticsDTO statistics = new StatisticsDTO(null, price, revenue, profit);
                result.add(statistics);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<StatisticsDTO> getStatisticsBy7Day() {
        ArrayList<StatisticsDTO> result = new ArrayList<>();
        try {
			Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = """
                         WITH RECURSIVE dates(date) AS (
                           SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                           UNION ALL
                           SELECT DATE_ADD(date, INTERVAL 1 DAY)
                           FROM dates
                           WHERE date < CURDATE()
                         )
                         SELECT 
                           dates.date AS date,
                           COALESCE(SUM(invoiceDetail.sellingPrice), 0) AS revenue,
                           COALESCE(SUM(receiptDetail.purchasePrice), 0) AS price
                         FROM dates
            			 LEFT JOIN invoice ON DATE(invoice.date) = dates.date
            		     LEFT JOIN invoiceDetail ON invoicedetail.invoiceId = invoice.invoiceId
            		     LEFT JOIN receipt ON DATE(receipt.date) = dates.date
            		     LEFT JOIN receiptDetail ON receiptDetail.receiptId = receipt.receiptId
                         GROUP BY dates.date
                         ORDER BY dates.date;""";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("date");
                int price = rs.getInt("price");
                int revenue = rs.getInt("revenue");
                int profit = revenue - price;
                StatisticsDTO statistics = new StatisticsDTO(date, price, revenue, profit);
                result.add(statistics);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
