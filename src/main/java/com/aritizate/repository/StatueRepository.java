package com.aritizate.repository;

import com.aritizate.model.Statue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatueRepository extends JpaRepository<Statue, Long> {
}

