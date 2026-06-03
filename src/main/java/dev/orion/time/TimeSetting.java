package dev.orion.time;

import java.time.temporal.ChronoUnit;

public record TimeSetting (
        ChronoUnit unit,
        int value
){
}
