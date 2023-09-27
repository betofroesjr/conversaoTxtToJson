package com.betofroes.desafiotecnicobetofroes.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    public void should_return_string_when_format_date() {
        // given
        String date = "20210308";
        String patternInput = "yyyyMMdd";
        String patternOutput = "yyyy-MM-dd";

        // when
        String result = DateUtil.format(date, patternInput, patternOutput);

        // then
        assertNotNull(result);
        assertEquals("2021-03-08", result);
    }
}