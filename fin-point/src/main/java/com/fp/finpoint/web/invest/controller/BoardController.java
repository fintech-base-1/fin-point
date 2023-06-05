package com.fp.finpoint.web.invest.controller;

import com.fp.finpoint.domain.invest.entity.Board;
import com.fp.finpoint.domain.invest.entity.BoardDto;
import com.fp.finpoint.domain.invest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // DI 주입.
public class BoardController {


    private final BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardwriteForm(){

        return "write";
    }

    @PostMapping("/board/write")//상세 페이지 안되서 리스트로 이동
    public String boardWritePro(BoardDto boardDto){

        boardService.save(boardDto);

        return "redirect:/board/list";
    }

//    @GetMapping("/board/list")
//    public String boardList(Model model){
//        System.out.println(boardService.boardList());
//        model.addAttribute("list",boardService.boardList());
//        return "list";
//    }

    @GetMapping("/board/view")
    public String boardView(Model model , Integer id){

        model.addAttribute("board",boardService.boardView(id));
        return "view";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id , Model model){
        System.out.println("수정test");
        model.addAttribute("board",boardService.boardView(id));

        return "invest_modify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id , Board board , Model model, BoardDto boardDto){

//        Board boardTemp = boardService.boardView(id);
//        boardTemp.setTitle(board.getTitle());
//        boardTemp.setContent(board.getContent());

        this.boardService.update(boardDto);

        model.addAttribute("message" , "수정 완료.");
        model.addAttribute("SearchUrl" , "/board/list");

        // 절대 이렇게 하면 안되고 Jpa에서 제공하는 변경감지나 Merge 기능을 따로 공부하자.
        //boardService.write(boardTemp);

        return "Message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "list";

    }


}
