package com.betofroes.desafiotecnicobetofroes.util;

import java.time.format.DateTimeFormatter;

public class DateUtil {

        public static String format(String date, String patternInput, String patternOutput) {
            DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern(patternInput);
            DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern(patternOutput);
            return formatterOutput.format(formatterInput.parse(date));
        }
}
