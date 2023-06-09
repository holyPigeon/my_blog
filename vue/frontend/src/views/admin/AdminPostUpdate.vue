<template>
    <div id="wrapper" class="container-fluid">
        <div class="container px-4s my-5">
            <div class="row gx-4 justify-content-center m-auto">
                <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="p-4">
                        <!-- style="--bs-text-opacity: 0.6;" -->
                        <h4 class="mb-3 text-center text-black fs-3 fw-bold">게시글 등록</h4>
                    </div>

                    <div class="row g-3 mt-1">
                        <div class="col-md-4 offset-md-4">
                            <input type="text" v-model="postData.title" class="form-control form-control-lg p-2" id="title"
                                name="title" placeholder="제목" required>
                        </div>
                    </div>
                    <div class="row g-3 mt-1">
                        <div class="col-md-4 offset-md-4">
                            <input type="text" v-model="postData.content" class="form-control form-control-lg p-2"
                                style="height: 100px;" id="content" name="content" placeholder="내용" required>
                        </div>
                    </div>

                    <!-- <div class="row">
                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                        </div> -->

                    <div class="row g-3 mt-3">
                        <button type="button" @click="updatePost()"
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
    name: 'adminPostUpdate',
    data() {
        return {
            postId: -1,
            postData: {
                // author: '',
                // title: '',
                // content: '',
            },
            userList: {
                count: -1,
                userData: {
                    // id: '',
                    // name: '',
                    // age: '',
                }
            }
        }
    },
    methods: {
        updatePost() {
            const data = this.postData;
            axios.post(`/post/update/${this.postId}`, data)
                .then((res) => {
                    console.log(res);
                    this.$router.push('/admin/post');
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        }
    },
    beforeMount() {
        const url = window.location.pathname;
        const splitArr = url.split("/");
        this.postId = splitArr[splitArr.length - 1];

        axios.get(`/post/list/${this.postId}`)
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.postData = { ...res.data };
            }).catch((err) => {
                let errMsg = JSON.stringify(err.response.data.message);
                errMsg = errMsg.substring(1, errMsg.length - 1);
                console.log("errMsg -> " + errMsg);
                alert(errMsg);
            });
    },
}

</script>
 
<style>
input::placeholder {
    font-size: 14px;
}
</style>