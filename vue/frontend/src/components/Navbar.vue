<template>
    <nav id="main-navbar" class="fixed-top navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a @click="$router.push('/');" class="navbar-brand" href="#">
                <img src="../assets/logo.png" alt="" width="30" height="24" class="d-inline-block align-text-top">
                My Blog
            </a>

            <!-- Right links -->
            <div>
                <ul class="navbar-nav d-flex align-items-center flex-row me-auto mb-2 mb-lg-0">

                    <!-- 비로그인 시 -->
                    <div v-if="!isLoggedIn" class="d-flex">
                        <li class="nav-item">
                            <a @click="$router.push('/login')" href="#" class="nav-link active mx-2"
                                aria-current="page">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active text-secondary" aria-current="page" href="#">|</a>
                        </li>
                        <li class="nav-item">
                            <a @click="$router.push('/user/join')" href="#" class="nav-link active mx-2">회원가입</a>
                        </li>
                    </div>

                    <!-- 로그인 시 -->
                    <div v-if="isLoggedIn" class="d-flex">
                        <li class="nav-item mx-2">
                            <a class="nav-link active" aria-current="page" href="#">{{ session.name }}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active text-secondary" aria-current="page" href="#">|</a>
                        </li>
                        <li class="nav-item mx-2">
                            <a class="nav-link active" aria-current="page" href="#">{{ session.nickname }}</a>
                        </li>
                        <li class="nav-item mx-2">
                            <a @click="logout" href="#" class="nav-link active">로그아웃</a>
                        </li>
                        <li class="nav-item mx-2">
                            <a @click="$router.push('/admin')" href="#" class="nav-link active text-danger">관리자 콘솔</a>
                        </li>
                    </div>

                </ul>
            </div>

        </div>
    </nav>
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