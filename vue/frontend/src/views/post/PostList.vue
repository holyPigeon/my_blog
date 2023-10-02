<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">

            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                <div class="overflow-x-auto">

                    <div class="p-4 text-center">
                        <p class="text-lg fw-bold">게시글 목록</p>
                    </div>
                    <div class="text-end">
                        <div class="dropdown dropdown-hover">
                            <label tabindex="0" class="btn btn-sm btn-primary m-1">{{ pageSize }}개</label>
                            <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                <li><a @click="changePageSize(10)">10개</a></li>
                                <li><a @click="changePageSize(30)">30개</a></li>
                                <li><a @click="changePageSize(50)">50개</a></li>
                            </ul>
                        </div>
                        <div class="dropdown dropdown-end dropdown-hover">
                            <label tabindex="0" class="btn btn-sm btn-primary m-1">필터</label>
                            <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                <li><a @click="changePageSort('like')">추천 많은 순</a></li>
                                <li><a @click="changePageSort('rlike')">추천 적은 순</a></li>
                            </ul>
                        </div>
                        <div class="flex-grow text-end">
                            <div class="dropdown">
                                <label tabindex="0" class="btn btn-sm btn-primary m-1">{{ searchCondition }}</label>
                                <ul tabindex="0"
                                    class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                    <li><a @click="changeSearchCondition('제목')">제목</a></li>
                                    <li><a @click="changeSearchCondition('작성자')">작성자</a></li>
                                </ul>
                            </div>
                            <input v-model="searchKeyword" type="text"
                                class="input input-bordered input-sm border-neutral-content w-1/4 max-w-xs" />
                            <button @click="search(searchKeyword)" class="btn btn-sm inline p-1">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                    stroke="currentColor" class="w-6 h-6 inline">
                                    <path stroke-linecap="round" stroke-linejoin="round"
                                        d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" />
                                </svg>
                            </button>
                        </div>
                    </div>

                    <table class="table border border-base-content mt-4">
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

                    <div class="text-center">
                        <div class="join border border-dark mt-8">
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
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'postList',
    data() {
        return {
            pageSize: 10,
            pageSort: 'date',
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
        changePageSize(size) {
            axios.get('/api/posts?sort=' + this.pageSort + '&size=' + size + '&page=' + (this.postList.number + 1))
                .then((res) => {
                    this.postList = { ...res.data };
                    this.pageSize = size;
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changePageSort(sort) {
            axios.get('/api/posts?sort=' + sort + '&size=' + this.pageSize + '&page=' + (this.postList.number + 1))
                .then((res) => {
                    this.postList = { ...res.data };
                    this.pageSort = 'date';
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changeSearchCondition(searchCondition) {
            this.searchCondition = searchCondition;
        },
        search(keyword) {
            if (this.searchCondition == '제목') {
                axios.get(`/api/posts?sort=${this.pageSort}&page=${this.postList.number + 1}&size=${this.pageSize}&name=${keyword}&nickname=`)
                    .then((res) => {
                        this.userList = { ...res.data };
                        if (this.postList.content.length == 0) {
                            alert('검색 결과가 없습니다.');
                        }
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            } else if (this.searchCondition == '작성자') {
                axios.get(`/api/posts?sort=${this.pageSort}&page=${this.postList.number + 1}&size=${this.pageSize}&name=&nickname=${keyword}`)
                    .then((res) => {
                        this.userList = { ...res.data };
                        if (this.postList.content.length == 0) {
                            alert('검색 결과가 없습니다.');
                        }
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        },
        checkButtonActive(currentPage, index) {
            if (currentPage === index) {
                return 'btn text-primary';
            } else {
                return '';
            }
        },
        goToPage(index) {
            axios.get('/api/posts?sort=' + this.pageSort + '&size=' + this.pageSize + '&page=' + index)
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
                axios.get('/api/posts?sort=' + this.pageSort + '&size=' + this.pageSize + '&page=' + index)
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
                axios.get('/api/posts?sort=' + this.pageSort + '&size=' + this.pageSize + '&page=' + index)
                    .then((res) => {
                        this.postList = { ...res.data };
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        }

    },
    beforeMount() {
        axios.get('/api/posts?sort=date&size=10&page=1')
            .then((res) => {
                this.postList = { ...res.data };
                this.pageSort = 'date';
                this.pageSize = 10;
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