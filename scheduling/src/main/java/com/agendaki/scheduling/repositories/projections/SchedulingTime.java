package com.agendaki.scheduling.repositories.projections;

import java.time.LocalTime;

public record SchedulingTime(LocalTime startHour, Short durationInMinutes) {
}
