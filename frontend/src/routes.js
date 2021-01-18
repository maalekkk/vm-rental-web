import Vue from 'vue';
import Router from 'vue-router';
import ManageUser from './views/ManageUser.vue';
import ShowUsers from './views/ShowUsers.vue';
import About from "./views/About.vue";
import HomePage from "./views/HomePage.vue";

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'homePage',
      component: HomePage,
    },
    {
      path: '/showUsers',
      name: 'showUsers',
      component: ShowUsers,
    },
    {
      path: '/manageUser',
      name: 'manageUser',
      component: ManageUser,
      props: true
    },
    {
      path: '/about',
      name: 'about',
      component: About,
    }

  ]
});