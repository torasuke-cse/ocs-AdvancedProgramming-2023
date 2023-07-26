import java.io.BufferedWriter;
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
     */
    public static void main(String[] args) {
        
        try {
            String punchStatus = args[0];
            String timestamp = TimeRecorder.generateTimestamp(punchStatus);
            TimeRecorder.recordTimestamp(timestamp);

        } catch (ArrayIndexOutOfBoundsException anException) {
            System.out.println("エラー：実行時引数（出退勤の設定）が指定されていません。");
            anException.printStackTrace();

        } catch (IllegalArgumentException anException) {
            System.out.println("エラー：実行時引数（出退勤の設定）が不適切です。");
            anException.printStackTrace();

        } catch (IOException anException) {
            System.out.println("エラー：ファイルへの出力で問題が発生しました。");
            anException.printStackTrace();
        }
    }

    /**
     * タイムスタンプの文字列を生成する。
     *
     * @param punchStatus 出退勤の設定（"in" or "out"）
     * @return タイムスタンプの文字列
     * @throws IllegalArgumentException 実行時引数の値が不適切な場合
     */
    public static String generateTimestamp(String punchStatus)
        throws IllegalArgumentException {

        String timestamp = null;
        Date currentDate = new Date();

        switch (punchStatus) {
            case TimeRecorder.PUNCH_IN:
                timestamp = "[INFO] " + currentDate + " 出勤";
                break;
            case TimeRecorder.PUNCH_OUT:
                timestamp = "[INFO] " + currentDate + " 退勤";
                break;
            default:
                timestamp = "[ERROR] " + currentDate + " Invalid argument - " + punchStatus;
                throw new IllegalArgumentException(timestamp);
        }

        return timestamp;
    }

    /**
     * タイムスタンプを記録する。
     *
     * @param timestamp タイムスタンプの文字列
     * @throws IOException ファイル入出力に不具合が生じた場合
     */
    public static void recordTimestamp(String timestamp)
        throws IOException {

        System.out.println(timestamp);

        boolean isAppending = true;   // 追記モードでファイルを開く

        try (
            BufferedWriter aWriter = new BufferedWriter(
                new FileWriter(TimeRecorder.FILENAME, isAppending)
            )
        ) {
            aWriter.write(timestamp);   // 書き出しを依頼する
            aWriter.newLine();          // 改行を依頼する
            aWriter.flush();            // 書き出しを強制的に完了させる
                                        // close()は不要（try-with-resources文）

        } catch (IOException anException) {
            throw anException;              // 呼び出し元にそのまま投げる
        }
    }

}
