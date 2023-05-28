<template>
  <div class="flex justify-content-end align-content-center m-1 ml-0  " style="border-bottom: 1px solid black; width: 100%;">
    <router-link to="/login"    style="color: black; font-size: medium; font-weight: 900;margin-right: 5px; top: 0%; margin-bottom: 2px;">  Já é cadastrado? então faça o Login</router-link>
  </div>

  <div class="flex align-content-center justify-content-start m-4 ">
    <h1 style="font-size: 60px; font-weight: 900;">Cadastro</h1>
  </div>

  <div class="flex flex-row gap-2 " style="margin-bottom: 18rem;">
    <div class="flex mb-4 flex-column ml-2" style="width: 50%;">
      <p style="margin: 10px; font-size: larger; font-weight: 600;">Caso você seja um Empresário, MEI, PJ ou seja prestador de serviço informal (apenas tendo o <i><u>CPF</u></i>)
        clique no botão abaixo para cadastrar sua empresa e poder <b>anunciar</b>  seus serviços em nossa plataforma.
      </p>
      <router-link to="/cadastroempresa"><Button label="Empresa" icon="pi pi-briefcase" style="background: #042f66 ; border: 0; margin: 10px; width: 300px; height: 4rem;" severity="secondary" /></router-link>
    </div>
    <div class="flex mb-4 flex-column" style="width: 50%;">
      <p style="margin: 10px; font-size: larger; font-weight: 600;">Se você deseja encontrar algum serviço e usar todas as ferramentas que
        a plataforma oferece (Como deixar comentários por exemplo). Clique no Botão abaixo e cadastre-se é <i><u>GRÁTIS</u></i>
      </p>
      <router-link to="/cadastrocliente"><Button label="Cliente" icon="pi pi-user-plus" style="background: #F45D2D ; border: 0; margin: 10px; width: 300px; height: 4rem;" severity="secondary" /></router-link>
    </div>
    

  </div>
</template>

<script>
export default {
  name: "Cadastro",
  created() {},
  data() {
    return {};
  },
  props: {},
  methods: {},
};
</script>

<style >

</style>