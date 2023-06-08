package com.fp.finpoint.web.like;

import com.fp.finpoint.domain.like.LikeResponse;
import com.fp.finpoint.domain.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;


    @PostMapping("/")
    public void likeInvest(@RequestParam Long id,
                           @RequestParam String email){

         likeService.goodInvest(id,email);
    }
}
