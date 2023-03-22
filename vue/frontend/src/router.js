import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'
import MemberJoin from './views/member/MemberJoin'
import MemberList from './views/member/MemberList'
import ItemList from './views/item/ItemList'


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
    path: "/item/list",
    component: ItemList,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;