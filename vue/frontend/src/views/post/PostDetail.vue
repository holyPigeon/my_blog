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
                                <!-- <div class="row">
                                    <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                                </div> -->
                                <section class="mb-5">
                                    <div class="card bg-light">
                                        <div class="card-body">
                                            <form class="mb-4"><textarea class="form-control" rows="3"
                                                    placeholder="Join the discussion and leave a comment!"></textarea>
                                            </form>
                                            <table class="text-center" style="width: 100%; border: 1px solid #CCCDCE;">
                                                <tbody>
                                                    <tr>
                                                        <!-- v-for="(post, index) in postList.data" :key="index" -->
                                                        <td style="width: 10%;"></td>
                                                        <td style="width: 50%;"><a
                                                                @click="$router.push(`/post/list/`)"
                                                                class="custom-nav"></a></td>
                                                        <td style="width: 15%;"></td>
                                                        <td></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </section>
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
            }
        }
    },
    methods: {
        test() {
            const url = window.location.pathname;
            const splitArr = url.split("/");
            const postId = splitArr[splitArr.length - 1]; // "1"
            this.postId = postId;
            console.log("postId -> " + postId);
        },
    },
    beforeMount() {
        const url = window.location.pathname;
        const splitArr = url.split("/");
        const postId = splitArr[splitArr.length - 1]; // "1"
        this.postId = postId;
        console.log("postId -> " + postId);

        axios.get(`/posts/${this.postId}`)
            .then((res) => {
                console.log("res.data => " + JSON.stringify(res.data));
                this.post = {...res.data};
                console.log("postData -> " + JSON.stringify(this.post));
            }).catch((err) => {
                JSON.stringify("err => " + err);
            });
    },
}

</script>
 
<style>
input::placeholder {
    font-size: 14px;
}

td {
    padding: 5px;
}
</style>