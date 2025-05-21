package com.tendersapp.repository;

import com.tendersapp.model.Account;
import com.tendersapp.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TenderRepository extends JpaRepository<Tender, String> {
    Optional<Tender> findByTitle(String title);
}