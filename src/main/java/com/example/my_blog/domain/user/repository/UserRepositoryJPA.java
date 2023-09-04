package com.example.my_blog.domain.user.repository;

import com.example.my_blog.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {
}
