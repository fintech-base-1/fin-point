package com.fp.finpoint.web.mypage;

import com.fp.finpoint.domain.file.service.FileService;
import com.fp.finpoint.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("/finpoint/mypage")
public class MypageController {

    private final FileService fileService;
    private final MemberService memberService;

    @GetMapping("")
    public String myPage(Model model, HttpServletRequest request){
        model.addAttribute("mypageDto",memberService.getMypageInfo(request));
        return "user/mypage/mypage";
    }

    @ResponseBody
    @GetMapping("/image")
    public Resource image(HttpServletRequest request) throws MalformedURLException {
     return fileService.getImageUrl(request);
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> uploadFile(@RequestParam("profileImage") MultipartFile file,
                                        HttpServletRequest request) throws IOException {
        fileService.saveFile(file, request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "프로필 이미지 업데이트 성공");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public String profile(){
        return "user/mypage/profile";
    }

    @GetMapping("/reset-goal")
    public String reset(){
        return "user/mypage/reset-goal";
    }
    @PostMapping("/goal")
    public ResponseEntity<Map<String ,String>> goal(@RequestBody MypageDto mypageDto, HttpServletRequest request){
        memberService.saveGoal(mypageDto.getGoal(),request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "목표금액 재설정 완료");
        return ResponseEntity.ok(response);
    }

}
