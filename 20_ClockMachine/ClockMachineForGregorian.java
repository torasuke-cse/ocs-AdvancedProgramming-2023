import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

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
    protected String getMonthName() {

        int monthIndex = this.calendar.get(Calendar.MONTH);   // 月の添え字の取得：0〜11

        String[] monthNames = ClockMachineForGregorian.MONTH_NAMES;

        return monthNames[monthIndex];
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
    
        // 処理対象のリストを束縛する
        String[] targetList = ClockMachineForGregorian.MONTH_NAMES;

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
