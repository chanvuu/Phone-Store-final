package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import bus.StatisticsBUS;

public class menuStatistics extends JPanel {

	private static final long serialVersionUID = 1L;
	StatisticsBUS statisticsBUS = new StatisticsBUS();

	/**
	 * Create the panel.
	 */
	public menuStatistics() {
		Color myColor = new Color(34, 33, 75);
		Color backGroundColor = Color.white;
		Color borderColor = myColor;
		Color textColor = myColor;
		Color buttonColor = myColor;
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.white);
		tabbedPane.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tabbedPane.setBackground(myColor);
		add(tabbedPane, BorderLayout.CENTER);
		
		Statistics statisticsPanel = new Statistics(statisticsBUS);
		statisticsPanel.setBorder(new LineBorder(borderColor, 3));
		statisticsPanel.setBackground(Color.RED);
		tabbedPane.addTab("Tổng Quan", null, statisticsPanel, null);
	}

}
