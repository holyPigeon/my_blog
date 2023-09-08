<template>
  <div>

    <div class="hero h-[60vh] bg-gradient-to-r from-sky-500 to-emerald-500">
      <div class="hero-overlay bg-opacity-60"></div>
      <div class="hero-content text-center text-neutral-content">
        <div class="max-w-md">
          <h1 class="mb-5 text-5xl font-bold">환상적인 게시판</h1>
          <p class="mb-5">말도 안되게 놀라운 CRUD를 경험해보세요.
            <br>
            쓰기, 읽기, 수정, 삭제 그 모든 것.
          </p>

          <div class="col-md-8 offset-md-2">
            <button @click="$router.push('/user/list')" class="btn btn-outline btn-primary py-3">
              <h6>회원 조회</h6>
            </button>
            <button @click="$router.push('/post/create')" class="btn btn-outline btn-primary mx-3 py-3">
              <h6>게시글 등록</h6>
            </button>
            <button @click="$router.push('/post/list')" class="btn btn-outline btn-primary py-3">
              <h6>게시글 조회</h6>
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="hero h-[60vh] bg-base-100">
      <div class="hero-content flex-col lg:flex-row">
        <img :src="require('../../assets/office1.jpg')" class="max-w-sm rounded-lg shadow-2xl" />
        <div class="text-neutral-content">
          <h1 class="text-5xl font-bold">미쳐버린 디자인</h1>
          <p class="py-6">본 게시판은 여러분에게 미쳐버린 디자인을 선사합니다. 이걸 목격한 여러분 역시 미쳐버릴 수 있으므로 주의합시다.</p>
          <!-- <button class="btn btn-secondary">Get Started</button> -->
        </div>
      </div>
    </div>

    <div class="hero h-[60vh] bg-base-100">
  <div class="hero-content flex-col lg:flex-row-reverse">
    <img :src="require('../../assets/office2.jpg')" class="max-w-sm rounded-lg shadow-2xl" />
    <div class="text-neutral-content">
      <h1 class="text-5xl font-bold">돌아버린 UI/UX</h1>
      <p class="py-6">본 게시판의 UI/UX 역시 돌아버리고 말았습니다. 이로 인해 지구 역시 1년에 한 번씩 태양 주위를 빙빙 돌고 있습니다.</p>
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
      isLogined: true,
      session: JSON.parse(sessionStorage.getItem("sessionData")),
    }
  },
}
</script>

<style scoped></style>
