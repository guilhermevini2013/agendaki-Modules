package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Scheduling;
import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.repositories.projections.SchedulingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    List<Scheduling> findAllByInstance(Instance instance);

    @Query("select new com.agendaki.scheduling.repositories.projections.SchedulingTime(s.startHour, s.service.durationInMinutes) from Scheduling s where s.instance.keyInstance = :keyInstance and s.date = :date")
    List<SchedulingTime> findAllTimeByInstanceAndDate(@Param("keyInstance") String keyInstance, @Param("date") LocalDate date);
}
