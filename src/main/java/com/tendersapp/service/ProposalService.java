package com.tendersapp.service;

import com.tendersapp.dto.ProposalDTO;
import com.tendersapp.model.Account;
import com.tendersapp.model.Proposal;
import com.tendersapp.model.Tender;
import com.tendersapp.repository.AccountRepository;
import com.tendersapp.repository.ProposalRepository;
import com.tendersapp.repository.TenderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final AccountRepository accountRepository;
    private final TenderRepository tenderRepository;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository,
                           AccountRepository accountRepository,
                           TenderRepository tenderRepository) {
        this.proposalRepository = proposalRepository;
        this.accountRepository = accountRepository;
        this.tenderRepository = tenderRepository;
    }

    @Transactional(readOnly = true)
    public List<ProposalDTO> findAllByTenderId(Integer tenderId) {
        return proposalRepository
                .findAllByTenderId(tenderId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProposalDTO getProposalById(Integer proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new EntityNotFoundException("Proposal не знайдено: " + proposalId));
        return toDTO(proposal);
    }

    @Transactional
    public void submitProposal(ProposalDTO dto) {
        Account creator = accountRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Account не знайдено: " + dto.getCreatorId()));
        Tender tender = tenderRepository.findById(dto.getTenderId())
                .orElseThrow(() -> new EntityNotFoundException("Tender не знайдено: " + dto.getTenderId()));

        Proposal proposal = new Proposal();
        proposal.setCreator(creator);
        proposal.setTender(tender);
        proposal.setPlanLength(dto.getPlanLength());
        proposal.setBudget(dto.getBudget());
        proposal.setDescription(dto.getDescription());

        proposalRepository.save(proposal);
    }

    private ProposalDTO toDTO(Proposal proposal) {
        ProposalDTO dto = new ProposalDTO();
        dto.setId(proposal.getId());
        dto.setCreatorId(proposal.getCreator().getTaxId());
        dto.setCreatorName(proposal.getCreator().getShortName());
        dto.setCreatorTel(proposal.getCreator().getTel());
        dto.setTenderId(proposal.getTender().getId());
        dto.setPlanLength(proposal.getPlanLength());
        dto.setBudget(proposal.getBudget());
        dto.setDescription(proposal.getDescription());
        return dto;
    }
}
