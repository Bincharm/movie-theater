package com.jpmc.theater.printer;

import com.jpmc.theater.model.Showing;

import java.util.Collection;

public interface FormatPrinter {
    void print(Collection<Showing> showings);
}
