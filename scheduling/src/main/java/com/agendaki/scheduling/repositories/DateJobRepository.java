package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.DateJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateJobRepository extends JpaRepository<DateJob, Long> {
}
