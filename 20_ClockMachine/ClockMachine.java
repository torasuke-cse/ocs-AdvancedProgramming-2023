import java.util.Calendar;

/**
 * 機器としての時計を再現したクラス
 */
public class ClockMachine extends Object {

    /**
     * 言語種別（英語／英名）
     */
    public static final int IN_ENGLISH = 0;

    /**
     * 言語種別（日本語／和名）
     */
    public static final int IN_JAPANESE = 1;

    /**
     * 月名（英名/和名）
     */
    public static final String[][] MONTH_NAMES = {
        {
            "January", "February", "March",
            "April",   "May",      "June",
            "July",    "August",   "September",
            "October", "November", "December"
        },
        {
            "睦月",   "如月", "弥生",
            "卯月",   "皐月", "水無月",
            "文月",   "葉月", "長月",
            "神無月", "霜月", "師走"
        }
    };

    /**
     * 曜日名（英名のみ）
     */
    public static final String[][] DAY_NAMES = {
        {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"},
        {"日", "月", "火", "水", "木", "金", "土"}
    };

    /**
     * 時計の機能を再現する。
     *
     * 実行した時点の日時を表示する。
     * 引数に指定した言語種別に応じて、月名の英名と和名を切り替える。
     *
     * @param languageType 言語種別
     */
    public void perform(int languageType) {

        Calendar aCalendar = Calendar.getInstance();   // 現在日時の生成

        // 現在日時の文字列を生成
        String dateString = this.getDateString(aCalendar, languageType);
        String timeString = this.getTimeString(aCalendar);

        // 月名を取得
        String monthName = this.getMonthName(aCalendar, languageType);
        
        // 画面出力
        System.out.println(dateString + " " + timeString);
        System.out.println("It's " + monthName + "!");

    }

    /**
     * 日付の文字列を生成して応答する。
     *
     * 指定された言語種別に応じた日付の文字列を生成して応答する。
     *
     * @param aCalendar 現在日時のカレンダー
     * @param languageType 言語種別
     * @return 日付の文字列
     */
    private String getDateString(Calendar aCalendar, int languageType) {

        int monthIndex     = aCalendar.get(Calendar.MONTH);          // 月の添え字の取得：0〜11
        int month          = monthIndex + 1;                         // 月の取得：1〜12
        int day            = aCalendar.get(Calendar.DAY_OF_MONTH);   // 日の取得
        int dayOfWeek      = aCalendar.get(Calendar.DAY_OF_WEEK);    // 曜日の取得：1〜7
        int dayOfWeekIndex = dayOfWeek - 1;                          // 曜日の添え字の取得：0〜6

        String dayName = ClockMachine.DAY_NAMES[languageType][dayOfWeekIndex];

        String dateString = month + "/" + day + "(" + dayName + ")";

        return dateString;

    }

    /**
     * 時刻の文字列を生成して応答する。
     *
     * @param aCalendar 現在日時のカレンダー
     * @return 時刻の文字列
     */
    private String getTimeString(Calendar aCalendar) {
    
        int hour   = aCalendar.get(Calendar.HOUR_OF_DAY);   // 時の取得
        int minute = aCalendar.get(Calendar.MINUTE);        // 分の取得

        String timeString = hour + ":" + minute;

        return timeString;

    }

    /**
     * 月名を応答する。
     *
     * 指定された言語種別に応じた月名を応答する。
     *
     * @param aCalendar 現在日時のカレンダー
     * @param languageType 言語種別
     * @return 月名の文字列
     */
    private String getMonthName(Calendar aCalendar, int languageType) {

        int monthIndex = aCalendar.get(Calendar.MONTH);   // 月の添え字の取得：0〜11

        String monthName = ClockMachine.MONTH_NAMES[languageType][monthIndex];

        return monthName;

    }
}
