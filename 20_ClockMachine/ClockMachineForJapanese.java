import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

/**
 * 機器としての時計を再現したクラス（和暦版）
 */
public class ClockMachineForJapanese extends Object {

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
     * 現在日時のカレンダーを保持するフィールド。
     */
    private Calendar calendar = null;

    /**
     * 時計の機能を再現する。
     *
     * 実行した時点の日時を表示する。
     */
    public void perform() {

        // 各フィールドの設定
        this.calendar = Calendar.getInstance();

        // 現在日時の文字列を生成
        String dateString = this.getDateString();
        String timeString = this.getTimeString();

        // 画面出力
        System.out.println(dateString + " " + timeString);

    }

    /**
     * 日付の文字列を生成して応答する。
     *
     * @return 日付の文字列
     */
    private String getDateString() {

        int year           = this.calendar.get(Calendar.YEAR);           // 年の取得
        String monthName   = this.getMonthName();                        // 月名の取得
        int monthIndex     = this.calendar.get(Calendar.MONTH);          // 月の添え字の取得：0〜11
        int month          = monthIndex + 1;                             // 月の取得：1〜12
        int day            = this.calendar.get(Calendar.DAY_OF_MONTH);   // 日の取得
        int dayOfWeek      = this.calendar.get(Calendar.DAY_OF_WEEK);    // 曜日の取得：1〜7
        int dayOfWeekIndex = dayOfWeek - 1;                              // 曜日の添え字の取得：0〜6

        String dayName = ClockMachineForJapanese.DAY_NAMES[dayOfWeekIndex];

        String dateString
            = year + "年" + month + "月" + day + "日(" + dayName + ")"
            + "（別名：" + monthName + "）";

        return dateString;

    }

    /**
     * 時刻の文字列を生成して応答する。
     *
     * @return 時刻の文字列
     */
    private String getTimeString() {
    
        int hour   = this.calendar.get(Calendar.HOUR_OF_DAY);   // 時の取得
        int minute = this.calendar.get(Calendar.MINUTE);        // 分の取得

        String timeString = String.format("%02d:%02d", hour, minute);

        return timeString;

    }

    /**
     * 月名を応答する。
     *
     * @return 月名の文字列
     */
    private String getMonthName() {

        int monthIndex = this.calendar.get(Calendar.MONTH);   // 月の添え字の取得：0〜11

        String monthName = ClockMachineForJapanese.MONTH_NAMES[monthIndex];

        return monthName;

    }

    /**
     * 月名をすべて繋げた文字列を応答する。
     *
     * 月名を、すべて文字列として結合して応答する。
     * また、参考プログラムとして、配列要素の参照方法４種類を試行する形で実行する。
     *
     * @return 月名を繋げた文字列
     */
    public String getAllMonthNames() {
    
        // 処理対象のリストを抽出する
        String[] targetList = ClockMachineForJapanese.MONTH_NAMES;

        // 画面出力の準備
        String separator = System.getProperty("line.separator");
        StringBuilder aBuilder = new StringBuilder();

        // カウントアップ（ダメダメ）
        for (int index = 0; index < targetList.length; index++) {
            aBuilder.append(targetList[index]).append(" ");
        }

        aBuilder.append(separator);

        // イテレータ（マシ）
        Iterator<String> anIterator = Arrays.asList(targetList).iterator();
        while (anIterator.hasNext()) {
            aBuilder.append(anIterator.next()).append(" ");
        }

        aBuilder.append(separator);

        // 拡張for（イケてる）
        for (String monthName : targetList) {
            aBuilder.append(monthName).append(" ");
        }

        aBuilder.append(separator);

        // Stream API
        Arrays.stream(targetList).forEach((String monthName) -> {
            aBuilder.append(monthName).append(" ");
        });

        return aBuilder.toString();
    }
}
