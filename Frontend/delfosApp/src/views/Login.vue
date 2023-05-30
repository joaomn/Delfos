<template>
  <div class="flex justify-content-center m-8">
  <div class="surface-card p-4 shadow-2 border-round w-full lg:w-6">
    <div class="text-center mb-5">
        <img src="../assets/WhatsApp Image 2023-05-21 at 18.51.50.jpeg" alt="Image" height="50" class="mb-3">
        <div class="text-900 text-3xl font-medium mb-3">Seja Bem Vindo</div>
        <span class="text-600 font-medium line-height-3">Não tem uma conta?</span>
       <router-link class="font-medium no-underline ml-2 text-blue-500 cursor-pointer" to="/cadastro">Clique aqui!</router-link>
    </div>

    <form>
        <label for="email1" class="block text-900 font-medium mb-2">Email</label>
        <InputText id="email1" type="text" v-model="email" class="w-full mb-3" />

        <label for="password1" class="block text-900 font-medium mb-2">Password</label>
        <InputText id="password1" type="password" v-model="password" class="w-full mb-3" toggleMask />

       

        <Button label="Acessar" icon="pi pi-user" class="w-full" @click="Userlogin"></Button>
    </form>
</div>
</div>
</template>

<script>
import Cookies from 'js-cookie';
import axios from 'axios';
import { AlternateEmail } from '@mui/icons-material';
export default {
  name: "Login",
  created() {},
  data() {
    return {
     
        email: '',
        password: ''
     
    };
  },
  props: {},
  methods: {
    async Userlogin(){
      const resultado = await axios
      .post("http://localhost:8080/api/cliente/login", {
        email: this.email,
        password: this.password
      })
      .then(result => {
        Cookies.set('cliente', this.email, { expires: 12/24 });        
        alert("Logado com Sucesso");
        this.$router.push('/painel');
        console.log(Cookies.get('cliente'));
        
      })
      .catch(erro => {
        alert('Usuario e/ou senha incorretos')
        location.reload();

      });

      
    }
  },
};
</script>

<style lang="scss" scoped></style>