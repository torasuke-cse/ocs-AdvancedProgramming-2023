import java.util.Calendar;

public class ClockMachine extends Object {

    public void perform() {

        // 月名（英名）
        final String[] MONTH_NAMES = {
            "January", "February", "March",
            "April",   "May",      "June",
            "July",    "August",   "September",
            "October", "November", "December"
        };

        Calendar aCalendar = Calendar.getInstance();   // 現在日時の生成

        System.out.println(aCalendar.getClass().getName());   // インスタンスのクラス名を表示

        int month = aCalendar.get(Calendar.MONTH);          // 月の取得
        int day   = aCalendar.get(Calendar.DAY_OF_MONTH);   // 日の取得

        int hour   = aCalendar.get(Calendar.HOUR_OF_DAY);   // 時の取得
        int minute = aCalendar.get(Calendar.MINUTE);        // 分の取得

        // 現在日時の文字列を生成
        String datetime = (month + 1) + "/" + day + " " + hour + ":" + minute;

        // 月名の変換
        String monthName = MONTH_NAMES[month];
        
        // 画面出力
        System.out.println(datetime);
        System.out.println("It's " + monthName + "!");
        
    }
    
}
