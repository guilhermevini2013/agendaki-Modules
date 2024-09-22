package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.template.Input;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input, Long> {

    @Query("SELECT i.label FROM Input i WHERE i.template.instance = :instance")
    List<String> findAllLabelsByInstance(Instance instance);
}
