package com.agendaki.scheduling.models.scheduling;

import com.agendaki.scheduling.dtos.request.InsertDateOfSchedulingDTO;
import com.agendaki.scheduling.dtos.response.ReadDateOfSchedulingDTO;
import com.agendaki.scheduling.repositories.UserRepository;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.DayOfWeek;

@Entity
@DiscriminatorValue("COMMON")
public class DateJobCommon extends DateJob {
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    public DateJobCommon(InsertDateOfSchedulingDTO dateOfSchedulingDTO, UserRepository.UserAuthProjection projectionOfUserEntityAuthenticated) {
        super(projectionOfUserEntityAuthenticated.getInstance(), dateOfSchedulingDTO.scheduleInitial(), dateOfSchedulingDTO.scheduleFinal(),
                dateOfSchedulingDTO.breakInitial(), dateOfSchedulingDTO.breakFinal());
        this.dayOfWeek = dateOfSchedulingDTO.dayOfWeek();
    }

    public DateJobCommon() {

    }
    public void update(InsertDateOfSchedulingDTO insertDateOfSchedulingDTO) {
        super.breakInitial = insertDateOfSchedulingDTO.breakInitial();
        super.breakFinal = insertDateOfSchedulingDTO.breakFinal();
        super.endTime = insertDateOfSchedulingDTO.scheduleFinal();
        super.startTime = insertDateOfSchedulingDTO.scheduleInitial();
        this.dayOfWeek = insertDateOfSchedulingDTO.dayOfWeek();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
