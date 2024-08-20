package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByInstance(Instance instance);
}
