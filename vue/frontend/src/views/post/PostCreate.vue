<template>
    <div class="hero min-h-screen bg-gradient-to-r from-sky-500 to-emerald-500">
        <div class="bg-opacity-60 hero-overlay"></div>
        <div class="hero-content text-center flex w-3/4 text-neutral-content">
            <div class="card flex-shrink-0 max-w-4xl w-full shadow-2xl bg-base-100 mb-64 p-8">
                
                    <div class="p-4">
                        <!-- style="--bs-text-opacity: 0.6;" -->
                        <h4 class="mb-3 text-center text-neutral-content fs-3 fw-bold">게시글 등록</h4>
                    </div>

                <div class="card-body overflow-x-auto">

                    <div class="form-control">
                        <label class="label">
                            <span class="label-text">제목</span>
                        </label>
                        <input v-model="post.title" type="text" placeholder="제목"
                            class="input input-bordered text-neutral-content" />
                    </div>

                    <div class="form-control">
                        <label class="label">
                            <span class="label-text">내용</span>
                        </label>
                        <textarea v-model="post.content" class="textarea textarea-bordered text-lg" rows="3"
                            style="text-indent: 0.5%; padding-top: 1%; resize: none;" placeholder="내용을 입력하세요."></textarea>
                    </div>

                    <div class="form-control mt-6 flex items-center">
                            <button @click="submitForm()" class="btn btn-primary w-1/6">완료</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'postCreate',
    data() {
        return {
            post: {
                author: JSON.parse(sessionStorage.getItem("sessionData")).nickname,
                title: '',
                content: '',
            },
            userList: {
                count: -1,
                data: [
                    {
                        //     id: '',
                        //     name: '',
                        //     nickname: '',
                    }
                ]
            }
        }
    },
    methods: {
        submitForm() {
            const data = this.post;
            console.log("data: " + JSON.stringify(data));
            axios.post('/api/posts', data)
                .then((res) => {
                    console.log(res);
                    this.$router.push('/');
                }).catch((err) => {
                    let errMsg = JSON.stringify(err.response.data.message);
                    errMsg = errMsg.substring(1, errMsg.length - 1);
                    console.log("errMsg -> " + errMsg);
                    alert(errMsg);
                });

            // const data = this.formData;
            // const url = `/api/join/user`;
            // const options = {
            //     method: 'POST',
            //     headers: { 'content-type': 'application/x-www-form-urlencoded' },
            //     data: qs.stringify(data),
            //     url,
            // };
            // axios(options)
            //     .then((res) => {
            //         console.log(res);
            //     }).catch(() => {
            //         console.log('fail');
            //     });
        },
        test() {
            console.log("author: " + this.post.author);
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
::placeholder {
    font-size: 18px;
}
</style>