package date_time_questions;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class question_4 {

    public static void main(String[] args) {
        String zoneStr = "";
        // checking if the ZoneId has been passed as an argument,
        // else use the default value of "Asia/Tokyo"
        if (args.length == 1) {
            zoneStr += args[0];
        } else {
            zoneStr = "Asia/Tokyo";
        }

        // create a UTC instant timestamp
        Instant inst = Instant.now();

        // convert to ZonedDateTime with .atZone() method
        ZonedDateTime zdt =
                inst.atZone(ZoneId.of(zoneStr));

        // printing out for visual examination
        System.out.println("Instant time: " + inst);
        System.out.println("ZoneId: " + zoneStr);
        System.out.println("ZonedDateTime: " + zdt);
    }
}
