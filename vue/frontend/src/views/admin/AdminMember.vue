<template>
    <div id="wrapper" class="container-fluid">
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
                            <td>Member Age</td>
                            <td>수정</td>
                            <td>삭제</td>
                        </tr>
                        <tr v-for="(member, index) in memberList.memberData" :key="index">
                            <td>{{ member.id }}</td>
                            <td>{{ member.name }}</td>
                            <td>{{ member.age }}</td>
                            <td><h6 @click="$router.push(`/admin/member/update/${member.id}`)" class="btn btn-outline-primary">수정</h6></td>
                            <td><h6 @click="deleteMember(member.id)" class="btn btn-outline-danger">삭제</h6></td>
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
    name: 'adminMember',
    data() {
        return {
            memberList: {
                count: -1,
                memberData: [
                    {
                        // id: '',
                        // name: '',
                        // age: '',
                    }
                ],
            }
        }
    },
    methods: {
        deleteMember(memberId) {
            axios.delete(`/member/delete/${memberId}`)
                .then((res) => {
                    console.log(res);
                    location.reload();
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
    },
    beforeMount() {
        axios.get('/member/list')
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.memberList.count = res.data.count;
                this.memberList.memberData = [...res.data.data];
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