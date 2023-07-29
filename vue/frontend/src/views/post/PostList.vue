<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4">
            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                <div class="overflow-x-auto">
                    <table class="table border border-base-content">
                        <!-- head -->
                        <thead>
                            <tr class="border border-base-content">
                                <th></th>
                                <th>제목</th>
                                <th>글쓴이</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(post, index) in posts.postList" :key="index" class="hover text-neutral-content ">
                                <th style="width: 10%;">{{ post.id }}</th>
                                <td style="width: 50%;">
                                    <a @click="$router.push(`/post/list/${post.id}`)">
                                        {{ post.title }}
                                    </a>
                                </td>
                                <td style="width: 15%;">{{ post.author }}</td>
                                <td>{{ post.createdAt }}</td>
                            </tr>
                        </tbody>
                    </table>
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
            posts: {
                count: -1,
                postList: [
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
        axios.get('/api/posts')
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.posts = { ...res.data };
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