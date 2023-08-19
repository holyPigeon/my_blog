<template>
    <div id="wrapper" class="container-fluid">
        <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
            <div class="bg-opacity-60 hero-overlay"></div>
            <div class="hero-content text-center w-full text-neutral-content">
                <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 p-8">
                    <article class="">
                        <header class="mb-4">
                            <h1 class="fw-bolder mb-1">{{ post.title }}</h1><!-- Post meta content-->
                            <div class="mb-2">Posted on
                                {{ post.createdAt }}</div>
                            <div class="mb-2">Updated on
                                {{ post.updatedAt }}</div>
                            <div class="mb-8">Posted by {{ post.author }}</div>
                        </header>
                        <!-- <figure class="mb-4"><img class="img-fluid rounded"
                                            src="https://dummyimage.com/900x400/ced4da/6c757d.jpg" alt="..."></figure> -->
                        <section class="mb-5">
                            <p class="fs-5 mb-4">{{ post.content }}</p>
                        </section>
                        <div class="mt-16 flex justify-center items-center">
                            <img v-if="alreadyLiked == false" @click="likeControl" :src="require('../../assets/heart.png')"
                                style="width: 30px;" class="heart-filter" />
                            <img v-if="alreadyLiked == true" @click="likeControl"
                                :src="require('../../assets/heart_fill.png')" style="width: 30px;"
                                class="heart-fill-filter" />
                        </div>
                        <p class="mt-3">{{ postLikeCount }}</p>
                    </article>
                    <div class="row">
                        <hr class="my-3 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                    </div>

                    <!-- 댓글 작성란 -->
                    <div id="write_comment" class="mb-5">
                        <h5 class="text-left p-3">{{ comments.count }}개의 댓글</h5>
                        <div class="card border rounded-3 border-neutral-content">
                            <div class="card-body grid grid-col-12 p-5">
                                <div class="col-start-1 col-span-1">
                                    <img class="mx-auto my-2" :src="require('../../assets/profile.png')" alt="avatar"
                                        width="50" height="50" />
                                </div>
                                <div class="col-end-12 col-span-10 p-1">
                                    <form class="mb-2"><textarea v-model="comment.content"
                                            class="textarea border border-neutral-content w-full" rows="3"
                                            style="text-indent: 0.5%; padding-top: 1%; resize: none;"
                                            placeholder="Leave a comment here"></textarea>
                                    </form>
                                </div>
                                <div class="col-start-10 col-span-2 p-1 text-end d-grid gap-2">
                                    <button @click="createComment" class="btn btn-outline btn-info">댓글 쓰기</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 댓글 리스트 -->
                    <div id="comments">
                        <div id="comment" v-for="(comment, index) in comments.commentList" :key="index">
                            <div class="grid grid-col-12 p-5">
                                <div class="col-start-1 col-span-1 p-2">
                                    <img class="" :src="require('../../assets/profile.png')" alt="avatar" width="50"
                                        height="50" />
                                </div>
                                <div class="col-start-2 col-span-3 text-start">
                                    <p>{{ comment.author }}</p>
                                    <p>{{ comment.createdAt }}</p>
                                </div>
                                <div class="col-start-5 col-end-10 text-start">
                                </div>
                                <div class="col-start-11 col-span-2 text-end">
                                    <button @click="createReplyCommentInput(comment.id)"
                                        class="btn btn-outline btn-neutral-content mr-4">답글</button>
                                    <button @click="deleteComment(comment.id)" class="btn btn-outline btn-error">삭제</button>
                                </div>
                                <div class="col-start-1 col-span-11 text-start px-2">
                                    <p class="mt-3">
                                        {{ comment.content }}
                                    </p>
                                </div>
                            </div>

                            <!-- 대댓글 리스트 -->
                            <div id="replyComment" v-for="(comment2, index) in comment.children" :key="index">
                                <div class="grid grid-col-12 p-5" style="">
                                    <div class="col-start-1 col-span-1 py-6">
                                        ㄴ
                                    </div>
                                    <div class="col-start-2 col-span-11 text-start">
                                        <div class="grid grid-col-12">
                                            <div class="col-start-1 col-span-1 p-2">
                                                <img class="" :src="require('../../assets/profile.png')" alt="avatar"
                                                    width="50" height="50" />
                                            </div>
                                            <div class="col-start-2 col-span-3 text-start">
                                                <p>{{ comment2.author }}</p>
                                                <p>{{ comment2.createdAt }}</p>
                                            </div>
                                            <div class="col-start-5 col-end-10 text-start">
                                            </div>
                                            <div class="col-start-11 col-span-2 text-end">
                                                <button @click="createReplyCommentInput(comment.id)"
                                                    class="btn btn-outline btn-neutral-content mr-4">답글</button>
                                                <button @click="deleteComment(comment2.id)"
                                                    class="btn btn-outline btn-error">삭제</button>
                                            </div>
                                            <div class="col-start-1 col-span-11 text-start px-2">
                                                <p class="mt-3">
                                                    {{ comment2.content }}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 대댓글 작성란 -->
                            <div class="grid grid-col-12">
                                <div v-if="replyCommentActive == true" class="col-start-2 col-span-10 py-4">
                                    <input v-model="replyComment.content" type="text" placeholder="Type here"
                                        class="input input-bordered border border-neutral-content w-full max" />
                                </div>
                                <div v-if="replyCommentActive == true" class="col-start-12 col-span-1 py-4">
                                    <button @click="createReplyComment(comment.id)" class="btn btn-outline btn-info mr-4">댓글
                                        쓰기</button>
                                </div>
                            </div>

                            <div class="row">
                                <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'postDetail',
    data() {
        return {
            postId: -1,
            post: {
                // id: '',
                // name: '',
                // price: -1,
                // quantity: -1,
            },
            alreadyLiked: false,
            postLikeCount: -1,
            comments: {
                count: -1,
                commentList: [
                    {
                        // id: 1,
                        // postId: 1,
                        // author: '',
                        // content: '',
                        // createdAt: '',
                        // updatedAt: '',
                        // children: [
                        //     {

                        //     }
                        // ]
                    }
                ],
            },
            replyComments: {
                count: -1,
                commentList: [
                    {
                        // id: 1,
                        // postId: 1,
                        // author: '',
                        // content: '',
                        // createdAt: '',
                        // updatedAt: '',
                    }
                ],
            },
            comment: {
                userId: JSON.parse(sessionStorage.getItem("sessionData")).id,
                content: "",
            },
            replyComment: {
                userId: JSON.parse(sessionStorage.getItem("sessionData")).id,
                parentId: -1,
                content: "",
            },
            replyCommentActive: {  // 대댓글 작성란 활성화
                // 1: false,
                // 2: false,
                // 3: true,
            },
        }
    },
    methods: {
        listPostDetail() {
            axios.get(`/api/posts/${this.postId}`)
                .then((res) => {
                    this.post = { ...res.data };
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
        likeControl() {
            if (this.alreadyLiked) {
                this.cancelLikePost();
            } else {
                this.likePost();
            }
        },
        likePost() {
            axios.post(`/api/posts/like`, {
                postId: this.postId,
                userId: JSON.parse(sessionStorage.getItem("sessionData")).id
            }).then((res) => {
                // 게시글 좋아요 후 좋아요된 결과를 서버로부터 가져와 클라이언트에 재반영하는 로직
                this.isAlreadyLiked();
                this.getPostLikeCount();
            }).catch((err) => {
                let errMsg = JSON.stringify(err.response.data.message);
                errMsg = errMsg.substring(1, errMsg.length - 1);
                console.log("errMsg -> " + errMsg);
                alert(errMsg);
            });
        },
        cancelLikePost() {
            axios.post(`/api/posts/cancelLike`, {
                postId: this.postId,
                userId: JSON.parse(sessionStorage.getItem("sessionData")).id
            }).then((res) => {
                // 게시글 좋아요 취소 후 취소된 결과를 서버로부터 가져와 클라이언트에 재반영하는 로직
                this.isAlreadyLiked();
                this.getPostLikeCount();
            }).catch((err) => {
                let errMsg = JSON.stringify(err.response.data.message);
                errMsg = errMsg.substring(1, errMsg.length - 1);
                console.log("errMsg -> " + errMsg);
                alert(errMsg);
            });
        },
        isAlreadyLiked() {
            axios.post(`/api/posts/isAlreadyLiked`, {
                postId: this.postId,
                userId: JSON.parse(sessionStorage.getItem("sessionData")).id
            }).then((res) => {
                console.log("alreadyLiked => " + JSON.stringify(res.data));
                // 게시글 좋아요 후 좋아요된 결과를 서버로부터 가져와 클라이언트에 재반영하는 로직
                this.alreadyLiked = res.data.alreadyLiked;
            }).catch((err) => {
                let errMsg = JSON.stringify(err.response.data.message);
                errMsg = errMsg.substring(1, errMsg.length - 1);
                console.log("errMsg -> " + errMsg);
                alert(errMsg);
            });
        },
        getPostLikeCount() {
            axios.get(`/api/posts/${this.postId}/getPostLikeCount`)
                .then((res) => {
                    console.log("getPostLikeCount => " + JSON.stringify(res.data));
                    // 게시글 좋아요 후 좋아요된 결과를 서버로부터 가져와 클라이언트에 재반영하는 로직
                    this.postLikeCount = res.data.count;
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
        listComments() {
            axios.get(`/api/posts/${this.postId}/comments`)
                .then((res) => {
                    this.comments = { ...res.data };
                    let obj = new Object();
                    for (let i = 0; i < this.comments.commentList.length; i++) {
                        let string = this.comments.commentList[i].id;
                        obj[string] = false;
                    }
                    this.replyCommentActive = obj;
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
        createComment() {
            axios.post(`/api/posts/${this.postId}/comments`, this.comment)
                .then((res) => {
                    console.log(res);
                    location.reload();
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
        createReplyCommentInput(parentId) {
            this.replyCommentActive[parentId] = true;
        },
        createReplyComment(parentId) {
            this.replyComment.parentId = parentId;
            axios.post(`/api/posts/${this.postId}/comments/reply`, this.replyComment)
                .then((res) => {
                    console.log(res);
                    location.reload();
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
        deleteComment(commentId) {
            axios.delete(`/api/posts/${this.postId}/comments/${commentId}`)
                .then((res) => {
                    console.log(res);
                    location.reload();
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
    },
    beforeMount() {
        const url = window.location.pathname;
        const splitArr = url.split("/");
        const postId = splitArr[splitArr.length - 1]; // "1"
        this.postId = postId;
        console.log("postId -> " + postId);

        this.listPostDetail();
        this.isAlreadyLiked();
        this.getPostLikeCount();
        this.listComments();
    },
}

</script>
 
<style>
::placeholder {
    font-size: 18px;
}

td {
    padding: 5px;
}

.heart-filter {
    filter: invert(75%) sepia(11%) saturate(76%) hue-rotate(98deg) brightness(98%) contrast(93%);
}

.heart-fill-filter {
    background-color: invert(48%) sepia(20%) saturate(6759%) hue-rotate(332deg) brightness(89%) contrast(118%);
}
</style>