import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * モデルオブジェクト（脚本家）。
 * データやビジネスロジックを担当する。
 * 
 * @author M.Nogi
 */
public class Model extends Object {
    
    /**
     * このモデルに関連付くビューのリスト
     */
    protected ArrayList<View> views = new ArrayList<View>();

    /**
     * 現在日時オブジェクト
     */
    protected Date currentDate = null;

    /**
     * コンストラクタ。
     * 時計としてのタイマー処理を準備する。
     */
    public Model() {

        // タイマーによって生起されるタスクを作成
        TimerTask aTask = new TimerTask() {
            public void run() {
                Model.this.currentDate = new Date();
                Model.this.changed();
            }
        };

        // １秒ごとにタスクを実行
        Timer aTimer = new Timer();
        aTimer.scheduleAtFixedRate(aTask, 0, 1000);
    }

    /**
     * このモデルに関連付くビューを登録する
     * 
     * @param aView 関連付くビュー
     */
    public void addView(View aView) {
    
        // 関連付くビューを覚えておく
        this.views.add(aView);
    }

    /**
     * このモデルの状態変化を関連付くビューたちに通知する
     */
    public void changed() {

        // 関連付くすべてのビューたちに更新を知らせていく
        for (View aView : this.views) {
            aView.update();
        }

    }

    /**
     * 現在日時を文字列として応答する
     * 
     * @return 現在日時の文字列
     */
    public String getCurrentDateString() {

        return this.currentDate.toString();

    }

}
