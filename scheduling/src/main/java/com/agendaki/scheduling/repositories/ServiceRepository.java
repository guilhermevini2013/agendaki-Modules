package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
