import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 出勤／退勤を管理するタイムレコーダー。
 */
public class TimeRecorder extends Object {

    /**
     * ユーザ名
     */
    public static final String USER_NAME = "R2A 野木雅文";

    /**
     * 出勤を表す定数
     */
    public static final String PUNCH_IN = "in";

    /**
     * 退勤を表す定数
     */
    public static final String PUNCH_OUT = "out";

    /**
     * 出退勤の一覧表示を表すコマンド文字列
     */
    public static final String COMMAND_LIST = "list";

    /**
     * 出力ファイル名
     */
    public static final String FILENAME = "timecard.csv";

    /**
     * CSVファイルのエンコーディング
     */
    public static final String CSV_ENCODING = "SJIS";

    /**
     * ソケット通信時のエンコーディング
     */
    public static final String SOCKET_ENCODING = "UTF-8";

    /**
     * ソケットサーバのIPアドレス
     */
    public static final String SOCKET_SERVER_IP = "localhost";

    /**
     * ソケットサーバのポート番号
     */
    public static final int SOCKET_SERVER_PORT = 8080;

    /**
     * データベースサーバのIPアドレス
     */
    public static final String DB_SERVER_IP = "10.15.169.63";

    /**
     * データベースサーバのポート番号
     */
    public static final int DB_SERVER_PORT = 3306;

    /**
     * データベース名
     */
    public static final String DB_NAME = "y2023_r2b";

    /**
     * データベースのユーザ名
     */
    public static final String DB_USER = "s2023_r2b";

    /**
     * データベースのパスワード
     */
    public static final String DB_PASSWORD = "ocs@4611";

    /**
     * 出退勤テーブルの名称
     */
    public static final String TABLE_NAME = "timecard_22XXXX";

    /**
     * 日付フォーマット
     */
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * エントリポイント。
     * 実行日時における出勤／退勤処理を行い、記録を一覧表示する。
     * 操作コマンドは、"in"（出勤）、"out"（退勤）、"list"（一覧表示）がある。
     *
     * @param args 実行時引数（[0]:操作コマンド、[1]:出退勤コメント）
     */
    public static void main(String[] args) {
        
        try {
            String command = args[0];

            if (command.equals(TimeRecorder.COMMAND_LIST)) {
                TimeRecorder.printTimestamps();

            } else {
                String punchStatus = command;
                String timestamp = null;

                if (args.length == 1) {
                    timestamp = TimeRecorder.generateTimestamp(punchStatus);
                } else {
                    String comment = args[1];
                    timestamp = TimeRecorder.generateTimestamp(punchStatus, comment);
                }

                TimeRecorder.recordTimestamp(timestamp);   // ローカルにファイルとして記録
                //TimeRecorder.sendTimestamp(timestamp);     // ソケットサーバ側に記録
                TimeRecorder.insertTimestamp(timestamp);     // データベースサーバ側に記録
            }

        } catch (ArrayIndexOutOfBoundsException anException) {
            System.out.println("エラー：実行時引数（出退勤の設定）が指定されていません。");
            anException.printStackTrace();

        } catch (IllegalArgumentException anException) {
            System.out.println("エラー：実行時引数（出退勤の設定）が不適切です。");
            anException.printStackTrace();

        } catch (IOException anException) {
            System.out.println("エラー：ファイルの入出力で問題が発生しました。");
            anException.printStackTrace();
        }
    }

    /**
     * タイムスタンプの文字列を生成する。（コメントなし）
     *
     * @param punchStatus 出退勤の設定（"in" or "out"）
     * @return タイムスタンプの文字列
     * @throws IllegalArgumentException 実行時引数の値が不適切な場合
     */
    public static String generateTimestamp(String punchStatus)
        throws IllegalArgumentException {

        String comment = null;

        return TimeRecorder.generateTimestamp(punchStatus, comment);
    }

