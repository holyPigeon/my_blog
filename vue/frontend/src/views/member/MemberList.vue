<template>
    <div id="wrapper" class="container-fluid">
        <img alt="Vue logo" src="../../assets/logo.png">
        <div class="container px-4s my-5">
            <div class="row gx-4 justify-content-center m-auto">
                <div class="col-md-10 position-static d-block p-3 text-black"
                    style="--bs-bg-opacity: .4; --bs-text-opacity: 0.6;">
                    <div class="p-4">
                        <!-- style="--bs-text-opacity: 0.6;" -->
                        <h4 class="mb-3 text-center text-black fs-3 fw-bold">회원 목록</h4>
                    </div>

                    <table class="text-center fs-4" style="width: 100%; border: 1px solid #444444;">
                        <tr>
                            <td>Member Id</td>
                            <td>Member Name</td>
                        </tr>
                        <tr v-for="(member, index) in memberList.userData" :key="index">
                            <td>{{ member.id }}</td>
                            <td>{{ member.name }}</td>
                        </tr>
                    </table>

                    <!-- <div class="row">
                            <hr class="my-4 col-md-12 offset-md-0 border border-1 border-dark" style="opacity: 0.1;">
                        </div> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'memberList',
    data() {
        return {
            memberList: {
                count: -1,
                userData: [
                    {
                        // id: '',
                        // name: '',
                    }
                ],
            }
        }
    },
    // methods: {
    //     submitForm() {
    //         const data = this.formData;
    //         axios.post('/member/join', data)
    //             .then((res) => {
    //                 console.log(res);
    //                 this.$router.push('/');
    //             }).catch((err) => {
    //                 let errMsg = JSON.stringify(err.response.data.message);
    //                 errMsg = errMsg.substring(1, errMsg.length - 1);
    //                 console.log("errMsg -> " + errMsg);
    //                 alert(errMsg);
    //             });
    //     },
    // },
    beforeMount() {
        axios.get('/member/list')
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.memberList.count = res.data.count;
                this.memberList.userData = [...res.data.data];
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
</style>