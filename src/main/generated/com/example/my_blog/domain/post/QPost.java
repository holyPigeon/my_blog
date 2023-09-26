package com.example.my_blog.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 1177604590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.example.my_blog.common.QBaseEntity _super = new com.example.my_blog.common.QBaseEntity(this);

    public final ListPath<com.example.my_blog.domain.comment.Comment, com.example.my_blog.domain.comment.QComment> comments = this.<com.example.my_blog.domain.comment.Comment, com.example.my_blog.domain.comment.QComment>createList("comments", com.example.my_blog.domain.comment.Comment.class, com.example.my_blog.domain.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<com.example.my_blog.domain.like.post.PostLike, com.example.my_blog.domain.like.post.QPostLike> likes = this.<com.example.my_blog.domain.like.post.PostLike, com.example.my_blog.domain.like.post.QPostLike>createList("likes", com.example.my_blog.domain.like.post.PostLike.class, com.example.my_blog.domain.like.post.QPostLike.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final com.example.my_blog.domain.user.QUser user;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.my_blog.domain.user.QUser(forProperty("user")) : null;
    }

}

