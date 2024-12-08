package com.agendaki.scheduling.services.scheduling.factory;

import com.agendaki.scheduling.models.scheduling.DateJob;
import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.repositories.projections.SchedulingTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimeSchedulingFactory {

    public static List<LocalTime> getTimeFree(Service service, DateJob dateJob, List<SchedulingTime> schedulingTimes) {
        Short durationInMinutes = service.getDurationInMinutes();

        // Garantir que a duração seja múltiplo de 5
        if (durationInMinutes % 5 != 0) {
            durationInMinutes = (short) (Math.ceil(durationInMinutes / 5.0) * 5);
        }

        LocalTime startHour = dateJob.getStartTime();
        LocalTime endHour = dateJob.getEndTime();

        // Gerando as opções de horários disponíveis com base na duração ajustada
        List<LocalTime> timeOptions = generatedTimeOptionsByDurationService(durationInMinutes, startHour, endHour);

        // Remover os horários já ocupados
        recoverTimeFree(timeOptions, schedulingTimes);

        return timeOptions;
    }

    private static void recoverTimeFree(List<LocalTime> timeOptions, List<SchedulingTime> schedulingTimes) {
        Iterator<LocalTime> iterator = timeOptions.iterator();

        // Iterar sobre os horários ocupados e remover as opções de horário conflitantes
        while (iterator.hasNext()) {
            LocalTime currentTime = iterator.next();
            for (SchedulingTime schedulingTime : schedulingTimes) {
                LocalTime start = schedulingTime.startHour();
                LocalTime end = start.plusMinutes(schedulingTime.durationInMinutes());

                // Se o horário está dentro do intervalo ocupado, remove da lista de opções
                if (!currentTime.isBefore(start) && !currentTime.isAfter(end)) {
                    iterator.remove();
                    break; // Não precisa verificar mais agendamentos para esse horário
                }
            }
        }
    }

    private static List<LocalTime> generatedTimeOptionsByDurationService(Short durationInMinutes, LocalTime startHour, LocalTime endHour) {
        List<LocalTime> timeOptions = new ArrayList<>();

        // Arredondar o horário de início para o múltiplo de 5 mais próximo
        int startMinutes = startHour.getMinute();
        int roundedStartMinutes = (startMinutes / 5) * 5;
        LocalTime roundedStartTime = startHour.withMinute(roundedStartMinutes).withSecond(0).withNano(0);

        while (roundedStartTime.plusMinutes(durationInMinutes).isBefore(endHour.plusMinutes(1))) {
            timeOptions.add(roundedStartTime);
            roundedStartTime = roundedStartTime.plusMinutes(durationInMinutes);
        }
        return timeOptions;
    }

}
