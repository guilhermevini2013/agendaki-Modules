package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.scheduling.Service;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByInstance(Instance instance);

    @Query("select s from Service s join s.professionals p where p.id = :professionalId and s.id = :serviceId")
    Optional<Service> findByIdProfessional(Long professionalId, Long serviceId);

    @Modifying
    @Query(value = "BEGIN TRANSACTION; " +
            "DELETE FROM professional_services WHERE services_id = :serviceId; " +
            "DELETE FROM service WHERE id = :serviceId AND instance_id = :instanceId; " +
            "COMMIT;", nativeQuery = true)
    void removeServiceAndAssociations(@Param("serviceId") Long serviceId, @Param("instanceId") Long instanceId);
}
