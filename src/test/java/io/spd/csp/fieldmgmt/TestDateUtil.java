package io.spd.csp.fieldmgmt;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public final class TestDateUtil {

    public static LocalDate pastDate() {
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-(100 * 365), -1));
    }

    public static LocalDate futureDate() {
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(1, (100 * 365)));
    }
}
