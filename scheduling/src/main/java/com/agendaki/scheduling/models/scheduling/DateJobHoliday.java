package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.DateJobHolidayDTO;
import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.repositories.UserRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("HOLIDAY")
public class DateJobHoliday extends DateJob {
    private Boolean isOpen;
    private LocalDate date;

    public DateJobHoliday(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO, UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated) {
        super(projectionOfUserEntityAuthenticated.getInstance(), insertDateOfSchedulingDTO.scheduleInitial(), insertDateOfSchedulingDTO.scheduleFinal(),
                insertDateOfSchedulingDTO.breakInitial(), insertDateOfSchedulingDTO.breakFinal());
        DateJobHolidayDTO dateJobHolidayDTO = insertDateOfSchedulingDTO.dateJobHoliday().get();
        this.isOpen = dateJobHolidayDTO.isOpen();
        this.date = dateJobHolidayDTO.dateOfHoliday();
    }

    public DateJobHoliday() {

    }
}
