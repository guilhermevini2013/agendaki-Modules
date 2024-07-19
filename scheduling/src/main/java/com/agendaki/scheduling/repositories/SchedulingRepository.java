package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
