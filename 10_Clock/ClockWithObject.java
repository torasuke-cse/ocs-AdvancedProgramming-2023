public class ClockWithObject extends Object {

    public static void main(String[] args) {

        String separator = System.getProperty("line.separator");
        
        StringBuilder aBuilder = new StringBuilder();
        aBuilder.append("This is a clock!!" + separator);

        for (int hour = 1; hour <= 24; hour++) {

            aBuilder.append(hour + separator);

            if (hour == 12) {

                aBuilder.append("It's lunch time!!" + separator);

            }

            if (hour == 24) {

                aBuilder.append("Good night..." + separator);
            
            }
            
        }

        System.out.print(aBuilder.toString());

    }

}
