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
        List<Invest> investList = this.investService.getInvestList();
        model.addAttribute("investList", investList);
        return "invest_list";
    }

    @GetMapping("/create")
    public String listCreate(InvestDto investDto) {
        return "invest_create";
    }

    @PostMapping("/create")
    public String listCreate(Model model, InvestDto investDto) {
        this.investService.create(investDto.getSubject(), investDto.getContent(), investDto.getId());
        return "redirect:/invest/list";
    }

    // 디테일 페이지
    @GetMapping(value = "/list/detail/{id}")
    public String detail(Model model, InvestDto investDto, @PathVariable Long id) {
//        this.investService.getInvestListDetail(id);
        model.addAttribute("investDto", investDto); // model 을 통해 view(화면)에서 사용 가능하게 함.
        return "invest_detail";
    }

    // Invest 게시글 삭제.
    @GetMapping("/delete")
    public String boardDelete(Long id){
        investService.deleteInvest(id);

        return "redirect:/invest_list";
    }

}
