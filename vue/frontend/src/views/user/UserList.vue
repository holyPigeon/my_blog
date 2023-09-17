<template>
    <div class="hero h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">
            <div class="flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                <div class="overflow-x-auto">
                    <div class="p-4">
                        <!-- style="--bs-text-opacity: 0.6;" -->
                        <h4 class="mb-3 text-center fs-3 fw-bold">회원 목록</h4>
                    </div>

                    <table class="text-center fs-4" style="width: 100%; border: 1px solid #CCCDCE;">
                        <tr style="border: 1px solid #CCCDCE;">
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
        axios.get('/api/users')
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