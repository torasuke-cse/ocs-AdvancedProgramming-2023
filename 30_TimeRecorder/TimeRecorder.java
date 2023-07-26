import java.util.Date;

/**
 * 出勤／退勤を管理するタイムレコーダー。
 */
public class TimeRecorder extends Object {

    /**
     * 出勤を表す定数
     */
    public static final String PUNCH_IN = "in";

    /**
     * 退勤を表す定数
     */
    public static final String PUNCH_OUT = "out";

    /**
     * エントリポイント。
     * 実行日時における出勤／退勤処理を行う。
     *
     * @param args 実行時引数（[0]:出退勤の設定 - "in" or "out"）
     * @throws IllegalArgumentException 実行時引数の値が不適切な場合
     */
    public static void main(String[] args) throws IllegalArgumentException {
        
        String punchStatus = args[0];
        String message = null;
        Date currentDate = new Date();

        switch (punchStatus) {
            case TimeRecorder.PUNCH_IN:
                message = "[INFO] " + currentDate + " 出勤";
                break;
            case TimeRecorder.PUNCH_OUT:
                message = "[INFO] " + currentDate + " 退勤";
                break;
            default:
                message = "[ERROR] Invalid argument - " + punchStatus;
                throw new IllegalArgumentException(message);
        }

        System.out.println(message);

    }

}
