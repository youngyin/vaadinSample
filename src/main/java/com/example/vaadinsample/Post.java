package com.example.vaadinsample;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {
    private int id;           // 게시글 ID
    private String title;     // 게시글 제목
    private String content;   // 게시글 내용
}
