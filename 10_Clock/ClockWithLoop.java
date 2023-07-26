public class ClockWithLoop extends Object {

    public static void main(String[] args) {

        System.out.println("This is a clock!!");

        for (int hour = 1; hour <= 24; hour++) {

            System.out.println(hour);

            if (hour == 12) {

                System.out.println("It's lunch time!!");
            
            }
            
        }

        System.out.println("Good night...");

    }

}
