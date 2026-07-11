package dev.orion.commons.utils.time;

import java.time.temporal.ChronoUnit;

public record TimeSetting (
        ChronoUnit unit,
        int value
){
}
