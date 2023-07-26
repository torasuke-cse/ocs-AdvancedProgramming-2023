import java.util.Calendar;
import java.util.Locale;

/**
 * 機器としての時計を再現したクラス（和暦版）
 */
public class ClockMachineForJapanese extends ClockMachine {

    /**
     * 月名
     */
    public static final String[] MONTH_NAMES = {
        "睦月",   "如月", "弥生",
        "卯月",   "皐月", "水無月",
        "文月",   "葉月", "長月",
        "神無月", "霜月", "師走"
    };

    /**
     * 曜日名
     */
    public static final String[] DAY_NAMES = {
        "日", "月", "火", "水", "木", "金", "土"
    };

    /**
     * 和暦の元号
     * @see https://docs.oracle.com/javase/8/docs/technotes/guides/intl/calendar.doc.html
     */
    public static final String[] ERA_NAMES = {
        "西暦", "明治", "大正", "昭和", "平成", "令和"
    }; 

    /**
     * 現在日時のカレンダーを生成して応答する。
     *
     * 日本のロケールを設定した上で、現在日時のカレンダーを生成して応答する。
     *
     * @see https://docs.oracle.com/javase/8/docs/technotes/guides/intl/calendar.doc.html
     * @see https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/util/Locale.html#of(java.lang.String,java.lang.String,java.lang.String)
     */
    protected Calendar getCalendar() {
        String language = "ja";
        String country  = "JP";
        String variant  = "JP";
        Locale aLocale = Locale.of(language, country, variant);
        return Calendar.getInstance(aLocale);
    }

    /**
     * 日付の文字列を生成して応答する。
     *
     * @return 日付の文字列
     */
    protected String getDateString() {

        int eraIndex       = this.calendar.get(Calendar.ERA);            // 年号の取得
        int year           = this.calendar.get(Calendar.YEAR);           // 年の取得
        String monthName   = this.getMonthName();                        // 月名の取得
        int monthIndex     = this.calendar.get(Calendar.MONTH);          // 月の添え字の取得：0〜11
        int month          = monthIndex + 1;                             // 月の取得：1〜12
        int day            = this.calendar.get(Calendar.DAY_OF_MONTH);   // 日の取得
        int dayOfWeek      = this.calendar.get(Calendar.DAY_OF_WEEK);    // 曜日の取得：1〜7
        int dayOfWeekIndex = dayOfWeek - 1;                              // 曜日の添え字の取得：0〜6

        String eraName = ClockMachineForJapanese.ERA_NAMES[eraIndex];
        String dayName = ClockMachineForJapanese.DAY_NAMES[dayOfWeekIndex];

        String dateString
            = eraName + year + "年" + month + "月" + day + "日(" + dayName + ")"
            + "（別名：" + monthName + "）";

        return dateString;

    }

    /**
     * 月名の配列を応答する。
     *
     * @return 月名の配列
     */
    protected String[] getMonthNames() {
        return ClockMachineForJapanese.MONTH_NAMES;
    }

}
