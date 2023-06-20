<template>
  <div class="flex justify-content-center m-8">
    <div class="surface-card p-4 shadow-2 border-round w-full lg:w-6">
    
    <h2 class="text-900 text-3xl font-medium mb-3"
      style="
        margin-bottom: 5rem;
        margin-left: 2rem;
        display: flex;
        justify-content: center;
        align-content: center;
      "
    >
      Cadastrar Cliente
    </h2>
 
    <form
      class="flex flex-column aligin-content-center align-items-center justify-content-center"
    > <div class="field " style=" width: 70%;">
          <div class="p-inputgroup">
            <span class="p-inputgroup-addon">
              <h4 class="mr-1"> Data de Nascimento</h4>
          
              <i class="pi pi-calendar-times"></i>
            </span>
            <Calendar v-model="cliente.dtNascimento" required showIcon />
          </div>
        </div>
   
      <div class=" field w-7 m-3 mb-3">
        <div class="p-inputgroup">
          <span class="p-inputgroup-addon">
            <P class="mr-1">Nome</P>
            <i class="pi pi-user-plus"></i>
          </span>
          <InputText placeholder="Nome" v-model="cliente.nome" required />
        </div>
       
      </div>

      <div class="field w-7 m-3">
        <div class="p-inputgroup">
          <span class="p-inputgroup-addon">
            <P class="mr-1">Telefone</P>
            <i class="pi pi-phone"></i>
          </span>
          <InputText placeholder="Telefone" v-model="cliente.telefone" required />
        </div>
      </div>

      <div class="field w-7 m-3">
        <div class="p-inputgroup">
          <span class="p-inputgroup-addon">
            <P class="mr-1">Email</P>
            <i class="pi pi-envelope"></i>
          </span>
          <InputText placeholder="Email" v-model="cliente.email" required />
        </div>
      </div>

      <div class="field w-7 m-3">
        <div class="p-inputgroup">
          <span class="p-inputgroup-addon">
            <P class="mr-1">Senha</P>
            <i class="pi pi-lock"></i>
          </span>
          <Password v-model="cliente.password" toggleMask />
        </div>
      </div>
 
      <Button
        label="Cadastrar"
        icon="pi pi-check"
        class="w-4 ml-3 mb-8"
        @click="addCliente"
      />
    </form>
 
  </div>
</div>
</template>

<script>
import { transform } from "@vue/compiler-core";
import axios from "axios";

export default {
  name: "CadastroCliente",
  created() {},
  data() {
    return {
      cliente: {
        nome: "",
        email: "",
        telefone: "",
        password: "",
        dtNascimento: "",
      },
    };
  },

  props: {},
  methods: {
    async loadData() {
      location.reload();
    },

    async addCliente() {
      const resultado = await axios
        .post("http://localhost:8080/api/cliente", this.cliente)
        .then((result) => {
          alert("Cadastrado com Sucesso!");
          this.$router.push('/login');
        })
        .catch((erro) => {
          alert("Dados incorretos ou já cadastrados"         
          );
        });
    },
  },
};
</script>

<style></style>
