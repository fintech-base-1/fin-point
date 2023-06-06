package com.fp.finpoint.web.invest.controller;

import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.entity.InvestDto;
import com.fp.finpoint.domain.invest.service.InvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/invest")
@Controller
@RequiredArgsConstructor // DI 주입. (InvestService)
public class InvestController {

    private final InvestService investService;

    //리스트+페이징+검색
    @GetMapping("/list")
    public String list (Model model,
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        String searchKeyword) {
        Page<Invest> list = null;

        if(searchKeyword == null) {
            list = investService.investList(pageable);
        }else {
            list = investService.investSearchList(searchKeyword, pageable);
        }
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "invest_list";
    }

    //글 작성
    @GetMapping("/create")
    public String listCreate(InvestDto investDto) {

        return "invest_create";
    }

    //작성 후 이동
    @PostMapping("/create")
    public String listCreate(Model model, InvestDto investDto) {
        this.investService.save(investDto);
        return "redirect:/invest/list";
    }

    // 디테일 페이지
    @GetMapping(value = "/list/detail/{id}")
    public String detail(Model model, InvestDto investDto, @PathVariable Long id) {
//        this.investService.getInvestListDetail(id);
        model.addAttribute("investDto", investService.getInvestListDetail(id)); // model 을 통해 view(화면)에서 사용 가능하게 함.
        return "invest_detail";
    }

    // Invest 게시글 삭제.
    @GetMapping("/delete")
    public String Delete(Long id){
        investService.deleteInvest(id);

        return "redirect:/invest_list";
    }

    //글 수정
    @GetMapping("/update/{id}")
    public String Update(@PathVariable("id") long id , Model model){
        System.out.println("수정test");
        model.addAttribute("invest",investService.getInvestListDetail(id));

        return "update";
    }

}
