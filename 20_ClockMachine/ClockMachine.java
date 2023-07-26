import java.util.Date;

public class ClockMachine extends Object {

    public void perform() {

        Date currentDate = new Date();   // 現在日時のオブジェクトを生成

        int month = currentDate.getMonth();   // 月の取得
        int day   = currentDate.getDate();    // 日の取得

        int hour   = currentDate.getHours();     // 時の取得
        int minute = currentDate.getMinutes();   // 分の取得

        // 現在日時の文字列を生成
        String datetime = (month + 1) + "/" + day + " " + hour + ":" + minute;

        // 画面出力
        System.out.println(datetime);
        
    }
    
}
