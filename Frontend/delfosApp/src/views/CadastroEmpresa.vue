<template>

  <div class="card">
    <Card>
      <template #title>
        <h2  style="
        margin-bottom: 5rem;
        margin-left: 2rem;
        display: flex;
        justify-content: center;
        align-content: center;
      "> Cadastro de Empresa</h2>
      </template>
      <template #content>
        <form class="flex flex-column aligin-content-center align-items-center justify-content-center">

          <div class=" field w-5 m-3 mb-3">
            <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                <P class="mr-1">Empresa</P>
                <i class="pi pi-user-plus"></i>
              </span>
              <InputText placeholder="Nome" v-model="empresa.razaoSocial" required />
            </div>
          </div>
          <div class="field w-5 m-3">
            <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                <P class="mr-1">Telefone</P>
                <i class="pi pi-phone"></i>
              </span>
              <InputText placeholder="Telefone" v-model="empresa.telefone" required />
            </div>
          </div>
          <div class=" field w-5 m-3 mb-3">
            <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                <P class="mr-1">Diretor</P>
                <i class="pi pi-user-plus"></i>
              </span>
              <InputText placeholder="Nome" v-model="empresa.responsavel" required />
            </div>
          </div>
          <div class=" field w-5 m-3 mb-3">
            <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                <P class="mr-1">CNPJ ou CPF</P>
                <i class="pi pi-user-plus"></i>
              </span>
              <InputText placeholder="Nome" v-model="empresa.cpfCnpj" required />
            </div>
          </div>
          <div class="field w-5 m-3">
            <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                <P class="mr-1">Email</P>
                <i class="pi pi-envelope"></i>
              </span>
              <InputText placeholder="Email" v-model="empresa.email" required />
            </div>
          </div>
    
          <div class="field w-5 m-3">
            <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                <P class="mr-1">Senha</P>
                <i class="pi pi-lock"></i>
              </span>
              <Password v-model="empresa.password" toggleMask />
            </div>
          </div>
          <Button
        label="Cadastrar"
        icon="pi pi-check"
        class="w-2 ml-3 mb-8"
        @click="addEmpresa"
      />

        </form>

      </template>
    </Card>
  </div>
  
</template>

<script>
import { transform } from "@vue/compiler-core";
import axios from 'axios';

export default {
  name: "CadastroEmpresa",
  created() {},
  data() {
    return {
      empresa: {
        razaoSocial: '',
        telefone: '',
        email: '',
        responsavel: '',
        cpfCnpj: '',
        password: ''

      }
    };
  },
  props: {},
  methods: {
    async loadData() {
      location.reload();
    },

    async addEmpresa() {
      const resultado = await axios
        .post("http://localhost:8080/api/empresa", this.empresa)
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

<style ></style>