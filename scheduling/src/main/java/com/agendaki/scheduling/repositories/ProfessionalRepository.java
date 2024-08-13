package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Professional;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    void deleteByIdAndInstance(Long id,Instance instance);

    Optional<Professional> findByIdAndInstance(Long id, Instance instance);
}
