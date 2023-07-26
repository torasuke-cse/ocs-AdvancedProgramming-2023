public class Main extends Object {

    public static void main(String[] args) {

        // 西暦表示
        ClockMachineForGregorian gregorianClock = new ClockMachineForGregorian();
        gregorianClock.perform();
        System.out.println(gregorianClock.getAllMonthNames());

        // 和暦表示
        ClockMachineForJapanese japaneseClock = new ClockMachineForJapanese();
        japaneseClock.perform();
        System.out.println(japaneseClock.getAllMonthNames());

        // 和暦表示（出雲版）
        ClockMachineForIzumonian izumonianClock = new ClockMachineForIzumonian();
        izumonianClock.perform();
        System.out.println(izumonianClock.getAllMonthNames());

        // 仏暦表示
        ClockMachineForBuddhist buddhistClock = new ClockMachineForBuddhist();
        buddhistClock.perform();
        System.out.println(buddhistClock.getAllMonthNames());

    }

}
