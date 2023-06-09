import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import UserLogin from '@/views/user/UserLogin.vue'
import UserJoin from '@/views/user/UserJoin.vue'
import UserList from '@/views/user/UserList.vue'
import PostCreate from './views/post/PostCreate'
import PostList from './views/post/PostList'
import PostDetail from './views/post/PostDetail'
import AdminHome from './views/admin/AdminHome'
import AdminUser from './views/admin/AdminUserList.vue'
import AdminPost from './views/admin/AdminPostList.vue'
import AdminUserUpdate from './views/admin/AdminUserUpdate.vue'
import AdminPostUpdate from './views/admin/AdminPostUpdate'

const routes = [
  {
    path: "/",
    component: AppHome,
  },
  {
    path: "/login",
    component: UserLogin,
  },
  {
    path: "/user/join",
    component: UserJoin,
  },
  {
    path: "/user/list",
    component: UserList,
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
    path: "/admin/user",
    component: AdminUser,
  },
  {
    path: "/admin/user/update/:userId",
    component: AdminUserUpdate,
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