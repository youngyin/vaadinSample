package com.example.vaadinsample;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route("detail")
@CssImport("./styles.css")
public class DetailView extends VerticalLayout implements HasUrlParameter<Integer> {
    private final PostService postService;

    @Autowired
    public DetailView(PostService postService) {
        this.postService = postService;
        setPadding(true);
        setSpacing(true);
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, Integer postId) {
        Optional<Post> postOptional = postService.getPostById(postId);

        if (postOptional.isPresent()) {
            initPresentView(postOptional.get());

        } else {
            initNotPresentView();
            
        }
    }

    void initPresentView(Post post) {
        // 제목 표시
        Div title = new Div();
        title.setText(post.getTitle());
        title.addClassName("detail-title"); // CSS 클래스 적용

        // 내용 표시
        Div content = new Div();
        content.setText(post.getContent());
        content.addClassName("detail-content"); // CSS 클래스 적용

        // Back to List 버튼
        Button backButton = new Button("Back to List", e ->
                getUI().ifPresent(ui -> ui.navigate("list"))
        );
        backButton.addClassName("back-button"); // CSS 클래스 적용

        // 컴포넌트 추가
        add(title, content, backButton);
    }

    void initNotPresentView(){
        // 게시글이 존재하지 않는 경우
        Div errorMessage = new Div();
        errorMessage.setText("Post not found.");
        errorMessage.getStyle().set("color", "red").set("font-size", "18px");

        Button backButton = new Button("Back to List", e ->
                getUI().ifPresent(ui -> ui.navigate("list"))
        );

        add(errorMessage, backButton);
    }
}
