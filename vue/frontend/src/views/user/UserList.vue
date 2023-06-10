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

                    <table class="text-center fs-4" style="width: 100%; border: 1px solid #CCCDCE;">
                        <tr>
                            <td>User Id</td>
                            <td>User Name</td>
                            <td>User Nickname</td>
                        </tr>
                        <tr v-for="(user, index) in userList.data" :key="index">
                            <td>{{ user.id }}</td>
                            <td>{{ user.name }}</td>
                            <td>{{ user.nickname }}</td>
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
    name: 'userList',
    data() {
        return {
            userList: {
                count: -1,
                data: [
                    {
                        // id: '',
                        // name: '',
                        // age: '',
                    }
                ],
            }
        }
    },
    beforeMount() {
        axios.get('/users')
            .then((res) => {
                console.log(res);
                // JSON.stringify("res => " + res);
                this.userList.count = res.data.count;
                this.userList.data = [...res.data.data];
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