import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import MemberJoin from './views/member/MemberJoin'
import MemberList from './views/member/MemberList'
import PostCreate from './views/post/PostCreate'
import PostList from './views/post/PostList'
import PostListDetail from './views/post/PostListDetail'


const routes = [
  {
    path: "/",
    component: AppHome,
  },
  {
    path: "/member/join",
    component: MemberJoin,
  },
  {
    path: "/member/list",
    component: MemberList,
  },
  {
    path: "/post/create",
    component: PostCreate,
  },
  {
    path: "/post/list",
    component: PostList,
  },
  {
    path: "/post/list/:postId",
    component: PostListDetail,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;