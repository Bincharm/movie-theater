package com.jpmc.theater.service;

import com.jpmc.theater.printer.FormatPrinter;
import com.jpmc.theater.model.Showing;

public interface ScheduleService {
    void addToSchedule(int sequence, Showing showing);
    Showing getShowing(int sequence);
    void printSchedule(FormatPrinter printer);
}
