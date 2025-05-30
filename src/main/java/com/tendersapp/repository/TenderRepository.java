package com.tendersapp.repository;

import com.tendersapp.model.Tender;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TenderRepository extends JpaRepository<Tender, Integer> {
    Optional<Tender> findByTitle(String title);
    @NotNull Optional<Tender> findById(@NotNull Integer id);

    @Query("SELECT t FROM Tender t WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.city) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(CAST(t.region as string)) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Tender> search(@Param("query") String query);
}