package com.example.my_blog.domain.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@DisplayName("회원 엔티티")
class UserTest {

  @Autowired
  EntityManager em;

  private User user1;

  @BeforeEach
  void setUp() {
    // given
    user1 = User.createUser("loginId1", "password1", "name1", "nickname1");
    em.flush();
    em.clear();

    em.persist(user1);
  }

  @Test
  @DisplayName("를 생성한다.")
  void createUser() {
    //when
    User findUser = em.find(User.class, user1.getId());

    //then
    Assertions.assertThat(user1).isEqualTo(findUser);
  }
}