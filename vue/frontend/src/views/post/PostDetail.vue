<template>
    <div id="wrapper" class="container-fluid">
        <!-- <img alt="Vue logo" src="../../assets/logo.png"> -->
        <div class="container px-4s my-5">
            <div class="row gx-4 justify-content-center m-auto">
                <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-lg-12">
                                <!-- Post content-->
                                <article>
                                    <!-- Post header-->
                                    <header class="mb-4">
                                        <!-- Post title-->
                                        <h1 class="fw-bolder mb-1">{{ post.title }}</h1><!-- Post meta content-->
                                        <div class="text-muted fst-italic mb-2">Posted on
                                            {{ post.createdAt }}</div>
                                        <div class="text-muted fst-italic mb-2">Updated on
                                            {{ post.updatedAt }}</div>
                                        <div class="text-muted fst-italic mb-2">Posted by {{ post.author }}</div>
                                    </header><!-- Preview image figure-->
                                    <!-- <figure class="mb-4"><img class="img-fluid rounded"
                                            src="https://dummyimage.com/900x400/ced4da/6c757d.jpg" alt="..."></figure> -->
                                    <!-- Post content-->
                                    <section class="mb-5">
                                        <p class="fs-5 mb-4">{{ post.content }}</p>
                                    </section>
                                </article>
                                <div class="row">
                                    <hr class="my-3 col-md-12 offset-md-0 border border-1 border-dark"
                                        style="opacity: 0.1;">
                                </div>

                                <!-- 댓글 작성란 -->
                                <div id="write_comment" class="mb-5">
                                    <h5 class="row text-left p-3">{{ comments.count }}개의 댓글</h5>
                                    <div class="card border rounded-3">
                                        <div class="card-body row" style="padding-right: 4%; padding-top: 2%;">
                                            <div class="col-md-1">
                                                <img class="rounded-circle shadow-1-strong me-3"
                                                    src="../../assets/profile.png" alt="avatar" width="50" height="50" />
                                            </div>
                                            <div class="col-md-11 p-1">
                                                <form class="mb-2"><textarea v-model="comment.content" class="form-control" rows="3"
                                                        style="text-indent: 0.5%; padding-top: 1%; resize: none;"
                                                        placeholder="Leave a comment here"></textarea>
                                                </form>
                                            </div>
                                            <div class="offset-md-10 col-md-2 p-1 text-end d-grid gap-2">
                                                <button @click="createComment" class="btn btn text-white"
                                                    style="background-color: #0090F8;">댓글 쓰기</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 댓글 리스트 -->
                                <div id="comments">
                                    <div id="comment" v-for="(comment, index) in comments.commentList" :key="index">
                                        <div class="row" style="padding-right: 4%; padding-left: 2%;">
                                            <div class="col-md-1 p-2 d-flex justify-content-center">
                                                <img class="rounded-circle shadow-1-strong me-3 mx-3"
                                                    src="../../assets/profile.png" alt="avatar" width="50" height="50" />
                                            </div>
                                            <div class="col-md-11 p-2">
                                                <div class="row">
                                                    <div class="col-md-2 text-start">
                                                        <h6>{{ comment.author }}</h6>
                                                    </div>
                                                    <div class="col-md-8"></div>
                                                    <div class="col-md-2 text-end"><button @click="deleteComment(comment.id)" class="btn btn-outline-danger">삭제</button></div>
                                                    <div class="col-md-3 text-start">
                                                        <h6>{{ comment.createdAt }}</h6>
                                                    </div>
                                                    <div class="col-md-9"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" style="padding-right: 4%; padding-left: 2%;">
                                            <div class="col-md-11 text-start">
                                                <p class="mt-3">
                                                    {{ comment.content }}
                                                </p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark"
                                            style="opacity: 0.1;">
                                        </div>
                                    </div>
                                </div>

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
                    }
                ],
            },
            comment: {
                userId: JSON.parse(sessionStorage.getItem("sessionData")).id,
                content: "",
            }
        }
    },
    methods: {
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

        axios.get(`/api/posts/${this.postId}`)
            .then((res) => {
                console.log("posts res.data => " + JSON.stringify(res.data));
                this.post = { ...res.data };
            }).catch((err) => {
                JSON.stringify("err => " + err);
            });

        axios.get(`/api/posts/${this.postId}/comments`)
            .then((res) => {
                console.log("comments res.data => " + JSON.stringify(res.data));
                this.comments = { ...res.data };
                console.log("this.comments ->" + JSON.stringify(this.comments));
            }).catch((err) => {
                JSON.stringify("err => " + err);
            });
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
</style>