package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.DateJob;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DateJobRepository extends JpaRepository<DateJob, Long> {

    @Query("SELECT CASE WHEN (COUNT(dh) > 0) THEN TRUE ELSE FALSE END FROM DateJobHoliday dh WHERE dh.instance = :instance AND dh.date = :date")
    boolean existsByDate(Instance instance, @Param("date") LocalDate date);

    @Query("select d from DateJobCommon d where d.instance= :instance AND d.dayOfWeek= :dayOfWeek")
    Optional<DateJob> findByInstanceAndDayOfWeek(Instance instance, DayOfWeek dayOfWeek);
}