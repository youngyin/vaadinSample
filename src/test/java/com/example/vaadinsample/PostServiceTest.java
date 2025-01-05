package com.example.vaadinsample;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class PostServiceTest {

    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService();
        postService.init(); // loadPosts 메서드 호출
    }

    @Test
    void testLoadPosts() {
        List<Post> posts = postService.getAllPosts();
        assertNotNull(posts, "Posts should not be null");
        assertFalse(posts.isEmpty(), "Posts list should not be empty");
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = postService.getAllPosts();
        assertEquals("There should be 3 posts", posts.size(), 3);
    }

    @Test
    void testGetPostById_ExistingId() {
        Optional<Post> postOptional = postService.getPostById(1);
        assertTrue(postOptional.isPresent(), "Post with ID 1 should be present");
        assertEquals("Post title should match", postOptional.get().getTitle(), "First Post");
    }

    @Test
    void testGetPostById_NonExistingId() {
        Optional<Post> postOptional = postService.getPostById(999);
        assertFalse(postOptional.isPresent(), "Post with ID 999 should not be present");
    }
}