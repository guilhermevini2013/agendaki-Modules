package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Professional;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    void deleteByIdAndInstance(Long id,Instance instance);

    Optional<Professional> findByIdAndInstance(Long id, Instance instance);

    @Query("select p from Professional  p join p.services s where s.id= :idService and p.instance= :instance")
    List<Professional> findByServiceAndInstance(Long idService, Instance instance);

    @Query("select distinct p from Professional p join p.services s where p.instance = :instance")
    List<Professional> findByServiceAndInstance(Instance instance);
}
