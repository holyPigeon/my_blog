import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import MemberLogin from '@/views/user/UserLogin.vue'
import MemberJoin from '@/views/user/UserJoin.vue'
import MemberList from '@/views/user/UserList.vue'
import PostCreate from './views/post/PostCreate'
import PostList from './views/post/PostList'
import PostDetail from './views/post/PostDetail'
import AdminHome from './views/admin/AdminHome'
import AdminMember from './views/admin/AdminUserList.vue'
import AdminPost from './views/admin/AdminPostList.vue'
import AdminMemberUpdate from './views/admin/AdminUserUpdate.vue'
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