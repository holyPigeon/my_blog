package com.example.my_blog;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.comment.service.CommentService;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

  private final InitService initService;

  /**
   * 애플리케이션 실행 전에 미리 init() 실행
   */
  @PostConstruct
  public void init() {
    initService.dbInit();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  @Slf4j
  static class InitService {

    private final EntityManager em;
    private final CommentService commentService;

    public void dbInit() {

      User user1 = createUser("admin", "0000", "admin", "Admin");
      User user2 = createUser("test1", "test1", "test1", "中村 あげは");
      User user3 = createUser("test2", "test2", "test2", "조병찬");
      em.persist(user1);
      em.persist(user2);
      em.persist(user3);

      Post post1 = createPost(user1, "테스트 게시물", "안녕하세요? 포럼 테스트용 게시물입니다. 포럼은 챗봇으로 해결되지 않는 문제를 집단지성으로 해결할 수 있는 공간입니다.");
      Post post2 = createPost(user2, "I'm from japan", "頭がとても痛いのですが、どこに行けばいいですか?");
      em.persist(post1);
      em.persist(post2);

      Comment comment1 = createComment(user2, post1, "こんにちは");
      Comment comment2 = createComment(user3, post1, "반갑습니다.");
      Comment replyComment1 = createComment(user1, post1, "반가워요~~~");
      Comment replyComment2 = createComment(user3, post1, "Nice to meet you.");
      em.persist(comment1);
      em.persist(comment2);
      commentService.saveReplyComment(comment1.getId(), replyComment1);
      commentService.saveReplyComment(comment1.getId(), replyComment2);

      em.flush();
    }

    private User createUser(String loginId, String password, String name, String nickname) {

      return User.createUser(loginId, password, name, nickname);
    }

    private Post createPost(User user, String title,String content) {

      return Post.createPost(user, title, content);
    }

    private Comment createComment(User user, Post post, String content) {

      return Comment.createComment(user, post, content);
    }

  }
}
