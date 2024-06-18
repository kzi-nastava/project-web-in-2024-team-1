import { createRouter, createWebHistory } from 'vue-router'
import HomePageView from '../views/HomePageView.vue'
import LoginView from '../views/LoginView.vue'

import AdminView from '../views/AdminView.vue'
import SellerView from '../views/SellerView.vue'
import CustomerView from '../views/CustomerView.vue'
import RegisterView from '../views/RegisterView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePageView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/seller',
    name: 'seller',
    component: SellerView
  },
  {
    path: '/customer',
    name: 'customer',
    component: CustomerView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
