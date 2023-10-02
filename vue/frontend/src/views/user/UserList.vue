<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">
            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                <div class="overflow-x-auto">

                    <div class="p-4 text-center">
                        <p class="mb-3 text-center text-lg fw-bold">회원 목록</p>
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
                                <li><a @click="changePageSort('like')">추천 많은 순</a></li>
                                <li><a @click="changePageSort('rlike')">추천 적은 순</a></li>
                            </ul>
                        </div>
                        <div class="flex-grow text-end">
                            <div class="dropdown">
                                <label tabindex="0" class="btn btn-sm btn-primary m-1">{{ searchCondition }}</label>
                                <ul tabindex="0"
                                    class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52">
                                    <li><a @click="changeSearchCondition('이름')">이름</a></li>
                                    <li><a @click="changeSearchCondition('닉네임')">닉네임</a></li>
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

                    <table class="text-center fs-4 border border-base-content w-full mt-4">
                        <tr class="border border-base-content">
                            <td>User Id</td>
                            <td>User Name</td>
                            <td>User Nickname</td>
                        </tr>
                        <tr v-for="(user, index) in userList.content" :key="index" class="hover text-neutral-content">
                            <td style="width: 10%;">{{ user.id }}</td>
                            <td style="width: 50%;">{{ user.name }}</td>
                            <td style="width: 40%;">{{ user.nickname }}</td>
                        </tr>
                    </table>

                    <div class="text-center">
                        <div class="join mt-8 border border-dark">
                            <button @click="goToPreviousPage((userList.number + 1) - 1)"
                                class="join-item btn"><a>이전</a></button>
                            <button @click="goToPage(page)" v-for="(page) in userList.totalPages" :key="page"
                                :class="checkButtonActive(userList.number + 1, page)" class="join-item btn hover:primary">{{
                                    page }}
                            </button>
                            <button @click="goToNextPage((userList.number + 1) + 1)" class="join-item btn">다음</button>
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
    name: 'userList',
    data() {
        return {
            pageSize: 10,
            pageSort: 'date',
            searchCondition: '이름',
            searchKeyword: '',
            userList: {
                // "content": [
                //     {
                //         // id: '',
                //         // name: '',
                //         // age: '',
                //     }
                // ],
                // "pageable": {
                //     "sort": {
                //         "empty": false,
                //         "unsorted": false,
                //         "sorted": true
                //     },
                //     "offset": 50,
                //     "pageNumber": 5, // 5+1 해서 6페이지
                //     "pageSize": 10,
                //     "paged": true,
                //     "unpaged": false
                // },
                // "totalPages": 6,
                // "totalElements": 51, // 총 유저 수
                // "last": true,
                // "size": 10, // 한 페이지당 유저 수
                // "number": 5, // 현재 페이지 (number = 5일 경우 6페이지)
                // "sort": {
                //     "empty": false,
                //     "unsorted": false,
                //     "sorted": true
                // },
                // "numberOfElements": 1, // 현재 페이지의 유저 수
                // "first": false,
                // "empty": false
            }
        }
    },
    methods: {
        changePageSize(size) {
            axios.get(`/api/users?sort=${this.pageSort}&page=${this.userList.number + 1}&size=${size}&name=&nickname=`)
                .then((res) => {
                    this.userList = { ...res.data };
                    this.pageSize = size;
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changePageSort(sort) {
            axios.get(`/api/users?sort=${sort}&page=${this.userList.number + 1}&size=${this.pageSize}&name=&nickname=`)
                .then((res) => {
                    this.userList = { ...res.data };
                    this.pageSort = 'date';
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        changeSearchCondition(searchCondition) {
            this.searchCondition = searchCondition;
        },
        search(keyword) {
            if (this.searchCondition == '이름') {
                axios.get(`/api/users?sort=${this.pageSort}&page=${this.userList.number + 1}&size=${this.pageSize}&name=${keyword}&nickname=`)
                    .then((res) => {
                        this.userList = { ...res.data };
                        if (this.userList.content.length == 0) {
                            alert('검색 결과가 없습니다.');
                        }
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            } else if (this.searchCondition == '닉네임') {
                axios.get(`/api/users?sort=${this.pageSort}&page=${this.userList.number + 1}&size=${this.pageSize}&name=&nickname=${keyword}`)
                    .then((res) => {
                        this.userList = { ...res.data };
                        if (this.userList.content.length == 0) {
                            alert('검색 결과가 없습니다.');
                        }
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        },
        checkButtonActive(currentPage, page) {
            if (currentPage === page) {
                return 'btn text-primary';
            } else {
                return '';
            }
        },
        goToPage(page) {
            axios.post(`/api/users?sort=${this.pageSort}&page=${page}&size=${this.pageSize}&name=&nickname=`)
                .then((res) => {
                    this.userList = { ...res.data };
                }).catch((err) => {
                    JSON.stringify("err => " + err);
                });
        },
        goToPreviousPage(index) {
            if (this.userList.first) {
                alert("첫 페이지입니다.");
            } else {
                goToPage(index);
            }
        },
        goToNextPage(index) {
            if (this.userList.last) {
                alert("마지막 페이지입니다.");
            } else {
                goToPage(index);
            }
        }
    },
    beforeMount() {
        axios.get(`/api/users?page=1&size=${this.pageSize}&sort=${this.pageSort}&name=&nickname=`)
            .then((res) => {
                this.userList = { ...res.data };
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
</style>