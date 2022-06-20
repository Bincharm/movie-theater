package com.jpmc.theater.service.impl;

import com.jpmc.theater.service.LocalDateProvider;

import java.time.LocalDate;

public final class LocalDateProviderImpl implements LocalDateProvider {

    private static LocalDateProviderImpl instance = null;

    private LocalDateProviderImpl() {
    }

    public static synchronized LocalDateProviderImpl singleton() {
        if (instance == null) {
            instance = new LocalDateProviderImpl();
        }
        return instance;
    }

    @Override
    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
