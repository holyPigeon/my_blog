<template>
  <div>
    <h1></h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3><a href="#" @click="$router.push('/')" class="nav-link active">This is Home</a></h3>
    <div class="row g-3 mt-3">

      <div class="col-md-4 offset-md-4">
        <h4 class="py-3" v-if="Object.keys(session).length !== 0">로그인 사용자
          <span>
            <h6 >{{ session.name }} | {{ session.nickname }}</h6>
          </span>
        </h4>
      </div>

      <div class="col-md-8 offset-md-2">
        <div class="btn-group">
          <button v-if="Object.keys(session).length === 0" @click="login" class="btn btn-outline-success py-3">
            <h6>로그인</h6>
          </button>
          <button v-if="Object.keys(session).length === 0" @click="$router.push('/user/join')" class="btn btn-outline-success py-3">
            <h6>회원 가입</h6>
          </button>
          <button v-if="Object.keys(session).length !== 0" @click="logout" class="btn btn-outline-success py-3">
            <h6>로그아웃</h6>
          </button>
          
        </div>
      </div>

      <div class="col-md-4 offset-md-4">
        <div class="btn-group">
          <button @click="$router.push('/user/list')" class="btn btn-outline-primary py-3">
            <h6>회원 조회</h6>
          </button>
          <button @click="$router.push('/post/create')" class="btn btn-outline-primary py-3">
            <h6>게시글 등록</h6>
          </button>
          <button @click="$router.push('/post/list')" class="btn btn-outline-primary py-3">
            <h6>게시글 조회</h6>
          </button>
        </div>
      </div>

      <div class="col-md-4 offset-md-4">
        <button @click="$router.push('/admin')" class="btn btn-outline-danger py-3">
          <h6>관리자 콘솔</h6>
        </button>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'appHome',
  data() {
    return {
      isLogined: true,
      session: {
        // id: 1,
        // loginId: admin,
        // name: kevin,
        // age: 20,
      }
    }
  },
  methods: {
    login() {
      this.$router.push('/login')
    },
    logout() {
      axios.post(`/logout`)
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
    axios.get(`/session`)
      .then((res) => {
        console.log(res);
        if (res.data.id !== -1) {
          this.session = { ...res.data };
        }
      }).catch((err) => {
        let errMsg = JSON.stringify(err.response.data.message);
        errMsg = errMsg.substring(1, errMsg.length - 1);
        console.log("errMsg -> " + errMsg);
        // alert(errMsg);
      });
  }
}
</script>

<style scoped></style>
