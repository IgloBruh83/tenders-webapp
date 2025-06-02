package com.tendersapp.repository;

import com.tendersapp.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
    Optional<Proposal> findById(Integer id);
    List<Proposal> findAllByTenderId(Integer tenderId);
}