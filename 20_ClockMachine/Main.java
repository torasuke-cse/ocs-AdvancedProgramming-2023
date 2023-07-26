public class Main extends Object {

    public static void main(String[] args) {

        ClockMachine aMachine = new ClockMachine();

        aMachine.perform(ClockMachine.IN_ENGLISH);    // 英名
        aMachine.perform(ClockMachine.IN_JAPANESE);   // 和名
        
        System.out.println(aMachine.getAllMonthNames());

    }

}
