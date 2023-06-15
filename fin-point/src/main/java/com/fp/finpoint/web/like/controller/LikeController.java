package com.fp.finpoint.web.like.controller;

import com.fp.finpoint.domain.like.service.LikeService;
import com.fp.finpoint.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    //상품 좋아요
    @PostMapping("/invest/list/detail/like/{invest_id}")
    @ResponseBody
    public boolean like(@PathVariable Long invest_id, HttpServletRequest request) {

        String email = CookieUtil.getEmailToCookie(request);

        //저장 true, 삭제 false
        boolean result = likeService.saveLike(invest_id, email);

        return result;

    }





}