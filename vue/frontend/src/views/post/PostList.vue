<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">

            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8"
                style="height: 780px; position: relative;">
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
                            <tr v-for="(post, index) in postList.content" :key="index" class="hover text-neutral-content">
                                <th style="width: 10%;">{{ post.id }}</th>
                                <td style="width: 50%;">
                                    <a @click="$router.push(`/post/list/${post.id}`)">
                                        {{ post.title }}
                                    </a>
                                </td>
                                <td style="width: 15%;">{{ post.author }}</td>
                                <td>{{ post.createdDate }}</td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="join mb-4 border border-dark" style="position: absolute; bottom: 0; left: 30%;">
                        <button @click="goToPreviousPage((postList.number + 1) - 1)"
                            class="join-item btn"><a>이전</a></button>
                        <button @click="goToPage(index)" v-for="(index) in postList.totalPages" :key="index"
                            :class="checkButtonActive(postList.number + 1, index)"
                            class="join-item btn hover:neutral-content">{{
                                index }}
                        </button>
                        <button @click="goToNextPage((postList.number + 1) + 1)" class="join-item btn">다음</button>
                    </div>

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
            postList: null
            // {
            //     "content": [
            //         {
            //             id: 1,
            //             author: '',
            //             title: '',
            //             content: '',
            //             createdDate: '',
            //             updatedAt: '',
            //         }
            //     ],
            //     "pageable": {
            //         "sort": {
            //             "empty": false,
            //             "unsorted": false,
            //             "sorted": true
            //         },
            //         "offset": 50,
            //         "pageNumber": 5, // 5+1 해서 6페이지
            //         "pageSize": 10,
            //         "paged": true,
            //         "unpaged": false
            //     },
            //     "totalPages": 6,
            //     "totalElements": 51, // 총 유저 수
            //     "last": true,
            //     "size": 10, // 한 페이지당 유저 수
            //     "number": 5, // 현재 페이지 (number = 5일 경우 6페이지)
            //     "sort": {
            //         "empty": false,
            //         "unsorted": false,
            //         "sorted": true
            //     },
            //     "numberOfElements": 1, // 현재 페이지의 유저 수
            //     "first": false,
            //     "empty": false
            // }
        }
    },
    methods: {
        checkButtonActive(currentPage, index) {
            if (currentPage === index) {
                return 'btn text-primary';
            } else {
                return '';
            }
        },
        goToPage(index) {
            axios.get('/api/posts?page=' + index)
                .then((res) => {
                    this.postList = { ...res.data };
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        goToPreviousPage(index) {
            if (this.postList.first) {
                alert("첫 페이지입니다.");
            } else {
                axios.get('/api/posts?page=' + index)
                    .then((res) => {
                        this.postList = { ...res.data };
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        },
        goToNextPage(index) {
            if (this.postList.last) {
                alert("마지막 페이지입니다.");
            } else {
                axios.get('/api/posts?page=' + index)
                    .then((res) => {
                        this.postList = { ...res.data };
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        }

    },
    beforeMount() {
        axios.get('/api/posts?page=1')
            .then((res) => {
                console.log(res);
                this.postList = { ...res.data };
                console.log("postList after -> " + JSON.stringify(this.postList));
            }).catch((err) => {
                JSON.stringify("err => " + err);
            });
    },
}

</script>
 
<style>
.table tr:last-child {
    border-bottom: 1px solid #CAD2CF;
}

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