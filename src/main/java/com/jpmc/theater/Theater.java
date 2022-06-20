package com.jpmc.theater;

import com.google.gson.GsonBuilder;
import com.jpmc.theater.discount.*;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.printer.FormatPrinter;
import com.jpmc.theater.printer.JsonFormatPrinter;
import com.jpmc.theater.printer.TextFormatPrinter;
import com.jpmc.theater.service.LocalDateProvider;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ScheduleService;
import com.jpmc.theater.service.impl.DiscountServiceImpl;
import com.jpmc.theater.service.impl.LocalDateProviderImpl;
import com.jpmc.theater.service.impl.ReservationServiceImpl;
import com.jpmc.theater.service.impl.ScheduleServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Theater {

    private final ScheduleService scheduleService;
    private final ReservationService reservationService;
    private final String name;


    public Theater(ScheduleService scheduleService, String name, ReservationService reservationService) {
        this.scheduleService = scheduleService;
        this.name = name;

        this.reservationService = reservationService;
    }

    public void reserve(Customer customer, int sequence, int ticketCount) {
        Validator.validateTicketCount(ticketCount);
        var reservation = reservationService.reserve(customer, sequence, ticketCount);

        System.out.printf("Your reservation for the movie: %s  is ready!\nYou have to pay: %.2f $, Thank you!\n", reservation.getShowing().getMovie().getTitle(), reservation.getTotalFee());
    }


    public void printSchedule(FormatPrinter printer) {
        scheduleService.printSchedule(printer);
    }

    public static void main(String[] args) {

        List<Function<Showing, Double>> discountRules = List.of(
                new FirstShowingOfTheDayDiscount(),
                new NoonStartTimeDiscount(),
                new SecondShowingOfTheDayDiscount(),
                new SeventhOfTheMonthDiscount(),
                new SpecialMovieDiscount());
        DiscountServiceImpl discountService = new DiscountServiceImpl(discountRules);
        ScheduleService scheduleService = new ScheduleServiceImpl(new HashMap<>());
        init(scheduleService, LocalDateProviderImpl.singleton());
        ReservationService reservationService = new ReservationServiceImpl(scheduleService, discountService);

        Theater theater = new Theater(scheduleService, "Some theater", reservationService);
        FormatPrinter textPrinter = new TextFormatPrinter(LocalDateProviderImpl.singleton());

        FormatPrinter jsonPrinter = new JsonFormatPrinter(new GsonBuilder().setPrettyPrinting().create());
        theater.printSchedule(textPrinter);
        theater.printSchedule(jsonPrinter);

        theater.reserve(new Customer("Sabina", "1"), 1, 5);

    }

    private static void init(ScheduleService scheduleService, LocalDateProvider provider) {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), false);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), false);
        scheduleService.addToSchedule(1, new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0)), 11));
        scheduleService.addToSchedule(2, new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0)), 12.5));
        scheduleService.addToSchedule(3, new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50)), 9));
        scheduleService.addToSchedule(4, new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30)), 11));
        scheduleService.addToSchedule(5, new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10)), 12.5));
        scheduleService.addToSchedule(6, new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50)), 9));
        scheduleService.addToSchedule(7, new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30)), 11));
        scheduleService.addToSchedule(8, new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10)), 12.5));
        scheduleService.addToSchedule(9, new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)), 9));
    }
}
