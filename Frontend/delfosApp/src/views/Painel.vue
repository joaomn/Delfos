<template>
  <div class="card">
  <div class="flex align-items-start justify-content-start" :style="{ position: 'relative', margin: '10px' }">
      <Toast />
      <SpeedDial :model="items" direction="down" :transitionDelay="80" showIcon="pi pi-bars" hideIcon="pi pi-times" buttonClass="p-button-outlined" />
  </div>
</div>
  <div class="flex justify-content-center m-4 ">
  <h1>Painel do Usuario</h1>
</div>

<div class="card" v-if="todos">
  <DataTable :value="anuncioss" tableStyle="min-width: 40rem">
      <template #header>
          <div class="flex flex-wrap align-items-center justify-content-between gap-2">
              <span class="text-xl text-900 font-bold">Ultimos Anuncios</span>
              <Button icon="pi pi-refresh" rounded raised @click="getAnuncios" />
          </div>
      </template>
      
      <Column header="">
          <template #body="slotProps">
              <img src="../icones/man-303792_1280.png" class="w-6rem shadow-8 border-round" />
          </template>
      </Column>
      <Column field="titulo" header="Nome"></Column>
      <Column field="texto" header="Descrição"></Column>

      <Column field="valor" header="Preço">      
      </Column>
      <Column field="tipo" header="Categoria"></Column>
      <Column field="telefone" header="Telefone"> </Column>
        <Column field="email" header="Email"></Column>
        <Column   id="coluna botao" header=" ">
          <template #body>
          <Button label="Comentarios" /> 
          </template>
        </Column>
         
     
      
      <template #footer> Exibindo um total de {{ anuncioss ? anuncioss.length : 0 }} Anuncios. </template>
  </DataTable>
</div>


<div class="card w-8" v-if="pesquisar" style="margin-left: 9rem;" >
<div class="flex justifi-content-center aligin-itens-center ml-5">
  <div class="card flex justify-content-center">
   
</div>
  <div class="p-inputgroup flex-1 mb-5">
    <InputText placeholder="O que você está procurando?" v-model="palavraBusca"/>
    <Button icon="pi pi-search" @click="getPorTitulo"/>
</div>
  </div>
 <DataTable :value="pesquisados" tableStyle="min-width: 40rem">
      <template #header>
          <div class="flex flex-wrap align-items-center justify-content-between gap-2">
              <span class="text-xl text-900 font-bold">Ultimos Anuncios</span>
              <Button icon="pi pi-refresh" rounded raised @click="getAnuncios" />
          </div>
      </template>
      
      <Column header="">
          <template #body="slotProps">
              <img src="../icones/man-303792_1280.png" class="w-6rem shadow-8 border-round" />
          </template>
      </Column>
      <Column field="titulo" header="Nome"></Column>
      <Column field="texto" header="Descrição"></Column>

      <Column field="valor" header="Preço">      
      </Column>
      <Column field="tipo" header="Categoria"></Column>
      <Column field="telefone" header="Telefone"> </Column>
        <Column field="email" header="Email"></Column>
        <Column   id="coluna botao" header=" ">
          <template #body>
          <Button label="Comentarios" /> 
          </template>
        </Column>
         
     
      
      <template #footer> Exibindo um total de {{ pesquisados ? pesquisados.length : 0 }} Anuncios. </template>
  </DataTable>
  
  
</div>







</template>

<script>
import { ref } from 'vue';
import axios from "axios";
import Cookies from "js-cookie";
import { transform } from '@vue/compiler-core';
import { useToast } from 'primevue/usetoast';
import { useRouter } from 'vue-router';
import router from '../router';

export default {
  name: "Painel",

  

  
  data() {
    return {
      items : ref([
  {
      label: 'Inicial',
      icon: 'pi pi-home',
      command: () => {
         this.todos = true;
         this.pesquisar = false;
         

      }
  },
  {
      label: 'Update',
      icon: 'pi pi-search',
      command: () => {
        this.todos = false;
         this.pesquisar = true;
      }
  },
  {
      label: 'Delete',
      icon: 'pi pi-power-off',
      command: () => {
         router.push('/')
      }
  }
  
]),
 categories : ref([
    { name: 'tipo', key: 'A' },   
    { name: 'titulo', key: 'P' }
   
]),
 
      

      data:{
        dtPostagem: '',
        email: '',
        id: '',
        telefone: '',
        texto: '',
        tipo:'',
        valor:'',
        titulo:'',
        empresa:{id:''}
      },

      anuncioss:[],

      pesquisados:[],

      palavraBusca: '',

      
categoria: '',
     
todos : true,
      pesquisar : false
     
     
      
    };
  },
  async mounted() {
        this.getAnuncios()
         

    },
  props: {
    
    
  },
  methods: {
    async resultado() {
      const usuario = await axios
        .get("http://localhost:8080/api/cliente/alguem/" + Cookies.get("cliente"))
        .then((resultado) => {
          const pessoa = resultado.data;
          console.log(pessoa);
          
        })
        .catch((erro) => {
          alert("Pessoa não encontrada");
        });
    },
async getAnuncios(){

  await axios
  .get("http://localhost:8080/api/anuncio")
  .then(anuncios =>{
    this.anuncioss = anuncios.data;
    
  })
  .catch(erro =>{
    console.log(erro);
  })

},

async getPorTitulo(){
  console.log("aqui foi");
  await axios
  .get("http://localhost:8080/api/anuncio/titulo/" + this.palavraBusca )
  .then(pesq =>{
    this.pesquisados = pesq.data;
    console.log(palavraBusca);
  })
  .catch(erro =>{
    console.log(erro)
    console.log(palavraBusca);
  })

  
  
}




  },





};







</script>

<style >



</style>