package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertHolidayDTO;
import com.agendaki.scheduling.repositories.UserRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("HOLIDAY")
public class DateJobHoliday extends DateJob {
    private LocalDate date;

    public DateJobHoliday(InsertHolidayDTO holidayDTO, UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated) {
        super(projectionOfUserEntityAuthenticated.getInstance(), holidayDTO.scheduleInitial(), holidayDTO.scheduleFinal(),
                holidayDTO.breakInitial(), holidayDTO.breakFinal(), holidayDTO.isOpen());
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

    public void update(InsertHolidayDTO insertHolidayDTO) {
        super.breakFinal = insertHolidayDTO.breakFinal();
        super.breakInitial = insertHolidayDTO.breakInitial();
        super.endTime = insertHolidayDTO.scheduleFinal();
        super.startTime = insertHolidayDTO.scheduleInitial();
        super.isOpen = insertHolidayDTO.isOpen();
        this.date = insertHolidayDTO.dateOfHoliday();
    }
}
