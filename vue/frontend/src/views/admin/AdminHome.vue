<template>
    <div>
        <h1></h1>
        <p>
            For a guide and recipes on how to configure / customize this project,<br>
            check out the
            <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
        </p>
        <h3><a href="#" @click="$router.push('/')" class="nav-link active">This is Admin Home</a></h3>
        <div class="row g-3 mt-3">
            <div class="col-md-4 offset-md-4">
                <div class="btn-group">
                    <button class="btn btn-outline-primary py-3">
                        <h6 @click="$router.push('/admin/member')">회원 관리</h6>
                    </button>
                </div>
            </div>
            <div class="col-md-4 offset-md-4">
                <div class="btn-group">
                    <button class="btn btn-outline-primary py-3">
                        <h6 @click="$router.push('/admin/post')">게시글 관리</h6>
                    </button>
                </div>
            </div>
        </div>

    </div>
</template>
  
<script>

export default {
    name: 'adminHome',
    data() {
        return {

        }
    },
    methods: {
        // test() {
        //   const postId = window.location.pathname; 
        //   console.log("postId => " + postId);
        // }
    }
}
</script>
  
<style scoped></style>
  