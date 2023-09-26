package com.example.my_blog.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -485681970L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.example.my_blog.domain.comment.Comment, com.example.my_blog.domain.comment.QComment> comments = this.<com.example.my_blog.domain.comment.Comment, com.example.my_blog.domain.comment.QComment>createList("comments", com.example.my_blog.domain.comment.Comment.class, com.example.my_blog.domain.comment.QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<com.example.my_blog.domain.post.Post, com.example.my_blog.domain.post.QPost> posts = this.<com.example.my_blog.domain.post.Post, com.example.my_blog.domain.post.QPost>createList("posts", com.example.my_blog.domain.post.Post.class, com.example.my_blog.domain.post.QPost.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

