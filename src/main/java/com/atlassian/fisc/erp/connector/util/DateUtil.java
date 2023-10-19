package com.atlassian.fisc.erp.connector.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String convertDateTakeOffMillis(String inputDateTime){

        // Parse the input date-time string to OffsetDateTime object
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(inputDateTime);

        // Convert the OffsetDateTime to UTC and format it to the desired output format
        return offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_INSTANT);
    }
}
