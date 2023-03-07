<template>
  <div>
    <img alt="Vue logo" src="../../assets/logo.png">
    <h1></h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3><a href="#" @click="$router.push('/')" class="nav-link active">This is Home</a></h3>
    <div class="row g-3">
      <div class="col-md-2 offset-md-4">
        <!-- <button @click="login" class="btn btn-outline-primary btn-lg col-md-12 p-2">로그인</button> -->
        <h4 @click="$router.push('/member/join')"><button class="btn btn-outline-primary py-3 m-3">회원 가입</button></h4>
      </div>
      <div class="col-md-2">
        <h4 @click="$router.push('/member/list')"><button class="btn btn-outline-primary py-3 m-3">회원 조회</button></h4>
      </div>
    </div>
    <div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'appHome',
  data() {
    return {

    }
  }
}
</script>

<style scoped></style>
