<template>
    <div>
        <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
            <div class="bg-opacity-60 hero-overlay"></div>
            <div class="hero-content text-center flex w-1/4">
                <div class="card flex-shrink-0 max-w-2xl w-full shadow-2xl bg-base-100 mb-32">
                    <div class="card-body">
                        <h4 class="mb-3 text-center text-neutral-content fs-3 fw-bold">회원가입</h4>
                        
                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">아이디</span>
                            </label>
                            <input v-model="formData.loginId" type="text" placeholder="아이디"
                                class="input input-bordered text-neutral-content" />
                        </div>

                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">비밀번호</span>
                            </label>
                            <input v-model="formData.password" type="text" placeholder="비밀번호"
                                class="input input-bordered text-neutral-content" />
                            <!-- <label class="label">
                                <a href="#" class="label-text-alt link link-hover">Forgot password?</a>
                            </label> -->
                        </div>

                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">이름</span>
                            </label>
                            <input v-model="formData.name" type="text" placeholder="이름" 
                                class="input input-bordered text-neutral-content" />
                        </div>

                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">닉네임</span>
                            </label>
                            <input v-model="formData.nickname" type="text" placeholder="닉네임" 
                                class="input input-bordered text-neutral-content" />
                        </div>
                    
                        <div class="form-control mt-6">
                            <button @click="submitForm()" class="btn btn-primary">회원가입</button>
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
    name: 'userJoin',
    data() {
        return {
            formData: {
                loginId: '',
                password: '',
                name: '',
                age: '',
            },
        }
    },
    methods: {
        submitForm() {
            axios.post('/api/users', this.formData)
                .then((res) => {
                    console.log(res);
                    this.$router.push('/');
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });
        },
    },
}

</script>
 
<style>
input::placeholder {
    font-size: 14px;
}
</style>