    /**
     * タイムスタンプの文字列を生成する。（コメントあり）
     *
     * @param punchStatus 出退勤の設定（"in" or "out"）
     * @param comment 出退勤のコメント（nullの場合は空文字で生成）
     * @return タイムスタンプの文字列
     * @throws IllegalArgumentException 実行時引数の値が不適切な場合
     */
    public static String generateTimestamp(String punchStatus, String comment)
        throws IllegalArgumentException {

        String timestamp = null;
        String userName = TimeRecorder.USER_NAME;
        String currentDate = TimeRecorder.dateFormat.format(new Date());

        if (comment == null) {
            comment = "";
        }

        switch (punchStatus) {
            case TimeRecorder.PUNCH_IN:
                timestamp = "INFO," + userName + "," + currentDate + ",出勤," + comment;
                break;
            case TimeRecorder.PUNCH_OUT:
                timestamp = "INFO," + userName + "," + currentDate + ",退勤," + comment;
                break;
            default:
                timestamp = "ERROR," + currentDate + ",Invalid argument - " + punchStatus;
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
                new OutputStreamWriter(
                    new FileOutputStream(
                        new File(TimeRecorder.FILENAME),
                        isAppending
                    ),
                    TimeRecorder.CSV_ENCODING
                )
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

    /**
     * タイムスタンプをソケットサーバへ送信する。
     *
     * @param timestamp タイムスタンプの文字列
     * @throws IOException ファイル入出力に不具合が生じた場合
     */
    public static void sendTimestamp(String timestamp)
        throws IOException {

        final String SERVER_IP = TimeRecorder.SOCKET_SERVER_IP;
        final int SERVER_PORT  = TimeRecorder.SOCKET_SERVER_PORT;

        try (
            Socket aSocket = new Socket(SERVER_IP, SERVER_PORT)
        ) {
            OutputStreamWriter aWriter = new OutputStreamWriter(
                aSocket.getOutputStream(),
                TimeRecorder.SOCKET_ENCODING
            );
            aWriter.write(timestamp);
            aWriter.flush();

        } catch (IOException aException) {
            throw aException;
        }
    }

    /**
     * タイムスタンプをデータベースサーバへ登録（挿入）する。
     *
     * @param timestamp タイムスタンプの文字列
     * @throws IllegalStateException データベース操作の過程で不具合が生じた場合
     */
    public static void insertTimestamp(String timestamp)
        throws IllegalStateException {

        // タイムスタンプ文字列を分解する
        int limitOfSplit = -5;
        String[] values = timestamp.split(",", limitOfSplit);

        // データベースに格納するデータ項目を整理する
        String type        = values[0];
        String username    = values[1];

        Date aDate = null;

        try {
            aDate = TimeRecorder.dateFormat.parse(values[2]);
        } catch (ParseException anException) {
            throw new IllegalStateException("Invalid format of timestamp");
        }

        Timestamp timestampValue = new Timestamp(aDate.getTime());

        String punchStatus = values[3];
        String comment     = values[4];

        // データベース接続に使用するドライバを読み込む
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException anException) {
            throw new IllegalStateException("Cannot load DB driver");
        }

        // データベース接続に必要な情報を整理する
        final String SERVER_IP   = TimeRecorder.DB_SERVER_IP;
        final int SERVER_PORT    = TimeRecorder.DB_SERVER_PORT;
        final String DB_NAME     = TimeRecorder.DB_NAME;
        final String DB_USER     = TimeRecorder.DB_USER;
        final String DB_PASSWORD = TimeRecorder.DB_PASSWORD;

        StringBuilder url = new StringBuilder();
        url.append("jdbc:mariadb://");
        url.append(SERVER_IP).append(":").append(SERVER_PORT).append("/");
        url.append(DB_NAME);
        url.append("?user=").append(DB_USER);
        url.append("&password=").append(DB_PASSWORD);

        // データベースに接続してレコード挿入を実行する
        try (
            Connection aConnection = DriverManager.getConnection(url.toString())
        ) {
            String sql = "INSERT INTO " + TimeRecorder.TABLE_NAME + " VALUES(?, ?, ?, ?, ?)";
            PreparedStatement aStatement = aConnection.prepareStatement(sql);

            aStatement.setString(1, type);
            aStatement.setString(2, username);
            aStatement.setTimestamp(3, timestampValue);
            aStatement.setString(4, punchStatus);
            aStatement.setString(5, comment);

            int affectedRows = aStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new IllegalStateException("Cannot insert timestamp");
            }

        } catch (SQLException anException) {
            throw new IllegalStateException(anException.getMessage());
        }
    }

    /**
     * 出退勤の記録を一覧表示する。
     *
     * @throws IOException ファイル入出力に不具合が生じた場合
     */
    public static void printTimestamps() throws IOException {

        try (
            BufferedReader aReader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(
                        new File(TimeRecorder.FILENAME)
                    ),
                    TimeRecorder.CSV_ENCODING
                )
            )
        ) {
            int countOfIn = 0;
            int countOfOut = 0;

            String line = null;

            while ((line = aReader.readLine()) != null) {
                System.out.println(line);

                String[] values = line.split(",");
                String punchStatusInJapanese = values[3];

                if (punchStatusInJapanese.equals("出勤")) {
                    countOfIn++;
                } else if (punchStatusInJapanese.equals("退勤")) {
                    countOfOut++;
                }
            }

            System.out.println("出勤：" + countOfIn);
            System.out.println("退勤：" + countOfOut);
        
        } catch (IOException anException) {
            throw anException;
        }
    }

}
