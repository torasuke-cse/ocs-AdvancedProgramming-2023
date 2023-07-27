/**
 * MVCの例題アプリケーションとして、複数画面を表示するクラス。
 * 
 * @author M.Nogi
 */
public class Main extends Object {

    /**
     * エントリポイント。
     * MVCの例題アプリケーションを起動する。
     * 
     * @param args 実行時引数。ここでは使用しない。
     */
    public static void main(String[] args) {
        
        MVCExample anApplication = new MVCExample();

        anApplication.perform();
        
    }
    
}
