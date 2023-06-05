package com.fp.finpoint.web.invest.controller;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.service.InvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/invest")
@Controller
@RequiredArgsConstructor // DI 주입. (InvestService)
public class InvestController {

    private final InvestService investService;

    @GetMapping("/list")
    public String list (Model model) {
        List<Invest> investList = this.investService.getList();
        model.addAttribute("investList", investList);
        return "invest_list";
    }

    @GetMapping("/create")
    public String listCreate(InvestDto investDto) {
        return "invest_create";
    }

    @PostMapping("/create")
    public String listCreate(Model model, InvestDto investDto) {
        this.investService.create(investDto.getSubject(), investDto.getContent());
        return "redirect:/invest/list";
    }

}
