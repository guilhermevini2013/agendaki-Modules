package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.repositories.UserRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("HOLIDAY")
public class DateJobHoliday extends DateJob {
    private Boolean isOpen;
    private LocalDate date;

    public DateJobHoliday(InsertHolidayDTO holidayDTO, UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated) {
        super(projectionOfUserEntityAuthenticated.getInstance(), holidayDTO.scheduleInitial(), holidayDTO.scheduleFinal(),
                holidayDTO.breakInitial(), holidayDTO.breakFinal());
        this.isOpen = holidayDTO.isOpen();
        this.date = holidayDTO.dateOfHoliday();
    }

    public DateJobHoliday() {

    }

    public Boolean getOpen() {
        return isOpen;
    }

    public LocalDate getDate() {
        return date;
    }
}
