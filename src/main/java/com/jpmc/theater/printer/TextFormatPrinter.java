package com.jpmc.theater.printer;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.LocalDateProvider;
import com.jpmc.theater.util.Util;

import java.util.Collection;

public class TextFormatPrinter implements FormatPrinter {

    private final LocalDateProvider provider;

    public TextFormatPrinter(LocalDateProvider provider) {
        this.provider = provider;
    }

    @Override
    public void print(Collection<Showing> showings) {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        showings.forEach((showing) ->
                System.out.println(showing.getSequenceOfTheDay() + ": " +
                        showing.getStartTime() + " " +
                        showing.getMovie().getTitle() + " " +
                        Util.humanReadableFormat(showing.getMovie().getRunningTime()) +
                        " $" + showing.getTicketPrice())
        );
        System.out.println("===================================================");
    }
}
