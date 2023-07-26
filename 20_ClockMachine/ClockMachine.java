import java.util.Calendar;

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
     * 月名を応答する。
     *
     * 言語種別等、文化の違いによって応答すべき文字列表現が異なるため、
     * 抽象メソッドとして定義し、具象クラスにて再定義する。
     *
     * @return 月名の文字列
     */
    protected abstract String getMonthName();

}
