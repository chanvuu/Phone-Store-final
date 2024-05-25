package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bus.StatisticsBUS;
import dao.CustomerDAO;
import dao.ProductDAO;
import dao.StaffDAO;
import dao.SupplierDAO;
import dto.StatisticsDTO;
import service.Formater;
import service.TableSorter;
import service.itemTaskbar;
import service.CurveChart.CurveChart;
import service.CurveChart.ModelChart2;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class Statistics extends JPanel {

	StatisticsBUS statisticsBUS;
    JPanel jp_top, jp_center, pnlChart;
    CurveChart chart;
    private JTable tableStatistics;
    private JScrollPane scrolltableStatistics;
    private DefaultTableModel tblModel;
    ArrayList<StatisticsDTO> dataset;

    private JPanel panel_1;
    private JLabel lblNewLabel;
    private JPanel panel_3;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JPanel panel;
    private JPanel panel_2;
    private JPanel panel_4;
    private JLabel lblNewLabel_6;
    private JPanel panel_5;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JPanel panel_6;
    private JLabel lblNewLabel_9;
    private JPanel panel_7;
    private JLabel lblNewLabel_10;
    private JLabel lblNewLabel_11;


    public Statistics(StatisticsBUS statisticsBUS) {
      this.statisticsBUS = statisticsBUS;
      this.dataset = statisticsBUS.getStatisticsBy7Day();
      initComponent();
      loadDataTalbe(this.dataset);
    }
//    
    public void loadDataChart() {
        for (StatisticsDTO i : dataset) {
            chart.addData(new ModelChart2(i.getDate() + "", new double[]{i.getPrice(), i.getRevenue(), i.getProfit()}));
        }
    }

    public void loadDataTalbe(ArrayList<StatisticsDTO> list) {
        tblModel.setRowCount(0);
        for (StatisticsDTO i : dataset) {
            tblModel.addRow(new Object[]{
                i.getDate(), Formater.FormatVND(i.getPrice()), Formater.FormatVND(i.getRevenue()), Formater.FormatVND(i.getProfit())
            });
        }
    }

    private void initComponent() {
        Color FontColor = new Color(96, 125, 139);
		Color myColor = new Color(34, 33, 75);
		Color borderColor = myColor;

        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        jp_top = new JPanel();
        jp_top.setOpaque(false);
        jp_top.setBorder(new EmptyBorder(10, 10, 10, 10));
        jp_top.setPreferredSize(new Dimension(0, 120));

//        listitem = new itemTaskbar[getSt.length];
//        for (int i = 0; i < getSt.length; i++) {
//            listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][2], getSt[i][0], 0);
//            jp_top.add(listitem[i]);
//        }

        jp_center = new JPanel(new BorderLayout());
        jp_center.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
        jp_center.setBackground(Color.WHITE);
        JPanel jp_center_top = new JPanel(new FlowLayout());
        jp_center_top.setBorder(new EmptyBorder(10, 0, 0, 10));
        jp_center_top.setOpaque(false);
        JLabel txtChartName = new JLabel("Thống kê doanh thu 1 tuần");
        txtChartName.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        jp_center_top.add(txtChartName);

        pnlChart = new JPanel();
        pnlChart.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlChart.setOpaque(false);
        pnlChart.setLayout(new BorderLayout(0, 0));
        chart = new CurveChart();
        chart.addLegend("Vốn", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Doanh thu", new Color(54, 4, 143), new Color(104, 49, 200));
        chart.addLegend("Lợi nhuận", new Color(211, 84, 0), new Color(230, 126, 34));

        loadDataChart();

        chart.start();
        pnlChart.add(chart, BorderLayout.CENTER);

        jp_center.add(jp_center_top, BorderLayout.NORTH);
        jp_center.add(pnlChart, BorderLayout.CENTER);

        tableStatistics = new JTable();
        tableStatistics.setBorder(new LineBorder(borderColor, 2, false));
        tableStatistics.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
        tableStatistics.getTableHeader().setReorderingAllowed(false);
        tableStatistics.getTableHeader().setBackground(borderColor);
        tableStatistics.getTableHeader().setForeground(Color.white);
        scrolltableStatistics = new JScrollPane();
        scrolltableStatistics.setBorder(new EmptyBorder(10, 10, 10, 10));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ngày", "Vốn", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableStatistics.setModel(tblModel);
        tableStatistics.setAutoCreateRowSorter(true);
        tableStatistics.setDefaultEditor(Object.class, null);
        scrolltableStatistics.setViewportView(tableStatistics);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableStatistics.setDefaultRenderer(Object.class, centerRenderer);
        tableStatistics.setFocusable(false);
        tableStatistics.setRowHeight(20);
        scrolltableStatistics.setPreferredSize(new Dimension(0, 250));

        TableSorter.configureTableColumnSorter(tableStatistics, 0, TableSorter.DATE_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableStatistics, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableStatistics, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableStatistics, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        this.add(jp_top, BorderLayout.NORTH);
        GridBagLayout gbl_jp_top = new GridBagLayout();
        gbl_jp_top.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_jp_top.rowHeights = new int[]{0, 0};
        gbl_jp_top.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_jp_top.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        jp_top.setLayout(gbl_jp_top);
        
        panel_1 = new JPanel();
        panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_1.setLayout(new BorderLayout(0, 0));
        panel_1.setPreferredSize(new Dimension(380, 60));
        panel_1.setBackground(Color.white);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 0, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 0;
        jp_top.add(panel_1, gbc_panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setPreferredSize(new Dimension(100, 100));
        ImageIcon iconProduct = new ImageIcon("Assets/Icon/Sản Phẩm.png");
        int newProductHeight = (int) (iconProduct.getIconHeight() * ((double) 80 / iconProduct.getIconWidth()));
        Image scaledProductImage = iconProduct.getImage().getScaledInstance(80, newProductHeight, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(scaledProductImage));
        panel_1.add(lblNewLabel, BorderLayout.WEST);
        
        panel_3 = new JPanel();
        panel_3.setLayout(new FlowLayout(0, 0, 0));
        panel_3.setOpaque(false);
        panel_1.add(panel_3, BorderLayout.CENTER);
        
        lblNewLabel_2 = new JLabel("0");
        lblNewLabel_2.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_2.putClientProperty("FlatLaf.style", "font: 300% $semibold.font");
        lblNewLabel_2.setForeground(Color.gray);
        lblNewLabel_2.setText(Integer.toString(ProductDAO.getInstance().selectAll().size()));
        panel_3.add(lblNewLabel_2);
        
        lblNewLabel_1 = new JLabel("Sản phẩm tồn kho");
        lblNewLabel_1.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_1.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        lblNewLabel_1.setForeground(FontColor);
        panel_3.add(lblNewLabel_1);
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout(0, 0));
        panel.setPreferredSize(new Dimension(380, 60));
        panel.setBackground(Color.white);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 0, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 0;
        jp_top.add(panel, gbc_panel);
        panel.setLayout(new BorderLayout(0, 0));

        
        panel_4 = new JPanel();
        panel_4.setLayout(new FlowLayout(0, 0, 0));
        panel_4.setOpaque(false);
        panel.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        panel_2 = new JPanel();
        panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_2.setLayout(new BorderLayout(0, 0));
        panel_2.setPreferredSize(new Dimension(380, 60));
        panel_2.setBackground(Color.white);
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 0, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 2;
        gbc_panel_2.gridy = 0;
        jp_top.add(panel_2, gbc_panel_2);
        
        lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setPreferredSize(new Dimension(100, 100));
        ImageIcon iconStaff = new ImageIcon("Assets/Icon/Nhân Viên.png");
        int newStaffHeight = (int) (iconStaff.getIconHeight() * ((double) 80 / iconStaff.getIconWidth()));
        Image scaledStaffImage = iconStaff.getImage().getScaledInstance(80, newStaffHeight, Image.SCALE_SMOOTH);
        lblNewLabel_6.setIcon(new ImageIcon(scaledStaffImage));
        panel_2.add(lblNewLabel_6, BorderLayout.WEST);
        
        panel_5 = new JPanel();
        panel_5.setPreferredSize(new Dimension(380, 60));
        panel_5.setLayout(new FlowLayout(0, 0, 0));
        panel_5.setOpaque(false);
        panel.add(panel_4, BorderLayout.CENTER);
        
        lblNewLabel_4 = new JLabel("0");
        lblNewLabel_4.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_4.putClientProperty("FlatLaf.style", "font: 300% $semibold.font");
        lblNewLabel_4.setForeground(Color.gray);
        lblNewLabel_4.setText(Integer.toString(CustomerDAO.getInstance().selectAll().size()));
        panel_4.add(lblNewLabel_4);
        
        lblNewLabel_5 = new JLabel("Khách hàng từ trước đến nay");
        lblNewLabel_5.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_5.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        lblNewLabel_5.setForeground(FontColor);
        panel_4.add(lblNewLabel_5);
        
        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setPreferredSize(new Dimension(100, 100));

        ImageIcon iconCustomer = new ImageIcon("Assets/Icon/Khách Hàng.png");
        int newCustomerHeight = (int) (iconCustomer.getIconHeight() * ((double) 80 / iconCustomer.getIconWidth()));
        Image scaledCustomerImage = iconCustomer.getImage().getScaledInstance(80, newCustomerHeight, Image.SCALE_SMOOTH);
        lblNewLabel_3.setIcon(new ImageIcon(scaledCustomerImage));
        panel.add(lblNewLabel_3, BorderLayout.WEST);
        panel_2.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        lblNewLabel_7 = new JLabel("0");
        lblNewLabel_7.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_7.putClientProperty("FlatLaf.style", "font: 300% $semibold.font");
        lblNewLabel_7.setForeground(Color.gray);
        lblNewLabel_7.setText(Integer.toString(StaffDAO.getInstance().selectAll().size()));
        panel_5.add(lblNewLabel_7);
        
        lblNewLabel_8 = new JLabel("Nhân viên đang hoạt động");
        lblNewLabel_8.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_8.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        lblNewLabel_8.setForeground(FontColor);
        panel_5.add(lblNewLabel_8);
        
        panel_6 = new JPanel();
        panel_6.setPreferredSize(new Dimension(380, 60));
        panel_6.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_6.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel_6 = new GridBagConstraints();
        gbc_panel_6.fill = GridBagConstraints.BOTH;
        gbc_panel_6.gridx = 3;
        gbc_panel_6.gridy = 0;
        jp_top.add(panel_6, gbc_panel_6);
        panel_6.setLayout(new BorderLayout(0, 0));
        
        lblNewLabel_9 = new JLabel("");
        lblNewLabel_9.setPreferredSize(new Dimension(100, 100));
        ImageIcon iconSupp = new ImageIcon("Assets/Icon/Nhà Cung Cấp.png");
        int newSupHeight = (int) (iconSupp.getIconHeight() * ((double) 80 / iconSupp.getIconWidth()));
        Image scaledSupImage = iconSupp.getImage().getScaledInstance(80, newSupHeight, Image.SCALE_SMOOTH);
        lblNewLabel_9.setIcon(new ImageIcon(scaledSupImage));
        panel_6.add(lblNewLabel_9, BorderLayout.WEST);
        
        panel_7 = new JPanel();
        panel_7.setPreferredSize(new Dimension(380, 60));
        panel_7.setOpaque(false);
        panel_6.add(panel_7, BorderLayout.CENTER);
        panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        lblNewLabel_10 = new JLabel("0");
        lblNewLabel_10.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_10.putClientProperty("FlatLaf.style", "font: 300% $semibold.font");
        lblNewLabel_10.setForeground(Color.gray);
        lblNewLabel_10.setText(Integer.toString(SupplierDAO.getInstance().selectAll().size()));
        panel_7.add(lblNewLabel_10);
        
        lblNewLabel_11 = new JLabel("Nhà cung cấp còn cung cấp");
        lblNewLabel_11.setPreferredSize(new Dimension(250, 30));
        lblNewLabel_11.setForeground(new Color(96, 125, 139));
        lblNewLabel_11.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        lblNewLabel_11.setForeground(FontColor);
        panel_7.add(lblNewLabel_11);
        this.add(jp_center, BorderLayout.CENTER);
        this.add(scrolltableStatistics, BorderLayout.SOUTH);
    }
}