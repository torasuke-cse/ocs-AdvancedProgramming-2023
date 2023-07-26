/**
 * 機器としての時計を再現したクラス（和暦/出雲版）
 */
public class ClockMachineForIzumonian extends ClockMachineForJapanese {

    /**
     * 出雲国における10月の和名
     */
    private static final String TENTH_MONTH_NAME = "神在月";

    /**
     * 月名
     */
    public static final String[] MONTH_NAMES = {
        "睦月",   "如月", "弥生",
        "卯月",   "皐月", "水無月",
        "文月",   "葉月", "長月",
        "神在月", "霜月", "師走"
    };

    /**
     * 月名の配列を応答する。
     *
     * 10月を「神在月」とする月名の配列を応答する。
     *
     * @return 月名の配列
     */
    protected String[] getMonthNames() {
        return ClockMachineForIzumonian.MONTH_NAMES;
    }

    /*
     * このやり方は、定数を直接参照されるとまずい（ClockMachineForIzumonian.MONTH_NAMES）
    protected String[] getMonthNames() {
        String[] originalMonthNames = ClockMachineForJapanese.MONTH_NAMES;
        String[] izumonianMonthNames = originalMonthNames.clone();

        int tenthMonth = 10;
        int tenthMonthIndex = tenthMonth - 1;
        izumonianMonthNames[tenthMonthIndex] = "神在月";

        return izumonianMonthNames;
    }
    */

}
