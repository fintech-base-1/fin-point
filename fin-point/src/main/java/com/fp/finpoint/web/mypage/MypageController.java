package com.fp.finpoint.web.mypage;

import com.fp.finpoint.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;

    @GetMapping("/mypage")
    public String mailConfirm(Model model)
    {
        MypageDto mypageDto = new MypageDto();
        mypageDto.setFinpoint(47000L);
        mypageDto.setPieceCnt(27L);
        mypageDto.setPieceKind(5L);
        mypageDto.setPiecePrice(570000L);

        model.addAttribute("mypageDto",mypageDto);
        return "myPage";
    }




}
