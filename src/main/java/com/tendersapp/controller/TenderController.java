package com.tendersapp.controller;

import com.tendersapp.dto.ProposalDTO;
import com.tendersapp.dto.TenderDTO;
import com.tendersapp.model.Account;
import com.tendersapp.model.Proposal;
import com.tendersapp.model.Region;
import com.tendersapp.model.Status;
import com.tendersapp.repository.AccountRepository;
import com.tendersapp.repository.ProposalRepository;
import com.tendersapp.repository.TenderRepository;
import com.tendersapp.service.ProposalService;
import com.tendersapp.service.TenderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Controller
@RequestMapping("/tenders")
public class TenderController {

    private final TenderService tenderService;
    private final ProposalService proposalService;
    private final AccountRepository accountRepository;

    @Autowired
    public TenderController(TenderService tenderService,
                            ProposalService proposalService,
                            AccountRepository accountRepository) {
        this.tenderService = tenderService;
        this.proposalService = proposalService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/{id}")
    public String showTenderDetails(@PathVariable("id") int id, Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("user");
        TenderDTO tenderDTO = tenderService.getTenderById(id);

        if (account != null) {
            model.addAttribute("isOwner", account.getTaxId().equals( tenderDTO.getCreatorId() ));
        } else {
            model.addAttribute("isOwner", false);
        }
        model.addAttribute("tender", tenderDTO);
        if (tenderDTO.getStatus() == Status.ENDED && tenderDTO.getWinnerId() != null) {
            ProposalDTO proposal = proposalService.getProposalById(tenderDTO.getWinnerId());
            model.addAttribute("winner", proposal);
        }

        List<ProposalDTO> proposals = proposalService.findAllByTenderId(id);
        model.addAttribute("proposals", proposals);

        return "tender";
    }

    @GetMapping("/search")
    public String searchTenders(@RequestParam("q") String query, Model model) {
        List<TenderDTO> results = tenderService.search(query);
        model.addAttribute("tenders", results);
        model.addAttribute("query", query);
        return "index";
    }

    // Відкриття сторінки створення тендера
    @GetMapping("/new")
    public String showCreateTenderForm(Model model, HttpSession session) {
        model.addAttribute("regions", Region.values());
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }

        model.addAttribute("tenderDTO", new TenderDTO());
        model.addAttribute("accounts", accountRepository.findAll());
        return "tenderCreator";
    }

    // Створення тендера
    @PostMapping
    @Transactional
    public String createTender(
            @Valid @ModelAttribute("tenderDTO") TenderDTO tenderDTO,
            BindingResult result,
            @RequestParam("localDateTime") String localDateTimeStr,
            @RequestParam("zoneId") String zoneIdStr,
            Model model,
            HttpSession session
    ) {
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            System.out.println("VALIDATION FAILED:");
            result.getFieldErrors().forEach(error ->
                    System.out.println("Field: " + error.getField() + ", Error: " + error.getDefaultMessage())
            );

            model.addAttribute("accounts", accountRepository.findAll());
            return "tenderCreator";
        }
        Account currentUser = (Account) session.getAttribute("user");
        tenderDTO.setCreatorId(currentUser.getTaxId());
        tenderDTO.setCreatorName(currentUser.getShortName());
        tenderDTO.setCreatorTel(currentUser.getTel());
        if (currentUser.getTel2() != null) {
            tenderDTO.setCreatorTel2(currentUser.getTel2());
        }
        LocalDateTime ldt = LocalDateTime.parse(localDateTimeStr);
        ZonedDateTime deadline = ldt.atZone(ZoneId.of(zoneIdStr));
        tenderDTO.setDeadline(deadline);

        tenderService.createTender(tenderDTO);
        return "redirect:/";
    }

    @GetMapping("/{id}/status")
    @Transactional
    public String toggleStatus(
            @PathVariable("id") int tenderId,
            HttpSession session
    ) {
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }

        TenderDTO tenderDTO = tenderService.getTenderById(tenderId);
        Status current = tenderDTO.getStatus();
        if (current != Status.ENDED) {
            Status next;
            if (current == Status.OPEN) {
                next = Status.CLOSED;
            } else {
                next = Status.OPEN;
            }
            tenderService.updateStatus(tenderId, next);
        }

        return "redirect:/tenders/" + tenderId;
    }

    // Відкриття сторінки створення пропозиції
    @GetMapping("/{tenderId}/proposals/new")
    public String showCreateProposalForm(
            @PathVariable("tenderId") int tenderId,
            Model model, HttpSession session
    ) {
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }

        ProposalDTO proposalDTO = new ProposalDTO();
        proposalDTO.setTenderId(tenderId);
        model.addAttribute("proposalDTO", proposalDTO);
        model.addAttribute("accounts", accountRepository.findAll());
        return "proposalCreator";  // шаблон src/main/resources/templates/proposals/new.html
    }

    @GetMapping("/{id}/winner/{proposalId}")
    @Transactional
    public String assignWinner(
            @PathVariable("id") Integer tenderId,
            @PathVariable("proposalId") Integer proposalId,
            HttpSession session
    ) {
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }
        TenderDTO tenderDTO = tenderService.getTenderById(tenderId);
        if (!account.getTaxId().equals(tenderDTO.getCreatorId())) {
            return "redirect:/tenders/" + tenderId;
        }

        tenderService.setWinner(tenderId, proposalId);
        return "redirect:/tenders/" + tenderId;
    }

    // Створення пропозиції
    @PostMapping("/{tenderId}/proposals")
    @Transactional
    public String submitProposal(
            @PathVariable("tenderId") int tenderId,
            @Valid @ModelAttribute("proposalDTO") ProposalDTO proposalDTO,
            BindingResult result,
            Model model, HttpSession session
    ) {

        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            System.out.println("VALIDATION FAILED:");
            result.getFieldErrors().forEach(error ->
                    System.out.println("Field: " + error.getField() + ", Error: " + error.getDefaultMessage())
            );

            model.addAttribute("accounts", accountRepository.findAll());
            return "proposalCreator";
        }
        proposalDTO.setTenderId(tenderId);
        proposalDTO.setCreatorId(account.getTaxId());
        proposalService.submitProposal(proposalDTO);
        return "redirect:/tenders/" + tenderId;
    }
}