import { createRouter, createWebHistory } from 'vue-router';

// Importe seus componentes Vue
import TheHome from '../views/TheHome.vue'
import Cadastro from  '../views/Cadastro.vue'
import Login from  '../views/Login.vue'
import CadastroEmpresa from  '../views/CadastroEmpresa.vue'
import CadastroCliente from  '../views/CadastroCliente.vue'
import Painel from  '../views/Painel.vue'
import LoginEmp from  '../views/LoginEmp.vue'
import LoginAmbos from  '../views/LoginAmbos.vue'


const routes = [
  {
    path: '/',   
    component: TheHome
  },
  {
    path: '/cadastro',
    component: Cadastro
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/cadastroempresa',
    component: CadastroEmpresa
  },
  {
    path: '/cadastrocliente',
    component: CadastroCliente
  }
  ,
  {
    path: '/painel',
    component: Painel
  },
  {
    path: '/loginemp',
    component: LoginEmp
  },
  {
    path: '/loginambos',
    component: LoginAmbos
  }

];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;