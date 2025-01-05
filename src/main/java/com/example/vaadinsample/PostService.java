package com.example.vaadinsample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private List<Post> posts;

    @PostConstruct
    public void init(){
        loadPosts();
    }

    // JSON 파일에서 게시글 데이터를 읽어오는 메서드
    private void loadPosts() {
        try (InputStream input = getClass().getResourceAsStream("/posts.json");
             BufferedInputStream bufferedInput = new BufferedInputStream(input)) {

            ObjectMapper mapper = new ObjectMapper();
            posts = mapper.readValue(bufferedInput, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 모든 게시글 목록을 반환
    public List<Post> getAllPosts() {
        return posts;
    }

    // ID로 특정 게시글을 반환
    public Optional<Post> getPostById(int id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst();
    }
}
