import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

/**
 * コントローラオブジェクト（監督役）。
 * モデル（脚本家）やビュー（俳優）との関係構築や、
 * ユーザリクエストの連携を担当する。
 * 
 * @author M.Nogi
 */
public class Controller extends MouseInputAdapter {
    
    /**
     * このコントローラに関連付くモデル
     */
    public Model model = null;

    /**
     * このコントローラに関連付くビュー
     */
    public View view = null;

    /**
     * マウスカーソルが自領域に入ってきた時の処理
     */
    public void mouseEntered(MouseEvent anEvent) {

        this.view.colorOn();

    }

    /**
     * マウスカーソルが自領域から出ていった時の処理
     */
    public void mouseExited(MouseEvent anEvent) {

        this.view.colorOff();
        
    }

    /**
     * このコントローラに関連付くモデルを設定する
     * 
     * @param 関連付くモデル
     */
    public void setModel(Model aModel) {

        this.model = aModel;

    }

    /**
     * このコントローラに関連付くビューを設定する
     * 
     * @param 関連付くビュー
     */
    public void setView(View aView) {

        this.view = aView;

    }

}
