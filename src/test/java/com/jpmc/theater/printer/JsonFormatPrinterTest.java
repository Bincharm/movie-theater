package com.jpmc.theater.printer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jpmc.theater.TestShowingFactory;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.ShowingModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonFormatPrinterTest {
    @Test
    void shouldPrintToConsoleJsonFormat() throws Exception {
        Gson gson = new Gson();
        FormatPrinter textFormatPrinter = new JsonFormatPrinter(gson);
        List<Showing> showings = List.of(TestShowingFactory.dummyShowing(1), TestShowingFactory.dummyShowing(2));
        String text = tapSystemOut(() -> {
            textFormatPrinter.print(showings);
        });

        List<ShowingModel> parsedShowings = gson.fromJson(text, new TypeToken<List<ShowingModel>>(){}.getType());
        assertEquals(showings.size(), parsedShowings.size());
        assertTrue(parsedShowings.size() > 1);
        assertEquals(showings.get(0).getSequenceOfTheDay(), parsedShowings.get(0).getSequenceOfTheDay());
        assertEquals(showings.get(0).getMovie().getTitle(), parsedShowings.get(0).getMovieTitle());
        assertEquals(showings.get(1).getSequenceOfTheDay(), parsedShowings.get(1).getSequenceOfTheDay());
        assertEquals(showings.get(1).getMovie().getTitle(), parsedShowings.get(1).getMovieTitle());
    }
}