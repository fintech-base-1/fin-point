package com.fp.finpoint.domain.invest.service;

import com.fp.finpoint.domain.invest.entity.Board;
import com.fp.finpoint.domain.invest.entity.BoardDto;
import com.fp.finpoint.domain.invest.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //필드 생성자 자동으로 만들어줌, 의존성 주입
public class BoardService {


    private final BoardRepository boardRepository;

    public void write(BoardDto boardDto){

        boardRepository.save(boardDto.toEntity());
    }

    public void save(BoardDto boardDto){

        boardRepository.save(boardDto.toEntity()).getId();
    }

    public void update(BoardDto boardDto){

        boardRepository.save(boardDto.toEntity());
    }

    //게시글 리스트 처리
    public List<Board> boardList(){
        return  boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    public Page<Board> boardList(Pageable pageable){
        return  boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String SearchKeyword,Pageable pageable){
        return boardRepository.findByTitleContaining(SearchKeyword, pageable);
    }
}
