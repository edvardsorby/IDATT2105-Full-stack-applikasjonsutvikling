import { createRouter, createWebHistory } from 'vue-router'
import LogInView from '../views/LogInView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: LogInView
    },
    {
      path: '/info',
      name: 'info',
      component: () => import('../views/TestView.vue')
    },
    {
      path: '/kalkulator',
      name: 'kalkulator',
      component: () => import('../views/CalculatorView.vue')
    },
    {
      path: '/kontaktskjema',
      name: 'kontaktskjema',
      component: () => import('../views/ContactFormView.vue')
    }
  ]
})

export default router