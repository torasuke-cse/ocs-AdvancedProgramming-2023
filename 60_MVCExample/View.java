import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ビューオブジェクト（俳優）。
 * ユーザへの情報提示を担当する。
 * 
 * @author M.Nogi
 */
public class View extends JPanel {
    
    /**
     * このビューに関連付くモデル
     */
    protected Model model = null;

    /**
     * このビューに関連付くコントローラ
     */
    protected Controller controller = null;

    /**
     * 現在日時のラベル部品
     */
    protected JLabel dateLabel = null;

    /**
     * コンストラクタ。
     * 関連付くモデル、コントローラとを互いに紐付ける。
     * 
     * @param aModel 関連付くモデル
     * @param aController 関連付くコントローラ
     */
    public View(Model aModel, Controller aController) {

        // まずはJPanelのコンストラクタを呼び出す
        super();

        // モデルを知り、モデルに自分を知らせる（相互関係を構築）
        this.model = aModel;
        this.model.addView(this);

        // コントローラを知り、コントローラに自分とモデルを知らせる（チーム関係を構築）
        this.controller = aController;
        this.controller.setModel(aModel);
        this.controller.setView(this);
        this.addMouseListener(aController);

        // 背景色を設定する
        this.setBackground(Color.LIGHT_GRAY);

        // ラベル部品を表示する
        this.dateLabel = new JLabel();
        this.dateLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 32));
        this.dateLabel.setForeground(Color.RED);
        this.add(dateLabel);

    }

    /**
     * 特定の背景色に設定する（オレンジ）
     */
    public void colorOn() {

        // 背景色を設定する
        this.setBackground(Color.ORANGE);

    }

    /**
     * 標準の背景色に戻す（ライトグレー）
     */
    public void colorOff() {

        // 背景色を設定する
        this.setBackground(Color.LIGHT_GRAY);

    }

    /**
     * ユーザに提示しているもの（表示内容）を更新する
     */
    public void update() {

        // 現在時刻を再設定する
        String currentDateString = this.model.getCurrentDateString();
        this.dateLabel.setText(currentDateString);

        // 画面を再描画（更新）する
        this.repaint();

    }

}
