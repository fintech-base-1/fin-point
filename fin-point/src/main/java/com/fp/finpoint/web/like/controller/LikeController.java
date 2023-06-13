package com.fp.finpoint.web.like.controller;

import com.fp.finpoint.domain.like.dto.LikeResponse;
import com.fp.finpoint.domain.like.repository.LikeRepository;
import com.fp.finpoint.domain.like.service.LikeService;
import com.fp.finpoint.domain.member.repository.MemberRepository;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import com.fp.finpoint.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
//    private final InvestService investService;


//    @PostMapping("/{memberId}/like")
//    public void likeInvest(@RequestParam Long id,
//                           @RequestParam String email) {
//
//        likeService.likeInvest(id, email);
//
//    }

    //상품 좋아요
    @PostMapping("/invest/list/detail/like/{invest_id}")
    @ResponseBody
    public boolean like(@PathVariable Long invest_id, HttpServletRequest request) {

        String email = CookieUtil.getEmailToCookie(request);

        //저장 true, 삭제 false
        boolean result = likeService.saveLike(invest_id, email);

        return result;

    }


    //좋아요 상태 체크
    @GetMapping("/like")
    public String getLikeChk(Model model, HttpServletRequest request, Long invest_id) {

        boolean status = false; // 비로그인 유저라면 무조건 status = false;

        String email = CookieUtil.getEmailToCookie(request);
        memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );
        /*
        쿠키로 찾아온 이메일을 토대로 멤버를 찾아오고
        멤버가 없다면 Exception을 터뜨림
        그 멤버의 이메일을 토대로 좋아요 여부를 체크함
         */
//        if (member != null) {
//            // 로그인한 사용자라면
//
//            /* member_id 반환 */
//            //??
//            new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
//        } else {

            /* 현재 로그인한 유저가 이 게시물을 좋아요 했는지 안 했는지 여부 확인 */
            status = likeService.findLike(invest_id, email);
//        }
        System.out.println("status !!!!!!!!!~~~~~~"+status);
        LikeResponse likeResponse = new LikeResponse();
        likeResponse.setStatus(String.valueOf(status));
        model.addAttribute("like", likeResponse);
        log.info("status={}", status);
        return "invest_detail";
    }


}