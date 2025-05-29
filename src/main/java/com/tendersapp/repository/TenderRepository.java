package com.tendersapp.repository;

import com.tendersapp.model.Account;
import com.tendersapp.model.Tender;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TenderRepository extends JpaRepository<Tender, Integer> {
    Optional<Tender> findByTitle(String title);
    @NotNull Optional<Tender> findById(@NotNull Integer id);
}