<template>
    <div id="wrapper" class="container-fluid">
        <div class="container px-4s my-5">
            <div class="row gx-4 justify-content-center m-auto">
                <!-- <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="p-4">
                        <h4 class="mb-3 text-center text-black fs-3 fw-bold">게시글 목록</h4>
                    </div>

                    <table class="text-center fs-4" style="width: 100%; border: 1px solid #444444;">
                        <tr>
                            <td>Post Id</td>
                            <td>Post Author</td>
                            <td>Post Title</td>
                            <td>Post Content</td>
                        </tr>
                        <tr v-for="(post, index) in postList.postData" :key="index">
                            <td>{{ post.id }}</td>
                            <td>{{ post.author }}</td>
                            <td>{{ post.title }}</td>
                            <td>{{ post.content }}</td>
                        </tr>
                    </table>
                </div> -->
                <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="p-4">
                        <h4 class="mb-3 text-center text-black fs-3 fw-bold">게시글 목록</h4>
                    </div>
                    <table class="text-center" style="width: 100%; border: 1px solid #444444;">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>글쓴이</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(post, index) in postList.data" :key="index">
                                <td style="width: 10%;">{{ post.id }}</td>
                                <td style="width: 50%;"><a @click="$router.push(`/post/list/${post.id}`)"
                                        class="custom-nav">{{ post.title }}</a></td>
                                <td style="width: 15%;">{{ post.author }}</td>
                                <td>{{ post.createdAt }}</td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- <div class="row">
                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                        </div> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'postList',
    data() {
        return {
            postList: {
                count: -1,
                data: [
                    {
                        // id: 1,
                        // author: '',
                        // title: '',
                        // content: '',
                        // createdAt: '',
                        // updatedAt: '',
                    }
                ],
            }
        }
    },
    methods: {
    },
    beforeMount() {
        axios.get('/post/list')
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.postList.count = res.data.count;
                this.postList.data = [...res.data.postList];
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

.custom-nav {
    color: #666666;
    text-decoration: none;
}

.custom-nav:hover {
    color: #0061f2;
}
</style>