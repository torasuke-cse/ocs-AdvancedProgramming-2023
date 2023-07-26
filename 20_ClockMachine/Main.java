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

    }

}
