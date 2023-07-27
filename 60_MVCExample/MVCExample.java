import javax.swing.JFrame;

/**
 * MVC（Model-View-Controller）の例題プログラム
 */
public class MVCExample extends Object {

    /**
     * MVCの例題プログラムを実行する
     */
    public void perform() {
        
        Model aModel = new Model();

        for (int index = 0; index < 10; index++) {

            Controller aController = new Controller();
            View aView = new View(aModel, aController);

            this.showWindowWith(aView, index);
        }
        
    }

    /**
     * 指定されたビューを乗せたウィンドウを生成・表示する
     * 
     * @param aView 
     * @param index 生成番号（連番でよい／表示位置に影響する）
     */
    public void showWindowWith(View aView, int index) {

        JFrame aFrame = new JFrame();
        aFrame.add(aView);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setLocation(80 * index, 80 * index);
        aFrame.setSize(550, 80);
        aFrame.setVisible(true);

    }

}
