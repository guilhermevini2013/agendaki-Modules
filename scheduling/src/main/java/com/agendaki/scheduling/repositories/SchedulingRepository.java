package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Scheduling;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    List<Scheduling> findAllByInstance(Instance instance);
}
