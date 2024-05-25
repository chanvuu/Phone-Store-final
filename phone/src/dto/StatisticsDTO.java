package dto;

import java.sql.Date;

public class StatisticsDTO {
    private Date date;
    private int price;
    private int revenue;
    private int profit;
    
	public StatisticsDTO() {
		super();
	}

    public StatisticsDTO(Date date, int price, int revenue, int profit) {
        this.date = date;
        this.price = price;
        this.revenue = revenue;
        this.profit = profit;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "StatisticsDTO [date=" + date + ", price=" + price + ", revenue=" + revenue + ", profit=" + profit + "]";
	}
    
    
}
