import java.io.FileWriter;
import java.io.IOException;
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
     * 出力ファイル名
     */
    public static final String FILENAME = "timecard.log";

    /**
     * エントリポイント。
     * 実行日時における出勤／退勤処理を行う。
     *
     * @param args 実行時引数（[0]:出退勤の設定 - "in" or "out"）
     * @throws IllegalArgumentException 実行時引数の値が不適切な場合
     * @throws IOException ファイル入出力に不具合が生じた場合
     */
    public static void main(String[] args)
        throws IllegalArgumentException, IOException {
        
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
                message = "[ERROR] " + currentDate + " Invalid argument - " + punchStatus;
                throw new IllegalArgumentException(message);
        }

        System.out.println(message);

        // 追記モードでファイルを開く
        boolean isAppending = true;
        FileWriter aWriter = new FileWriter(TimeRecorder.FILENAME, isAppending);

        String lineSeparator = System.lineSeparator();   // 改行文字の取得

        aWriter.write(message);         // 書き出しを依頼する
        aWriter.write(lineSeparator);   // 書き出しを依頼する
        aWriter.flush();                // 書き出しを強制的に完了させる
        aWriter.close();                // ファイルを閉じる

    }

}
