<template>
    <div class="navbar bg-primary">
        <div class="flex-1">
            <a @click="$router.push('/');" class="btn btn-ghost normal-case text-xl">
                <p class="mb-1">myBlog</p>
            </a>
        </div>
        <div class="flex-none">
            <!-- <div class="dropdown dropdown-end">
                    <label tabindex="0" class="btn btn-ghost btn-circle">
                        <div class="indicator">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                                stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
                            </svg>
                            <span class="badge badge-sm indicator-item">8</span>
                        </div>
                    </label>
                    <div tabindex="0" class="mt-3 z-[1] card card-compact dropdown-content w-52 bg-secondary shadow">
                        <div class="card-body">
                            <span class="font-bold text-lg">8 Items</span>
                            <span class="text-info">Subtotal: $999</span>
                            <div class="card-actions">
                                <button class="btn btn-primary btn-block">View cart</button>
                            </div>
                        </div>
                    </div>
                </div> -->
            <div class="dropdown dropdown-end">
                <label tabindex="0" class="btn btn-ghost btn-circle avatar">
                    <div class="w-10 rounded-full">
                        <img v-if="!isLoggedIn" :src="require('../assets/profile2.png')" />
                        <img v-if="isLoggedIn" :src="require('../assets/profile3.png')" />
                    </div>
                </label>
                <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-primary rounded-box w-52">
                    <!-- 비로그인 시 -->
                    <div v-if="!isLoggedIn">
                        <li>
                            <p class="text-neutral-content">로그인 정보가 없습니다.</p>
                        </li>
                        <li>
                            <a @click="$router.push('/login')" class="justify-between">
                                로그인
                                <!-- <span class="badge">New</span> -->
                            </a>
                        </li>
                        <li><a @click="$router.push('/user/join')">회원가입</a></li>
                    </div>
                    <!-- 로그인 시 -->
                    <div v-if="isLoggedIn">
                        <li><p>{{ session.name }} | {{ session.nickname }}</p>
                        </li>
                        <li><a @click="logout" class="justify-between">로그아웃</a></li>
                        <li><a @click="$router.push('/admin')" class="text-error">관리자 콘솔</a></li>
                    </div>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'Navbar',
    data() {
        return {
            isLogined: true,
        }
    },
    methods: {
        logout() {
            axios.post(`/api/logout`)
                .then((res) => {
                    console.log(res);
                    sessionStorage.removeItem("sessionData");
                    location.reload();
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
    },
    computed: {
        isLoggedIn() {
            if (sessionStorage.getItem("sessionData") == null) {
                return false;
            } else {
                return true;
            }
        },
        session() {
            return JSON.parse(sessionStorage.getItem("sessionData"));
        }
    },
}
</script>

<style></style>