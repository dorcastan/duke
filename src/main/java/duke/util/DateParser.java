package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handles the parsing of dates from String formats into Date objects.
 */
public class DateParser {
    /**
     * Array of date formats that Duke is able to parse. A valid date can take
     * any of the following formats:
     * 1. EEE, dd MMM yy, hh:mm
     * 2. dd-MM-yyyy hh:mm
     * 3. dd-MM-yyyy
     * 4. dd-MM-yy
     * 5. hh:mm
     * 6. EEE
     * where EEE is the day of week (e.g. Mon, Fri).
     */
    public static final SimpleDateFormat[] DATE_FORMATS = {
            new SimpleDateFormat("EEE, dd MMM yy, hh:mm"),
            new SimpleDateFormat("dd-MM-yy hh:mm"),
            new SimpleDateFormat("dd-MM-yyyy"),
            new SimpleDateFormat("dd-MM-yy"),
            new SimpleDateFormat("hh:mm"),
            new SimpleDateFormat("EEE")
    };

    /**
     * Returns a Date corresponding to the date represented by the given String.
     * If the input format is invalid (does not match any of the date formats
     * specified in DateParser.DATE_FORMATS), returns the Date representing the
     * current instant.
     *
     * @param date String representing a date.
     * @return Date corresponding to the given date String, or the
     *         current instant.
     */
    public static Date parse(String date) {
        for (SimpleDateFormat format : DATE_FORMATS) {
            try {
                return format.parse(date);
            } catch (ParseException e) {
                // do nothing and try the next format
            }
        }
        return new Date();
    }
}
