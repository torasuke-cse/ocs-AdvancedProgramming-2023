import java.util.Calendar;

/**
 * 機器としての時計を再現したクラス（西暦版）
 */
public class ClockMachineForGregorian extends ClockMachine {

    /**
     * 月名
     */
    public static final String[] MONTH_NAMES = {
        "January", "February", "March",
        "April",   "May",      "June",
        "July",    "August",   "September",
        "October", "November", "December"
    };

    /**
     * 曜日名
     */
    public static final String[] DAY_NAMES = {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };

    /**
     * 日付の文字列を生成して応答する。
     *
     * @return 日付の文字列
     */
    protected String getDateString() {

        int year           = this.calendar.get(Calendar.YEAR);           // 年の取得
        String monthName   = this.getMonthName();                        // 月名の取得
        int day            = this.calendar.get(Calendar.DAY_OF_MONTH);   // 日の取得
        int dayOfWeek      = this.calendar.get(Calendar.DAY_OF_WEEK);    // 曜日の取得：1〜7
        int dayOfWeekIndex = dayOfWeek - 1;                              // 曜日の添え字の取得：0〜6

        String dayName = ClockMachineForGregorian.DAY_NAMES[dayOfWeekIndex];

        String dateString = dayName + " " + monthName + " " + day + " " + year;

        return dateString;

    }

    /**
     * 月名の配列を応答する。
     *
     * @return 月名の配列
     */
    protected String[] getMonthNames() {
        return ClockMachineForGregorian.MONTH_NAMES;
    }

}
