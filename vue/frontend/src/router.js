import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import MemberJoin from './views/member/MemberJoin'


const routes = [
  {
    path: "/",
    component: AppHome,
  },
  {
    path: "/member/join",
    component: MemberJoin,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;