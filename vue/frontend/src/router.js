import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import MemberLogin from './views/member/MemberLogin'
import MemberJoin from './views/member/MemberJoin'
import MemberList from './views/member/MemberList'
import PostCreate from './views/post/PostCreate'
import PostList from './views/post/PostList'
import PostDetail from './views/post/PostDetail'
import AdminHome from './views/admin/AdminHome'
import AdminMember from './views/admin/AdminMember'
import AdminPost from './views/admin/AdminPost'
import AdminMemberUpdate from './views/admin/AdminMemberUpdate'
import AdminPostUpdate from './views/admin/AdminPostUpdate'


const routes = [
  {
    path: "/",
    component: AppHome,
  },
  {
    path: "/login",
    component: MemberLogin,
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
    component: PostDetail,
  },
  {
    path: "/admin/",
    component: AdminHome,
  },
  {
    path: "/admin/member",
    component: AdminMember,
  },
  {
    path: "/admin/member/update/:memberId",
    component: AdminMemberUpdate,
  },
  {
    path: "/admin/post",
    component: AdminPost,
  },
  {
    path: "/admin/post/update/:postId",
    component: AdminPostUpdate,
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;