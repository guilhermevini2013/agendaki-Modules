package com.agendaki.scheduling.services.scheduling.factory;

import com.agendaki.scheduling.models.scheduling.DateJob;
import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.repositories.projections.SchedulingTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSchedulingFactory {

    public static List<LocalTime> getTimeFree(Service service, DateJob dateJob, List<SchedulingTime> schedulingTimes) {
        Short durationInMinutes = service.getDurationInMinutes();
        LocalTime startHour = dateJob.getStartTime();
        LocalTime endHour = dateJob.getEndTime();
        List<LocalTime> timeOptions = generatedTimeOptionsByDurationService(durationInMinutes, startHour, endHour);
        recoverTimeFree(timeOptions, schedulingTimes);
        return timeOptions;
    }

    private static void recoverTimeFree(List<LocalTime> timeOptions, List<SchedulingTime> schedulingTimes) {
        for (SchedulingTime schedulingTime : schedulingTimes) {
            if (timeOptions.contains(schedulingTime.startHour())) {
                timeOptions.removeIf(option -> option.isAfter(schedulingTime.startHour()) && option.isBefore(schedulingTime.startHour().plusMinutes(schedulingTime.durationInMinutes())) || option == schedulingTime.startHour());
            }
        }
    }

    private static List<LocalTime> generatedTimeOptionsByDurationService(Short durationInMinutes, LocalTime startHour, LocalTime endHour) {
        Boolean isTimeBeforeEndHour = true;
        List<LocalTime> timeOptions = new ArrayList<>();
        do {
            if (startHour.plusMinutes(durationInMinutes).isBefore(endHour.plusMinutes(60))) {
                timeOptions.add(startHour);
                startHour = startHour.plusMinutes(durationInMinutes);
            } else {
                isTimeBeforeEndHour = false;
            }
        } while (isTimeBeforeEndHour);
        return timeOptions;
    }

}
