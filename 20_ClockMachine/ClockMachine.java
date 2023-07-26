import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

/**
 * 機器としての時計を再現したクラス（西暦版）
 */
public abstract class ClockMachine extends Object {

    /**
     * 現在日時のカレンダーを保持するフィールド。
     */
    protected Calendar calendar = null;

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
     * 言語種別等、文化の違いによって応答すべき文字列表現が異なるため、
     * 抽象メソッドとして定義し、具象クラスにて再定義する。
     *
     * @return 日付の文字列
     */
    protected abstract String getDateString();

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
     * 月名の配列を応答する。
     *
     * 言語種別等、文化の違いによって応答すべき文字列表現が異なるため、
     * 抽象メソッドとして定義し、具象クラスにて再定義する。
     *
     * @return 月名の配列
     */
    protected abstract String[] getMonthNames();

    /**
     * 月名を応答する。
     *
     * @return 月名の文字列
     */
    protected String getMonthName() {

        int monthIndex = this.calendar.get(Calendar.MONTH);   // 月の添え字の取得：0〜11

        String[] monthNames = this.getMonthNames();

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
        String[] targetList = this.getMonthNames();

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
