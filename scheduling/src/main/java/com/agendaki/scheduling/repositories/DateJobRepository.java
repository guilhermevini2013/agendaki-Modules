package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.DateJob;
import com.agendaki.scheduling.models.scheduling.DateJobHoliday;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DateJobRepository extends JpaRepository<DateJob, Long> {

    @Query("SELECT CASE WHEN (COUNT(dh) > 0) THEN TRUE ELSE FALSE END FROM DateJobHoliday dh WHERE dh.instance = :instance AND dh.date = :date")
    boolean existsByDate(Instance instance, @Param("date") LocalDate date);

    @Query("select dc from DateJobCommon dc where dc.instance= :instance AND dc.dayOfWeek= :dayOfWeek")
    Optional<DateJob> findByInstanceAndDayOfWeek(Instance instance, DayOfWeek dayOfWeek);

    @Query("select dc from DateJobCommon dc where dc.instance= :instance")
    List<DateJob> findDateJobCommonByInstance(Instance instance);

    @Query("select dh from DateJobHoliday dh where dh.instance = :instance")
    Set<DateJob> findDateJobHolidayByInstance(Instance instance);

    void deleteByIdAndInstance(Long id, Instance instance);

    @Query("select dh from DateJobHoliday dh where dh.id= :id and dh.instance= :instance")
    Optional<DateJobHoliday> getDateHolidayByidAndInstance(Long id, Instance instance);

    @Query("select dc from DateJobCommon dc where dc.instance.keyInstance= :keyInstance and dc.dayOfWeek= :dayOfWeek")
    Optional<DateJob> findDateJobCommonByKeyInstance(String keyInstance, DayOfWeek dayOfWeek);
}
