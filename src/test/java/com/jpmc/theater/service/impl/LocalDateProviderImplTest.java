package com.jpmc.theater.service.impl;

import com.jpmc.theater.service.LocalDateProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateProviderImplTest {
    @Test
    void whenCallingInstance_sameInstanceIsReturned() {
        LocalDateProvider provider1 = LocalDateProviderImpl.singleton();
        LocalDateProvider provider2 = LocalDateProviderImpl.singleton();
        assertEquals(provider1, provider2);
        assertSame(provider1, provider2);
    }
}