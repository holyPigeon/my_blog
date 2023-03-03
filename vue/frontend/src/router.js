import { createWebHistory, createRouter } from "vue-router"
import AppHome from './views/home/AppHome'


const routes = [
  {
    path: "/",
    component: AppHome,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;