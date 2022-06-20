package com.jpmc.theater.printer;

import com.google.gson.Gson;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.ShowingModel;
import com.jpmc.theater.util.Util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JsonFormatPrinter implements FormatPrinter {
    private final Gson gson;

    public JsonFormatPrinter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void print(Collection<Showing> showings) {
        List<ShowingModel> showModelList = showings.stream()
                .map(showing -> new ShowingModel(
                        showing.getMovie().getTitle(),
                        showing.getSequenceOfTheDay(),
                        showing.getStartTime().toString(),
                        Util.humanReadableFormat(showing.getMovie().getRunningTime()),
                        showing.getTicketPrice())).collect(Collectors.toList());
        System.out.println(gson.toJson(showModelList));
    }
}
