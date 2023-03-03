import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router'
import store from './store.js'

let app = createApp(App);
app.config.globalProperties.window = window;

app.use(router).use(store).mount('#app');
