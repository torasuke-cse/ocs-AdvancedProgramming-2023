import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * キャンバス部品を使用したグラフィカルな時計クラス。
 * 
 * @author M.Nogi
 */
public class CanvasClock extends Object {

    /**
     * 針の画像ファイル名（ハト）
     */
    public static final String HAND_IMAGE_FILENAME = "bird_hato.png";

    /**
     * 針の画像オブジェクト
     */
    protected BufferedImage handImage = null;

    /**
     * キャンバス部品（ペンや画像など）を用いた時計ウィンドウを表示する。
     */
    public void perform() {
        
        // ウィンドウの準備
        JFrame mainFrame = new JFrame("CanvasClock");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        Canvas aCanvas = new Canvas() {
            public void paint(Graphics aGraphics) {
                CanvasClock.this.drawHandImage(aGraphics);
            }
        };
        mainFrame.add(aCanvas);
        
        // ウィンドウの表示
        mainFrame.setVisible(true);

        // 時刻更新処理の定義
        TimerTask aTimerTask = new TimerTask() {
            public void run() {
                aCanvas.repaint();
            }
        };

        // 時刻更新を１秒ごとに実行
        Timer aTimer = new Timer();
        aTimer.scheduleAtFixedRate(aTimerTask, 0, 1000);   // Timer#schedule() はNG
    }

    /**
     * 針画像を描画する。
     * 
     * @param aGraphics 描画のためのオブジェクト
     */
    public void drawHandImage(Graphics aGraphics) {

        // 針画像オブジェクトを取得する
        BufferedImage handImage = this.getHandImage();

        // 画像の中心座標（回転軸となる座標）を算出する
        double anchorX = handImage.getWidth() / 2.0;
        double anchorY = handImage.getHeight() / 2.0;
        
        // 現在秒によって回転角度を算出する（度→ラジアン）
        int currentSeconds = Calendar.getInstance().get(Calendar.SECOND);
        double rotateDegree = currentSeconds / 60.0 * 360.0;
        double rotateRadian = Math.toRadians(rotateDegree);

        // 必要な角度（ラジアン）だけ右回転させる
        AffineTransform aTransform = AffineTransform.getRotateInstance(rotateRadian, anchorX, anchorY);
        AffineTransformOp aTransformOp = new AffineTransformOp(aTransform, AffineTransformOp.TYPE_BICUBIC);

        // 画像オブジェクトを描画する
        aGraphics.drawImage(aTransformOp.filter(handImage, null), 0, 0, null);
    }

    /**
     * 針画像オブジェクトを取得する。
     * 
     * @return 針画像オブジェクト
     */
    public BufferedImage getHandImage() {

        // 初回処理なら針画像ファイルから針画像オブジェクトを生成する
        if (this.handImage == null) {
            try {
                File imageFile = new File(CanvasClock.HAND_IMAGE_FILENAME);
                this.handImage = ImageIO.read(imageFile);

            } catch (IOException anException) {
                anException.printStackTrace();
            }
        }

        // 針画像オブジェクトを応答する
        return this.handImage;
    }

}
