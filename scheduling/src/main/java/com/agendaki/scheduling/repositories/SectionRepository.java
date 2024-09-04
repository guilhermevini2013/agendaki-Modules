package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.template.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
