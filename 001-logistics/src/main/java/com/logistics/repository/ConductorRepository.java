package com.logistics.repository;

import com.logistics.model.ConductorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepository extends JpaRepository<ConductorModel, Long> {
}

