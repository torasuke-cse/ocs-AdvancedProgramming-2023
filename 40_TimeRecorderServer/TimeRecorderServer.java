import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * クライアントからの出退勤登録を受け付けるソケットサーバ。
 */
public class TimeRecorderServer extends Object {

    /**
     * ポート番号
     */
    public static final int PORT = 8080;

    /**
     * 受信データのエンコーディング
     */
    public static final String ENCODING = "UTF-8";

    /**
     * エントリポイント。
     * ソケットサーバとして、指定ポート番号で接続待ちを行い、
     * クライアントからの出退勤登録要求に応じて、画面に出力する。
     *
     * @param args 実行時引数（特に使用しない）
     */
    public static void main(String[] args)
        throws IOException {

        final int EOF = -1;
        final int SERVER_PORT = TimeRecorderServer.PORT;
        
        System.out.println("===== Starting TimeRecorderServer =====");

        try (
            ServerSocket server = new ServerSocket(SERVER_PORT)
        ) {
            while (true) {
                Socket aSocket = server.accept();
                InputStreamReader aReader = new InputStreamReader(
                    aSocket.getInputStream(),
                    TimeRecorderServer.ENCODING
                );

                int value = 0;
                while ((value = aReader.read()) != EOF) {
                    System.out.printf("%c", value);
                }

                String senderIp = aSocket.getInetAddress().toString();
                System.out.printf(" from %s", senderIp);
                System.out.println();
            }
        }
    }

}