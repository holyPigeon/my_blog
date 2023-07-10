<template>
  <div style="margin: -50px;">

    <!-- Header-->
    <header class="bg-dark py-5" style="padding-top: 100px;">
      <div class="container px-5">
        <div class="row gx-5 justify-content-center">
          <div class="col-lg-6">
            <div class="text-center my-5">
              <h1 class="display-5 fw-bolder text-white mb-2">환상적인 게시판</h1>
              <h5 class="text-white-50 mb-4 mt-3">말도 안되게 놀라운 CRUD를 경험해보세요.
                <br>
                쓰기, 읽기, 수정, 삭제 그 모든 것.</h5>
              <div class="row g-3 mt-3">

                <div class="col-md-8 offset-md-2">
                  <button @click="$router.push('/user/list')" class="btn btn-outline-light   py-3">
                    <h6>회원 조회</h6>
                  </button>
                  <button @click="$router.push('/post/create')" class="btn btn-outline-light mx-3 py-3">
                    <h6>게시글 등록</h6>
                  </button>
                  <button @click="$router.push('/post/list')" class="btn btn-outline-light   py-3">
                    <h6>게시글 조회</h6>
                  </button>
                </div>

              </div>
            </div>
          </div>
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
