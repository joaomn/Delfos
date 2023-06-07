<template>
  <div style="border-bottom: 0.9px solid black">
    <div
      id="cadastro-login"
      class="flex flex-row justify-content-end gap-2 m-2 ml-0 mt-4 font-bold font-xl  w-full"
    >
      <router-link id="castre" class="cursor-pointer" to="/cadastro" style="color: black;"> Cadastre-se </router-link>
      <p>|</p>
      <router-link class="cursor-pointer mr-2" to="/loginambos" style="color: black;"> Login</router-link>
    </div>
  </div>
  <br />
  <div class="flex " >
  <div class="flex  flex-column w-full m-8 h-6rem mb-1 text-xl font-bold" style="border-bottom: px solid black;">
    <h1 style="margin-bottom: 2px;">Encontre Profissionais de Confiança</h1>
    
  <div class="flex w-full m-2 h-auto font-semibold text-overflow-ellipsis">
    Pintores, Marceneiros, Encanadores, Babá, Jardineiros, Pedreiros... Encontre quaquer
    tipo de prestador de serviço em nosso site.
</div>
  </div>
 
    <div class="flex justify-content-end w-full  mb-0 " style="margin-right: 2rem;">
      <img src="../assets/gdgggggggggggggggggggggg 1.png" alt="" style="mix-blend-mode: multiply;" />
    </div>
</div>

<div class="flex flex-clumn m-4 mb-1">
    <div>
      <h3>Encontre um serviço aqui: </h3>  
    </div>
    </div>
    <div class="flex ml-4 gap-2 ">
    <span class="p-input-icon-left ">
        <i class="pi pi-search " />
        <InputText v-model="value1" placeholder="Search" class="w-30rem border-round-xl" />
        <Button @click="getByTitulo"  label="Pesquisar " icon="pi pi-search " class="text-white border-round-xl border-none ml-3" style="background: #f45d2d; " />
        </span>
        
</div>
<Dialog v-model:visible="aqui" :style="{ width: '650px' }"  :modal="true">
  <DataTable :value="anuncios" tableStyle="min-width: 40rem">
    <template #header>
        <div class="flex flex-wrap align-items-center justify-content-between gap-2">
            <span class="text-xl text-900 font-bold"> Anuncios relacionados</span>
 
            
        </div>
    </template>     
    
    <Column field="titulo" header="Nome"></Column>
    <Column field="texto" header="Descrição"></Column>

    <Column field="valor" header="Preço">      
    </Column>
    <Column field="tipo" header="Categoria"></Column>
    <Column field="telefone" header="Telefone"> </Column>
      <Column field="email" header="Email"></Column> <template #footer> Exibindo um total de {{ anuncios ? anuncios.length : 0 }} Anuncios. </template>
    </DataTable>
</Dialog>

<div class="flex m-3">
    <h1>Pesquise Por Categorias:</h1>
  </div>
<div class=" grid m-2" style="width: 1200px; height: auto;">
  
  <div class="col" >
    <Card > 
         
      <template #title >Refrigeração </template>
      
      <template #content>
        
          <img src="../icones/ac_unit_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'refrigeracao')">
      </template>     
   
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title> Pedreiro </template>
      <template #content>
         <img src="../icones/handyman_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'pedreiro')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title>Encanador </template>
      <template #content>
          <img src="../icones/plumbing_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'encanador')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title>Babá </template>
      <template #content>
          <img src="../icones/baby_changing_station_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'baba')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title>Arquitetos </template>
      <template #content>
          <img src="../icones/apartment_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'arquiteto')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title>Eletricista </template>
      <template #content>
          <img src="../icones/bolt_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'eletricista')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title>Lava Jato </template>
      <template #content>
          <img src="../icones/local_car_wash_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'lava jato')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title>Jardineiro </template>
      <template #content>
          <img src="../icones/local_florist_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'jardineiro')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title> Vidraceiros </template>
      <template #content>
         <img src="../icones/window_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'vidraceiro')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card >     
      <template #title > Mecânica  </template>
      <template #content>
          <img src="../icones/home_repair_service_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'mecanica')">
      </template>      
  </Card>
  </div>
  <div class="col">
    <Card class="flex justify-content-center aligin-content-center">     
      <template #title> Serviços Gerais </template>
      <template #content>
          <img src="../icones/cleaning_services_FILL0_wght400_GRAD0_opsz48.svg" alt="" @click="getByTipo(tipo = 'faxina')">
      </template>      
  </Card>
  </div>
  
 
  
  
</div>

 <div class="surface-section px-4 py-8 md:px-6 lg:px-8 mt-8">
    <div class="text-700 text-center">
        <div class=" text-3xl font-bold mb-3">Seja um Parceiro e ofereça seu serviço</div>
        <div class="text-900 font-bold text-5xl mb-3">Se inscreva aqui:</div>
       
       <router-link to="/cadastro" tag="button"> <Button label="Inscreva-se"    class="font-bold px-5 py-3 p-button-raised p-button-rounded white-space-nowrap border-none" style="background: #f45d2d; ">


        </Button></router-link>
   
      </div>

</div>  
  


</template>

<script>
import { AlternateEmail } from '@mui/icons-material';
import axios from 'axios';

export default  
{
  name: 'TheHome',
  data() {
    return {
      value1: '',
      aqui: false, 

      anuncios:[],
      tipo:''
    };
  },
  props: {},
  methods: {
    teste(){
      alert("chamou")
    },
    async getByTitulo(string){
      this.aqui = true;
      string = this.value1

      await axios
      .get("http://localhost:8080/api/anuncio/titulo/" + string)
      .then(respota =>{
        this.anuncios = respota.data;

      })
      .catch(erro =>{
        console.log(erro)
      })

    },

    async getByTipo(tipo){
      this.aqui = true;
      tipo = this.tipo;
      await axios
      .get("http://localhost:8080/api/anuncio/tipo/" + tipo)
      .then(respota =>{
        this.anuncios = respota.data;
        console.log(tipo)

      })
      .catch(erro =>{
        console.log(erro)
      })

    }
  },
};




</script>

<style>
#cadastre:hover {
  color: #f45d2d;
}


</style>
