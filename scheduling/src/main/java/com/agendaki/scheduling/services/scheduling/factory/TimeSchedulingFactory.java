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
            LocalTime start = schedulingTime.startHour();
            LocalTime end = start.plusMinutes(schedulingTime.durationInMinutes());
            for (int i = 0; i < timeOptions.size()-1; i++) {
                if (timeOptions.get(i).isAfter(start.minusMinutes(1)) && timeOptions.get(i).isBefore(end.plusMinutes(1))) {
                    timeOptions.remove(i);
                }
            }
        }
    }

    private static List<LocalTime> generatedTimeOptionsByDurationService(Short durationInMinutes, LocalTime startHour, LocalTime endHour) {
        List<LocalTime> timeOptions = new ArrayList<>();
        while (startHour.plusMinutes(durationInMinutes).isBefore(endHour.plusMinutes(1))) {
            timeOptions.add(startHour);
            startHour = startHour.plusMinutes(durationInMinutes);
        }
        return timeOptions;
    }

}
