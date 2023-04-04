<template>
  <div>
    <h1></h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3><a href="#" @click="$router.push('/')" class="nav-link active">This is Home</a></h3>
    <div class="row g-3">
      <div class="col-md-6 offset-md-3">
        <div class="row mt-3">

          <div class="col-md-4 offset-md-4">
            <div class="btn-group">
              <button class="btn btn-outline-primary py-3">
                <h6 @click="$router.push('/member/join')">회원 가입</h6>
              </button>
              <button class="btn btn-outline-primary py-3">
                <h6 @click="$router.push('/member/list')">회원 조회</h6>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-6 offset-md-3">
        <div class="row">

          <div class="col-md-4 offset-md-4">
            <div class="btn-group">
              <button class="btn btn-outline-primary py-3">
                <h6 @click="$router.push('/post/create')">게시글 등록</h6>
              </button>
              <button class="btn btn-outline-primary py-3">
                <h6 @click="$router.push('/post/list')">게시글 조회</h6>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'appHome',
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
