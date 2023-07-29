<template>
  <div>

    <div class="hero h-screen h-[50vh] bg-gradient-to-r from-sky-500 to-emerald-500">
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

    <div class="hero h-screen h-[50vh] bg-base-100">
      <div class="hero-content flex-col lg:flex-row">
        <img :src="require('../../assets/office1.jpg')" class="max-w-sm rounded-lg shadow-2xl" />
        <div class="text-neutral-content">
          <h1 class="text-5xl font-bold">미쳐버린 디자인</h1>
          <p class="py-6">본 게시판은 여러분에게 미쳐버린 디자인을 선사합니다. 이걸 목격한 여러분 역시 미쳐버릴 수 있기 떄문에 주의해야 합니다.</p>
          <!-- <button class="btn btn-secondary">Get Started</button> -->
        </div>
      </div>
    </header>
    <!-- Features section-->
    <section class="py-5 border-bottom" id="features">
      <div class="container px-5 my-5">
        <div class="row gx-5">
          <div class="col-lg-4 mb-5 mb-lg-0">
            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-collection"></i></div>
            <h2 class="h4 fw-bolder">Featured title</h2>
            <p>Paragraph of text beneath the heading to explain the heading. We'll add onto it with another sentence and
              probably just keep going until we run out of words.</p>
            <a class="text-decoration-none" href="#!">
              Call to action
              <i class="bi bi-arrow-right"></i>
            </a>
          </div>
          <div class="col-lg-4 mb-5 mb-lg-0">
            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-building"></i></div>
            <h2 class="h4 fw-bolder">Featured title</h2>
            <p>Paragraph of text beneath the heading to explain the heading. We'll add onto it with another sentence and
              probably just keep going until we run out of words.</p>
            <a class="text-decoration-none" href="#!">
              Call to action
              <i class="bi bi-arrow-right"></i>
            </a>
          </div>
          <div class="col-lg-4">
            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-toggles2"></i></div>
            <h2 class="h4 fw-bolder">Featured title</h2>
            <p>Paragraph of text beneath the heading to explain the heading. We'll add onto it with another sentence and
              probably just keep going until we run out of words.</p>
            <a class="text-decoration-none" href="#!">
              Call to action
              <i class="bi bi-arrow-right"></i>
            </a>
          </div>
        </div>
      </div>
    </section>

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
