package com.jpmc.theater.printer;

import com.jpmc.theater.TestShowingFactory;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.LocalDateProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class TextFormatPrinterTest {
    @Test
    void shouldPrintToConsoleInPlainText() throws Exception {
        LocalDateProvider localDateProvider = Mockito.mock(LocalDateProvider.class);
        when(localDateProvider.currentDate()).thenReturn(LocalDate.of(2022, 6,1));
        FormatPrinter textFormatPrinter = new TextFormatPrinter(localDateProvider);
        List<Showing> showings = List.of(TestShowingFactory.dummyShowing(1), TestShowingFactory.dummyShowing(2));
        String text = tapSystemOut(() -> {
            textFormatPrinter.print(showings);
        });

        assertTrue(text.trim().contains("2022-06-01"));
        assertTrue(text.trim().contains("1: 2022-06-01T13:15 Test (0 hours 50 minutes) $100.0"));
        assertTrue(text.trim().contains("2: 2022-06-01T13:15 Test (0 hours 50 minutes) $100.0"));
    }
}