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
          <template #body="slotProps">
          <Button icon="pi pi-comment" class="shadow-7 border-none" text  severity="help" @click="openComent(slotProps.data)"/> 
          </template>
        </Column>
         
     
      
      <template #footer> Exibindo um total de {{ anuncioss ? anuncioss.length : 0 }} Anuncios. </template>
  </DataTable>
</div>


<div class="card w-full" v-if="pesquisar" style="margin-left: 0rem;" >
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
          <Button icon="pi pi-comment" class="shadow-7 border-none" text severity="help"/> 
          </template>
        </Column>
         
     
      
      <template #footer> Exibindo um total de {{ pesquisados ? pesquisados.length : 0 }} Anuncios. </template>
  </DataTable>
  
  
</div>


<Dialog v-model:visible="aqui" :style="{ width: '950px' }"  :modal="true">
  <DataTable :value="coments" tableStyle="min-width: 40rem">
    <template #header>
        <div class="flex flex-wrap align-items-center justify-content-between gap-2">
            <span class="text-xl text-900 font-bold"> Comentarios</span>
            <Button icon="pi pi-plus" severity="success" rounded raised @click="displayCreate = true" />
            
        </div>
    </template>     
    
    <Column field="cliente.nome" rouded header="Nome"></Column>
    <Column field="texto"  class="" rouded header="Comentou"></Column>
    <Column field="avaliacao" :sortable="true" header="Avaliação">
      <template #body="slotProps">
        <Rating v-model="slotProps.data.avaliacao" :cancel="false" :readonly="true" />
      </template>
    </Column>
    <Column id="coluna botao" class="w-3" header=" ">
      <template #body="slotProps">
        <Button
          v-if="slotProps.data.cliente.id === pessoaID"
          icon="pi pi-trash"
          class="shadow-5 border-none m-3"
          text
          severity="danger"
          @click="deleteDialogbox(slotProps.data)"
        />
      
      </template>
    </Column>  
      
      <template #footer> Exibindo um total de {{ coments ? coments.length : 0 }} Comentarios. </template>
    </DataTable>
</Dialog>

<Sidebar v-model:visible="displayCreate" style="height: 65%;" position="right">
  <div class="card">
      <h2 class="text-center mb-5">Inserir Comentario</h2>
      <form class="p-fluid">
          <div class="field">
              <div class="p-inputgroup">
                  <span class="p-inputgroup-addon">
                      <i class="pi pi-comment"></i>
                  </span>
                  <InputText placeholder="Comentario" v-model="comentarioPost.texto"  />
              </div>
          </div>
          <div class="field mt-3">
            
              <div class="card flex justify-content-center gap-3">
                <p> Avaliação: </p>
                  <Rating v-model="comentarioPost.avaliacao" :cancel="false"  />
              </div>
          
          </div>
          <div class="field">
              <div class="p-inputgroup">
                 
                  <InputText type="hidden" v-model="comentarioPost.cliente.id" />
              </div>
          </div>
          <div class="field">
            <div class="p-inputgroup">
               
                <InputText type="hidden" v-model="comentarioPost.anuncio.id" />
            </div>
        </div>
        
          <Button type="submit" label="Enviar" class="mt-2" @click="addComentario"></Button>

      </form>
  </div>
</Sidebar>
 

<Dialog v-model:visible="deleteDialog" :style="{ width: '650px' }" header="Deletar" :modal="true">

  <div class="textodelete">
      Tem certeza que deseja deletar o comentario?
  </div>
  <Div class="botaoDeletar m-3">

      <Button label="Sair" icon="pi pi-times" style="background-color: #c2bebe; width: 90px;height:40px ;"
          @click="closeDelete" autofocus class="p-button-text" />
      <Button label="Deletar" icon="pi pi-trash"
          style="background-color: rgba(218, 35, 35, 0.897);width: 110px;height:43px ;margin-left: 10px;"
          @click="deleteAgenda(alguem.id)" />
  </Div>
</Dialog>




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

      comentarioPost:{

        anuncio:{
          id: '',
        },
        cliente:{
          
          id:'',
        },
        avaliacao: 0,
        texto: ''

      },

      pessoa:[],

      pessoaID:'',


      
categoria: '',

coments:[],
     
todos : true,
      pesquisar : false,
      comentariosDialog: false,
      aqui: false,
      displayCreate: false,
      deleteDialog: false
    
     
     
      
    };
  },
  async mounted() {
    await this.resultado()
        this.getAnuncios()
         

    },
  props: {
    
    
  },
  methods: {
    async resultado() {
      const usuario = await axios
        .get("http://localhost:8080/api/cliente/alguem/" + Cookies.get("cliente"))
        .then((resultado) => {
          const alguem = resultado.data;

          this.pessoaID =alguem.id;
          
          
        })
        .catch((erro) => {
          console.log("pessoa not found")
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

  
  
},

 async openComent(data){
  console.log(data.id)
  this.comentarioPost.anuncio.id = data.id;
  this.aqui = true;
    await axios
    .get("http://localhost:8080/api/comentario/" + data.id + "/comentarios")
    .then(resp =>{
      console.log("chamou no comentario")
      console.log(resp)
      this.coments = resp.data
      
    })
    .catch(err =>{
      console.log(err)
    })
  },

  async addComentario(){
    this.comentarioPost.cliente.id = this.pessoaID;
  
    console.log(this.comentarioPost.anuncio.id)
    await axios
    .post("http://localhost:8080/api/comentario/", this.comentarioPost)
    .then(resp =>{
      console.log("cadastrou")
      
    })
    .catch(error => {
      console.log(this.comentarioPost)
      alert("aqui")
    })

  },

  deleteDialogbox(coisas){
      this.alguem = {...coisas};
      this.deleteDialog = true;

    }
    ,
    closeDelete(){
      this.deleteDialog = false;

    },
    async deleteAgenda(id) {
            let result = await axios.delete('http://localhost:8080/api/comentario/' + id)
            .then(response =>{
              this.deleteDialog = false;
             
              location.reload();

            })
            .catch(err=>{
              console.lod(err)
            })
            
        }


  },



 





};







</script>

<style >



</style>