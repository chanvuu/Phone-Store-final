package service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formater {
    public static String FormatVND(double vnd) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(vnd) + "đ";
    }
    
    public static String FormatMoney(double vnd) {
        DecimalFormat formatter = new DecimalFormat("#########");
        return formatter.format(vnd);
    }
    
    public static String FormatTime(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return formatDate.format(thoigian);
    }
    public static String FormatBirthDay(Date thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
        return formatDate.format(thoigian);
    }
    public static String FormatTime(Date thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return formatDate.format(thoigian);
    }
}
