<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">

            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                <div class="overflow-x-auto">

                    <div class="p-4 text-center">
                        <p class="text-lg fw-bold">게시글 목록</p>
                    </div>
                    <div class="flex items-center">
                        <div class="dropdown dropdown-hover text-start">
                            <label tabindex="0" class="btn btn-sm btn-primary m-1">{{ pageSize }}개</label>
                            <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                <li><a @click="changePageSize(10)">10개</a></li>
                                <li><a @click="changePageSize(30)">30개</a></li>
                                <li><a @click="changePageSize(50)">50개</a></li>
                            </ul>
                        </div>
                        <div class="dropdown dropdown-hover text-start">
                            <label tabindex="0" class="btn btn-sm btn-primary m-1">필터</label>
                            <ul tabindex="0" class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                <li><a @click="changePageSort('view')">조회수 많은 순</a></li>
                                <li><a @click="changePageSort('rview')">조회수 적은 순</a></li>
                                <li><a @click="changePageSort('like')">추천 많은 순</a></li>
                                <li><a @click="changePageSort('rlike')">추천 적은 순</a></li>
                                <li><a @click="changePageSort('date')">날짜 최신 순</a></li>
                                <li><a @click="changePageSort('rdate')">날짜 오래된 순</a></li>
                            </ul>
                        </div>
                        <div class="flex-grow text-end">
                            <div class="dropdown">
                                <label tabindex="0" class="btn btn-sm btn-primary m-1">{{ searchCondition }}</label>
                                <ul tabindex="0"
                                    class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                    <li><a @click="changeSearchCondition('제목')">제목</a></li>
                                    <li><a @click="changeSearchCondition('내용')">내용</a></li>
                                    <li><a @click="changeSearchCondition('작성자')">작성자</a></li>
                                    <li><a @click="changeSearchCondition('전체')">전체</a></li>
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
                                <th>좋아요</th>
                                <th>조회</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr v-for="post in postList.content" :key="post.id" class="hover text-neutral-content">
                                <th style="width: 10%;">{{ post.id }}</th>
                                <td style="width: 50%;">
                                    <a @click="$router.push(`/post/list/${post.id}`)">
                                        {{ post.title }}
                                    </a>
                                </td>
                                <td style="width: 15%;">{{ post.author }}</td>
                                <td>{{ post.createdDate }}</td>
                                <td class="text-center">{{ post.likeCount }}</td>
                                <td class="text-center">{{ post.viewCount }}</td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="text-center">
                        <div class="join border border-dark mt-8">
                            <button @click="goToPreviousPage((postList.number + 1) - 1)"
                                class="join-item btn"><a>이전</a></button>
                            <button @click="goToPage(page)" v-for="(page) in postList.totalPages" :key="page"
                                :class="checkButtonActive(postList.number + 1, page)"
                                class="join-item btn hover:neutral-content">{{
                                    page }}
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
            searchCondition: '제목',
            searchKeyword: '',
            searchParameter: '',
            postList: {}
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
            axios.get(`/api/posts?sort=${this.pageSort}&size=${size}&page=${this.postList.number + 1}`)
                .then((res) => {
                    this.postList = { ...res.data };
                    this.pageSize = size;
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changePageSort(sort) {
            this.changeSearchParameter(this.searchKeyword);
            axios.get(`/api/posts/sort?sort=${sort}&size=${this.pageSize}&page=${this.postList.number + 1}${this.searchParameter}`)
                .then((res) => {
                    this.postList = { ...res.data };
                    this.pageSort = sort;
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changeSearchCondition(searchCondition) {
            this.searchCondition = searchCondition;
        },
        search(keyword) {
            this.changeSearchParameter(keyword);
            axios.get(`/api/posts/search?page=1&size=${this.pageSize}${this.searchParameter}`)
                .then((res) => {
                    this.postList = { ...res.data };
                    if (this.postList.totalElements == 0) {
                        alert('검색 결과가 없습니다.');
                    }
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changeSearchParameter(keyword) {
            switch (this.searchCondition) {
                case '제목':
                    this.searchParameter
                        = `&title=${keyword}&content=&author=`;
                    break;

                case '내용':
                    this.searchParameter
                        = `&title=&content=${keyword}&author=`;
                    break;

                case '작성자':
                    this.searchParameter
                        = `&title=&content=&author=${keyword}`;
                    break;

                case '전체':
                    this.searchParameter
                        = `&title=${keyword}&content=${keyword}&author=${keyword}`;
                    break;

                default:
                    this.searchParameter
                        = ``;
            }
        },
        checkButtonActive(currentPage, index) {
            if (currentPage === index) {
                return 'btn text-primary';
            } else {
                return '';
            }
        },
        goToPage(page) {
            this.changeSearchParameter(this.searchKeyword);
            axios.get(`/api/posts/sort?sort=${this.pageSort}&size=${this.pageSize}&page=${page}${this.searchParameter}`)
                .then((res) => {
                    this.postList = { ...res.data };
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        goToPreviousPage(page) {
            if (this.postList.first) {
                alert("첫 페이지입니다.");
            } else {
                this.goToPage(page);
            }
        },
        goToNextPage(page) {
            if (this.postList.last) {
                alert("마지막 페이지입니다.");
            } else {
                this.goToPage(page);
            }
        }

    },
    created() {
        axios.get(`/api/posts?page=1&size=${this.pageSize}`)
            .then((res) => {
                this.postList = { ...res.data };
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