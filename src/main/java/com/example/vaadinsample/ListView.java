package com.example.vaadinsample;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("list")
@CssImport("./styles.css")
public class ListView extends VerticalLayout {

    private final PostService postService;

    @Autowired
    public ListView(PostService postService){
        this.postService = postService;
        initView();
    }

    private void initView() {
        List<Post> posts = postService.getAllPosts();
        posts.forEach(this::initListItem);
    }

    private void initListItem(Post post) {
        // 카드 컨테이너
        Div card = new Div();
        card.addClassName("card"); // CSS 클래스 적용

        // 제목과 내용 컨테이너
        Div contentContainer = new Div();
        contentContainer.addClassName("content-container");

        Div title = new Div();
        title.setText(post.getTitle());
        title.addClassName("card-title");

        Div contentSnippet = new Div();
        contentSnippet.setText(post.getContent().substring(0, Math.min(50, post.getContent().length())) + "...");
        contentSnippet.addClassName("card-content");

        // 버튼
        Button detailButton = new Button("View Details", event ->
                getUI().ifPresent(ui -> ui.navigate("detail/" + post.getId()))
        );
        detailButton.addClassName("view-details-button"); // CSS 클래스 적용

        // 제목과 내용을 contentContainer에 추가
        contentContainer.add(title, contentSnippet);

        // 카드에 제목/내용 컨테이너와 버튼 추가
        card.add(contentContainer, detailButton);
        add(card);
    }

}
