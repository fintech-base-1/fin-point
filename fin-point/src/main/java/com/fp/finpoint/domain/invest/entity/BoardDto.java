package com.fp.finpoint.domain.invest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    public String title; //제목
    public String content; //내용
    public String name; //작성자
    public Integer id; //글 번호
    


    public Board toEntity(){
       Board board = new Board(this.title, this.content, this.name, this.id);
       return board;
   }







}
