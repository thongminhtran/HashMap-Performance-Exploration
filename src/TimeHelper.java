public class TimeHelper {
    public static void convertMillisecondsToMinute(long milliseconds) {
        // formula for conversion for
        // milliseconds to minutes.
        long minutes = (milliseconds / 1000) / 60;

        // formula for conversion for
        // milliseconds to seconds
        long seconds = (milliseconds / 1000) % 60;

        // Print the output
        System.out.println("Duration: " + milliseconds + " Milliseconds = "
                + minutes + " minutes and "
                + seconds + " seconds.");
    }
}
