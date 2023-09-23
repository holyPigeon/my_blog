<template>
    <div class="hero h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">
            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                <div class="overflow-x-auto">
                    <div class="p-4">
                        <p class="mb-3 text-center text-lg fw-bold">회원 목록</p>
                    </div>

                    <table class="text-center fs-4 border border-base-content w-full">
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
                            <button @click="goToPage(index)" v-for="(index) in userList.totalPages" :key="index"
                                :class="checkButtonActive(userList.number + 1, index)"
                                class="join-item btn hover:primary">{{ index }}
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
        checkButtonActive(currentPage, index) {
            if (currentPage === index) {
                return 'btn text-primary';
            } else {
                return '';
            }
        },
        goToPage(index) {
            axios.get('/api/users?page=' + index)
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
                axios.get('/api/users?page=' + index)
                    .then((res) => {
                        this.userList = { ...res.data };
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        },
        goToNextPage(index) {
            if (this.userList.last) {
                alert("마지막 페이지입니다.");
            } else {
                axios.get('/api/users?page=' + index)
                    .then((res) => {
                        this.userList = { ...res.data };
                    }).catch((err) => {
                        JSON.stringify("err => " + err);
                    });
            }
        }

    },
    beforeMount() {
        axios.get('/api/users?page=1')
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