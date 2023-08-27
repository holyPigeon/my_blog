package com.example.my_blog;


import com.example.my_blog.controller.CommentController;
import com.example.my_blog.controller.LoginController;
import com.example.my_blog.controller.PostController;
import com.example.my_blog.controller.UserController;
import com.example.my_blog.exception.ErrorResponse;
import com.example.my_blog.exception.MyBlogErrorCode;
import com.example.my_blog.exception.MyBlogException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {
        PostController.class, CommentController.class, LoginController.class, UserController.class
})
public class ApiExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> catchAnotherArtException(MyBlogException ex) {
        MyBlogErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode));
    }
}
