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
                                <!-- <section class="mb-5">
                                    <div class="card bg-light">
                                        <div class="card-body">
                                            <form class="mb-4"><textarea class="form-control" rows="3"
                                                    placeholder="Join the discussion and leave a comment!"></textarea>
                                            </form>
                                            <div class="d-flex mb-4">
                                            
                                                <div class="flex-shrink-0"><img class="rounded-circle"
                                                        src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="...">
                                                </div>
                                                <div class="ms-3">
                                                    <div class="fw-bold">Commenter Name</div>If you're going to lead a space
                                                    frontier, it has to be government; it'll never be private enterprise.
                                                    Because the space frontier is dangerous, and it's expensive, and it has
                                                    unquantified risks.
                                                  
                                                    <div class="d-flex mt-4">
                                                        <div class="flex-shrink-0"><img class="rounded-circle"
                                                                src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
                                                                alt="..."></div>
                                                        <div class="ms-3">
                                                            <div class="fw-bold">Commenter Name</div>And under those
                                                            conditions, you cannot establish a capital-market evaluation of
                                                            that enterprise. You can't get investors.
                                                        </div>
                                                    </div>
                                                    <div class="d-flex mt-4">
                                                        <div class="flex-shrink-0"><img class="rounded-circle"
                                                                src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
                                                                alt="..."></div>
                                                        <div class="ms-3">
                                                            <div class="fw-bold">Commenter Name</div>When you put money
                                                            directly to a problem, it makes a good headline.
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="d-flex">
                                                <div class="flex-shrink-0"><img class="rounded-circle"
                                                        src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="...">
                                                </div>
                                                <div class="ms-3">
                                                    <div class="fw-bold">Commenter Name</div>When I look at the universe and
                                                    all the ways the universe wants to kill us, I find it hard to reconcile
                                                    that with statements of beneficence.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section> -->
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

        axios.get(`/post/list/${this.postId}`)
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