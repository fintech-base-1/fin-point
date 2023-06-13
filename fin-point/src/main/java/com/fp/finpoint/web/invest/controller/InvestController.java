package com.fp.finpoint.web.invest.controller;

import com.fp.finpoint.domain.file.service.FileService;
import com.fp.finpoint.domain.file.service.InvestFileService;
import com.fp.finpoint.domain.invest.dto.InvestDto;
import com.fp.finpoint.domain.invest.entity.Invest;
import com.fp.finpoint.domain.invest.repository.InvestRepository;
import com.fp.finpoint.domain.invest.service.InvestService;
import com.fp.finpoint.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/invest")
@Controller
@RequiredArgsConstructor // DI 주입. (InvestService)
public class InvestController {

    private final InvestService investService; // 롬복 생성자 빈 주입방식 @Autowired
//    private final FileService fileService;
    private final InvestFileService investFileService;

    // 전체 리스트 페이지.
    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       String searchKeyword) {
        Page<Invest> list = null;

        if (searchKeyword == null) {
            list = investService.investList(pageable);
        } else {
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

    // 디테일 페이지.
    @GetMapping(value = "/list/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Invest readInvestDetail = this.investService.readInvestDetail(id);
        model.addAttribute("investDetail", readInvestDetail); // model 을 통해 view(화면)에서 사용 가능하게 함.

        return "invest_detail";
    }

    // 글 작성.
    @Value("${file.dir}") // @Value 는 application.yml 파일에 입력되어있는 값을 가져올수 있다.
    private String fileDir;

    @GetMapping("/create")
    public String listCreate(InvestDto investDto) {
        return "invest_create";
    }

    @PostMapping("/create")
    public String listCreate(@ModelAttribute InvestDto investDto,
                             @RequestParam MultipartFile file,
                             HttpServletRequest request) throws IOException {
        // 업로드하는 html form 의 name 에 맞추어 @RequestParam 을 적용하면 된다. 추가로 @ModelAttribute 에서도 MultipartFile 을 동일하게 사용할 수 있다.
//        log.info("request={}", request);
//        log.info("itemName={}", itemName);
//        log.info("multipartFile={}", file);

//        if (!file.isEmpty()) {
//
//        }
        Long fileEntity = investFileService.saveFile(file);
        System.out.println("fileId : "+fileEntity);
        //TODO: 이미지 저장하는 로직에 대한 리펙토링 필요 (성능적인 이유)
        log.info("파일 저장 fullPath={}", file);
        String email = CookieUtil.getEmailToCookie(request);
        investService.create(investDto, email, fileEntity);
        return "redirect:/invest/list";
    }

    // 글 삭제.
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        investService.deleteInvest(id);

        return "redirect:/invest/list";
    }


    // 글 수정.
    @GetMapping("/modify/{id}")
    public String modifyInvest(@PathVariable("id") Long id, Model model) {
        Invest modifyInvest = investService.readInvestDetail(id);
        model.addAttribute("modify", modifyInvest);

        return "invest_modify";
    }

    @PostMapping("/update/{id}")
    public String investUpdate(@PathVariable("id") Long id, Model model, InvestDto investDto) {

        this.investService.updateInvest(investDto);

        model.addAttribute("message", "수정 완료");
        model.addAttribute("SearchUrl", "/invest/list");

        return "Message";
    }


    @PostMapping("/test")
    @ResponseBody
    public String test(@RequestParam(name = "id") Long id, HttpServletRequest request, @RequestParam(name = "count") Long count) {
        //todo: pathVariable 이용해 invest_id 를 가져올 예정 일단 테스트를 위해 만든 api 로 requestparam 으로 받아서 테스트했음
        investService.purchase(id, request, count);
        return "success";
    }

}

