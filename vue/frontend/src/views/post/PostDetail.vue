<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-start w-full text-neutral-content">
            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 p-8">
                <article>
                    <div class="grid grid-col-12 mb-12">
                        <div class="col-start-1 col-span-1">
                            <img class="" :src="require('../../assets/profile.png')" alt="avatar" width="50" height="50" />
                        </div>
                        <div class="col-start-2 col-span-11 text-start">
                            <p>{{ post.author }}</p>
                            <p>{{ post.createdDate }} ·
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                    stroke="currentColor" class="w-5 h-5 inline" style="vertical-align: text-top;">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                        d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z" />
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                        d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                                </svg>
                                {{ post.viewCount }}
                                <!-- / {{ post.lastModifiedDate }} -->
                            </p>
                        </div>
                    </div>

                    <header class="mb-12">
                        <h1 class="fw-bolder mb-1 text-3xl">{{ post.title }}</h1>
                    </header>
                    <!-- <figure class="mb-4"><img class="img-fluid rounded"
                                            src="https://dummyimage.com/900x400/ced4da/6c757d.jpg" alt="..."></figure> -->
                    <section class="mb-5">
                        <p class="fs-5 mb-4">{{ post.content }}</p>
                    </section>
                    <div class="mt-16 flex justify-center items-center">
                        <img v-if="alreadyLiked == false" @click="likeControl" :src="require('../../assets/heart.png')"
                            style="width: 30px;" class="heart-filter" />
                        <img v-if="alreadyLiked == true" @click="likeControl" :src="require('../../assets/heart_fill.png')"
                            style="width: 30px;" class="heart-fill-filter" />
                    </div>
                    <p class="mt-3 text-center">{{ postLikeCount }}</p>
                </article>
                <div class="row">
                    <hr class="my-3 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                </div>

                <!-- 댓글 작성란 -->
                <div id="write_comment" class="mb-5">
                    <h5 class="text-left p-3">{{ comments.count }}개의 댓글</h5>
                    <div class="card border-2 rounded-3 border-dark" style="border-color: #2D2A2A;">
                        <div class="card-body grid grid-col-12 p-5">
                            <div class="col-start-1 col-span-1">
                                <img class="mx-auto my-2" :src="require('../../assets/profile.png')" alt="avatar" width="50"
                                    height="50" />
                            </div>
                            <div class="col-end-12 col-span-10 p-1">
                                <form class="mb-2"><textarea v-model="comment.content"
                                        class="textarea border-2 w-full text-lg" rows="3"
                                        style="text-indent: 0.5%; padding-top: 1%; resize: none; border-color: #2D2A2A;"
                                        placeholder="Leave a comment here"></textarea>
                                </form>
                            </div>
                            <div class="col-start-10 col-span-2 p-1 text-end d-grid gap-2">
                                <button @click="createComment" class="btn btn-primary">댓글 쓰기</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 댓글 리스트 -->
                <div id="comments">
                    <div v-for="(comment, index) in comments.commentList" :key="index">
                        <div class="grid grid-col-12 p-5">
                            <div class="col-start-1 col-span-1 p-2">
                                <img class="" :src="require('../../assets/profile.png')" alt="avatar" width="50"
                                    height="50" />
                            </div>
                            <div class="col-start-2 col-span-3 text-start">
                                <p>{{ comment.author }}</p>
                                <p>{{ comment.createdDate }}</p>
                            </div>
                            <div class="col-start-5 col-end-11 text-start">
                            </div>
                            <div class="col-start-11 col-span-2 text-end">
                                <button @click="createReplyCommentInput(comment.id)"
                                    class="text-neutral-content mr-4">답글</button>
                                <button @click="deleteComment(comment.id)" class="text-error">삭제</button>
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
                                <div class="col-start-1 col-span-1 py-6 text-center">
                                    ㄴ
                                </div>
                                <div class="col-start-2 col-span-11 text-start">
                                    <div class="grid grid-col-12">
                                        <div class="col-start-1 col-span-1 p-2">
                                            <img class="" :src="require('../../assets/profile.png')" alt="avatar" width="50"
                                                height="50" />
                                        </div>
                                        <div class="col-start-2 col-span-3 text-start">
                                            <p>{{ comment2.author }}</p>
                                            <p>{{ comment2.createdDate }}</p>
                                        </div>
                                        <div class="col-start-5 col-end-11 text-start">
                                        </div>
                                        <div class="col-start-11 col-span-2 text-end">
                                            <button @click="createReplyCommentInput(comment.id)"
                                                class="text-neutral-content mr-4">답글</button>
                                            <button @click="deleteComment(comment2.id)"
                                                class="text-error">삭제</button>
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
                        <div class="grid grid-col-12 p-5">
                            <div class="col-start-1 col-span-1">

                            </div>
                            <div v-if="replyCommentActive[comment.id] === true" class="col-start-2 col-span-10">
                                <input @input="onReplyCommentInputChange" type="text" placeholder="Type here"
                                    class="input input-bordered border border-dark w-full max" />
                            </div>
                            <div v-if="replyCommentActive[comment.id] === true" class="col-start-12 col-span-1 text-end">
                                <button @click="createReplyComment(comment.id)" class="btn btn-primary">댓글
                                    쓰기</button>
                            </div>
                        </div>

                        <div v-if="index !== comments.commentList.length - 1" class="row">
                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
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
                        // createdDate: '',
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
                        // createdDate: '',
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
                    // 부모 댓글들의 id를 미리 추출하여 replyCommentActive 객체에 저장함으로써
                    // 답글 버튼 클릭 시 해당 부모 댓글 id와 일치하는 작성란을 활성화줄 수 있도록 함.
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
        onReplyCommentInputChange(event) {
            this.replyComment.content = event.target.value;
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