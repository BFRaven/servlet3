package common.helpers;

//   convert from a java util date, to a java sql date; best if it's a method for reuse.

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateHelper extends CommonHelperLog {

    public  static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

    public static java.util.Date stringToUtilDate(String dateIn, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        java.util.Date dateOut = null;

        try {
            dateOut = dateFormat.parse(dateIn);

        } catch (ParseException parseEx) {
            logger.error(parseEx);
        }

        return dateOut;
    }
}