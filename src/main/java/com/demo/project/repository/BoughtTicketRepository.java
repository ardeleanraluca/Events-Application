package com.demo.project.repository;

import com.demo.project.entity.BoughtTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtTicketRepository extends JpaRepository<BoughtTicketEntity, Long>{
}
