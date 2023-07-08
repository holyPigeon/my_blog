<template>
    <div id="wrapper" class="container-fluid">
        <div class="container px-4s my-5">
            <div class="row gx-4 justify-content-center m-auto">
                <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="p-4">
                        <!-- style="--bs-text-opacity: 0.6;" -->
                        <h4 class="mb-3 text-center text-black fs-3 fw-bold">회원정보 수정</h4>
                    </div>
                        <div class="row g-3">
                            <div class="col-md-4 offset-md-4">
                                <input type="text" v-model="data.name" class="form-control form-control-lg p-2"
                                       id="name" name="name" placeholder="이름" required>
                            </div>
                        </div>
                        <div class="row g-3 mt-2">
                            <div class="col-md-4 offset-md-4">
                                <input type="text" v-model="data.nickname" class="form-control form-control-lg p-2"
                                       id="nickname" name="nickname" placeholder="닉네임" required>
                            </div>
                        </div>

                        <!-- <div class="row">
                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                        </div> -->

                        <div class="row g-3 mt-3">
                            <button type="button" @click="updateUser()"
                                class="btn btn-outline-dark btn-lg col-md-4 offset-md-4 p-2" id="signup"
                                style="opacity: 0.7;">완료</button>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'adminUserUpdate',
    data() {
        return {
            userId: -1,
            data: {
                // name: '',
                // nickname: '',
            },
        }
    },
    methods: {
        updateUser() {
            const data = this.data;
            axios.update(`/api/users/${this.userId}`, data)
                .then((res) => {
                    console.log(res);
                    this.$router.push('/admin/user');
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
        // setMemberName(event, index)  {
        //     console.log("event -> " + JSON.stringify(event));
        //     console.log("index -> " + index);
        //     // this.data.name = event.target.value;
        // },
        // setMemberNickname(event, index)  {
        //     console.log("event -> " + JSON.stringify(event));
        //     console.log("index -> " + index);
        //     // this.data.nickname = event.target.value;
        // }
    },
    beforeMount() {
        const url = window.location.pathname;
        const splitArr = url.split("/");
        this.userId = splitArr[splitArr.length - 1];
        
        axios.get(`/api/users/${this.userId}`)
                .then((res) => {
                    console.log(res);
                    this.data = {...res.data};
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
    }
}

</script>
 
<style>
input::placeholder {
    font-size: 14px;
}
</style>