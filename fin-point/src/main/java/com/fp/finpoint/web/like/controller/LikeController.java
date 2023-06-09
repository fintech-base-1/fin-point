package com.fp.finpoint.web.like.controller;

import com.fp.finpoint.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;


    @PostMapping("/{memberId}/like")
    public void likeInvest(@RequestParam Long id,
                           @RequestParam String email){

         likeService.goodInvest(id,email);
    }
}
