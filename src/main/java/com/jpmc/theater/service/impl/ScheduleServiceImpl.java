package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.printer.FormatPrinter;
import com.jpmc.theater.service.ScheduleService;

import java.util.Map;

public class ScheduleServiceImpl implements ScheduleService {

    private final Map<Integer, Showing> schedule;

    public ScheduleServiceImpl(Map<Integer, Showing> schedule) {
        this.schedule = schedule;
    }

    @Override
    public void addToSchedule(int sequence, Showing showing){
        if(schedule.containsKey(sequence))
            throw new IllegalArgumentException("Sequence is not available, please try another one");
        schedule.put(sequence, showing);
    }

    @Override
    public Showing getShowing(int sequence){
        if(!schedule.containsKey(sequence))
            throw new IllegalStateException("Not able to find any showing for given sequence " + sequence);
        return schedule.get(sequence);
    }

    @Override
    public void printSchedule(FormatPrinter printer) {
        printer.print(schedule.values());
    }

}
