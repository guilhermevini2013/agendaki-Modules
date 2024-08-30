package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.template.Template;
import com.agendaki.scheduling.models.user.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    Optional<Template> findByInstance(Instance instance);
}
