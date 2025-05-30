package com.tendersapp.service;

import com.tendersapp.dto.TenderDTO;
import com.tendersapp.model.Account;
import com.tendersapp.model.Status;
import com.tendersapp.model.Tender;
import com.tendersapp.repository.AccountRepository;
import com.tendersapp.repository.TenderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderService {

    private final TenderRepository tenderRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository,
                         AccountRepository accountRepository) {
        this.tenderRepository = tenderRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public TenderDTO getTenderById(int id) {
        Tender tender = tenderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tender не знайдено: " + id));
        return toDTO(tender);
    }

    @Transactional
    public void createTender(TenderDTO dto) {
        Account creator = accountRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Account не знайдено: " + dto.getCreatorId()));

        Tender tender = new Tender();
        tender.setTitle(dto.getTitle());
        tender.setDescription(dto.getDescription());
        tender.setStatus(Status.OPEN);
        tender.setBudget(dto.getBudget());
        tender.setDeadline(dto.getDeadline());
        tender.setRegion(dto.getRegion());
        tender.setCity(dto.getCity());
        tender.setAddress(dto.getAddress());
        tender.setCreator(creator);
        if (dto.getWinnerId() != null) {
            Account winner = accountRepository.findById(dto.getWinnerId())
                    .orElseThrow(() -> new EntityNotFoundException("Account (winner) не знайдено: " + dto.getWinnerId()));
            tender.setWinner(winner);
        }
        tenderRepository.save(tender);
    }

    @Transactional
    public void updateStatus(int tenderId, Status status) {
        Tender tender = tenderRepository.findById(tenderId)
                .orElseThrow(() -> new EntityNotFoundException("Tender не знайдено: " + tenderId));
        tender.setStatus(status);
        tenderRepository.save(tender);
    }

    // Видалення тендера
    @Transactional
    public void deleteTender(int tenderId) {
        if (!tenderRepository.existsById(tenderId)) {
            throw new EntityNotFoundException("Tender не знайдено: " + tenderId);
        }
        tenderRepository.deleteById(tenderId);
    }

    // Додатково: отримати всі тендери як DTO (для списку)
    @Transactional(readOnly = true)
    public List<TenderDTO> findAll() {
        return tenderRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TenderDTO> search(String query) {
        return tenderRepository.search(query).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    // Конвертація сутності в DTO
    private TenderDTO toDTO(Tender tender) {
        TenderDTO dto = new TenderDTO();
        dto.setId(tender.getId());
        dto.setTitle(tender.getTitle());
        dto.setDescription(tender.getDescription());
        dto.setStatus(tender.getStatus());
        dto.setBudget(tender.getBudget());
        dto.setDeadline(tender.getDeadline());
        dto.setRegion(tender.getRegion());
        dto.setCity(tender.getCity());
        dto.setAddress(tender.getAddress());
        dto.setCreatorId(tender.getCreator().getTaxId());
        dto.setCreatorName(tender.getCreator().getShortName());
        dto.setCreatorTel(tender.getCreator().getTel());
        if (tender.getCreator().getTel2() != null) {
            dto.setCreatorTel2(tender.getCreator().getTel2());
        }
        if (tender.getWinner() != null) {
            dto.setWinnerId(tender.getWinner().getTaxId());
        }
        return dto;
    }
}