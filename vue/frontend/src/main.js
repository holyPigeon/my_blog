import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store.js'
import './tailwind.css'

let app = createApp(App);
app.config.globalProperties.window = window;

app.use(router).use(store).mount('#app');
