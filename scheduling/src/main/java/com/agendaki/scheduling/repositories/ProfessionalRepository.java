package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

}
