import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import MemberJoin from './views/member/MemberJoin'
import MemberList from './views/member/MemberList'


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
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;