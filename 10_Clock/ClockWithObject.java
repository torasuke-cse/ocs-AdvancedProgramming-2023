/**
 * ClockWithObject Ver.6
 *
 * Inspired by R.Yanagimoto R2B 2023
 */
public class ClockWithObject extends Object {

    public static void main(String[] args) {

        String separator = System.getProperty("line.separator");
        
        StringBuilder aBuilder = new StringBuilder();
        aBuilder.append("This is a clock!!").append(separator);

        for (int hour = 1; hour <= 24; hour++) {

            aBuilder.append(hour).append(separator);

            if (hour == 12) {

                aBuilder.append("It's lunch time!!").append(separator);

            }

            if (hour == 24) {

                aBuilder.append("Good night...").append(separator);
            
            }
            
        }

        System.out.print(aBuilder.toString());
	
    }

}
