public class ClockWithLoop extends Object {

    public static void main(String[] args) {

        System.out.println("This is a clock!!");

        for (int hour = 1; hour <= 12; hour++) {

            System.out.println(hour);

        }
        
        System.out.println("It's lunch time!!");

        for (int hour = 13; hour <= 24; hour++) {

            System.out.println(hour);

        }
        
        System.out.println("Good night...");

    }

}
