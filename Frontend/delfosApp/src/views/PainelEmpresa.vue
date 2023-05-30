<template>
 
  <div class="card flex justify-content-start align-content-start m-2 mt-3">
    <Sidebar v-model:visible="visible">
        <h2>Sidebar</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </Sidebar>
    <Button icon="pi pi-arrow-right" @click="visible = true" />
</div>
 <div class="flex justify-content-center aligin-itens-center ">
    
    <h2 style="font-size: 40px; font-weight: 900; margin: 4rem; margin-top: -60px;">Paniel de Empresa</h2>
  </div>

  <div class="card">
    <DataTable :value="anuncios" tableStyle="min-width: 40rem">
      <template #header>
          <div class="flex flex-wrap align-items-center justify-content-between gap-2">
              <span class="text-xl text-900 font-bold">Ultimos Anuncios</span>
   
              <Button icon="pi pi-plus" severity="success" rounded raised @click="displayCreate = true" />
          </div>
      </template>     
      
      <Column field="titulo" header="Nome"></Column>
      <Column field="texto" header="Descrição"></Column>

      <Column field="valor" header="Preço">      
      </Column>
      <Column field="tipo" header="Categoria"></Column>
      <Column field="telefone" header="Telefone"> </Column>
        <Column field="email" header="Email"></Column>
        <Column   id="coluna botao" header=" ">
          <template #body="slotProps">
          <Button label="Editar"  icon="pi pi-user-edit" severity="warning" class="m-1" @click="openEdit(slotProps.data)" />
          <Button label="Apagar"  severity="danger"  icon="pi pi-trash" class="m-1" @click="deleteDialogbox(slotProps.data)" /> 
          </template>
        </Column>
         
     
      
      <template #footer> Exibindo um total de {{ anuncios ? anuncios.length : 0 }} Anuncios. </template>
  </DataTable>
  </div>

  <Dialog v-model:visible="displayCreate" :style="{ width: '900px' }" :modal="true" class="p-fluid">
    <div class="card">
        <h2 class="text-center">Inserir Anuncio</h2>
        <form class="p-fluid">
            <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-user"></i>
                    </span>
                    <InputText placeholder="Nome" v-model="anuncio.titulo"  />
                </div>
            </div>
            <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-envelope"></i>
                    </span>
                    <InputText placeholder="Email" v-model="anuncio.email" required/>
                </div>
            </div>
            <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-phone"></i>
                    </span>
                    <InputText placeholder="Telefone" v-model="anuncio.telefone" />
                </div>
            </div>
            <div class="field">
              <div class="p-inputgroup">
                  <span class="p-inputgroup-addon">
                      <i class="pi pi-phone"></i>
                  </span>
                  <InputText placeholder="Preço" v-model="anuncio.valor"  />
              </div>
          </div>
          <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-phone"></i>
                </span>
                <InputText placeholder="Descreva seu serviço" v-model="anuncio.texto"  />
            </div>
        </div>
        <div class="field">
          <div class="p-inputgroup">
              <span class="p-inputgroup-addon">
                  <i class="pi pi-phone"></i>
              </span>
              <InputText placeholder="Tipo de Serviço" v-model="anuncio.tipo"  />
          </div>
      </div>
        <input type="hidden" v-model="anuncio.empresa.id">
            <Button type="submit" label="Enviar" class="mt-2" @click="addAnuncio"></Button>

        </form>
    </div>
    </Dialog>

    <Dialog v-model:visible="displayEdit" :style="{ width: '900px' }" :modal="true" class="p-fluid">
      <div class="card">
          <h2 class="text-center">Editar Anuncio</h2>
          <form class="p-fluid">
              <div class="field">
                  <div class="p-inputgroup">
                      <span class="p-inputgroup-addon">
                          <i class="pi pi-user"></i>
                      </span>
                      <InputText placeholder="Nome" v-model="cont.titulo"  />
                  </div>
              </div>
              <div class="field">
                  <div class="p-inputgroup">
                      <span class="p-inputgroup-addon">
                          <i class="pi pi-envelope"></i>
                      </span>
                      <InputText placeholder="Email" v-model="cont.email" required/>
                  </div>
              </div>
              <div class="field">
                  <div class="p-inputgroup">
                      <span class="p-inputgroup-addon">
                          <i class="pi pi-phone"></i>
                      </span>
                      <InputText placeholder="Telefone" v-model="cont.telefone" />
                  </div>
              </div>
              <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-phone"></i>
                    </span>
                    <InputText placeholder="Preço" v-model="cont.valor"  />
                </div>
            </div>
            <div class="field">
              <div class="p-inputgroup">
                  <span class="p-inputgroup-addon">
                      <i class="pi pi-phone"></i>
                  </span>
                  <InputText placeholder="Descreva seu serviço" v-model="cont.texto"  />
              </div>
          </div>
          <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-phone"></i>
                </span>
                <InputText placeholder="Tipo de Serviço" v-model="cont.tipo"  />
            </div>
        </div>
          <input type="hidden" v-model="cont.empresa.id">
              <Button type="submit" label="Enviar" class="mt-2" @click="updateAgenda(cont.id)"></Button>
  
          </form>
      </div>
      </Dialog>

    <Dialog v-model:visible="deleteDialog" :style="{ width: '650px' }" header="Deletar" :modal="true">

      <div class="textodelete">
          Tem certeza que deseja deletar o usuario?
      </div>
      <Div class="botaoDeletar">

          <Button label="Sair" icon="pi pi-times" style="background-color: #f2f2f2; width: 120px;height:45px ;"
              @click="closeDelete" autofocus class="p-button-text" />
          <Button label="Deletar" icon="pi pi-trash"
              style="background-color: red;width: 120px;height:45px ;margin-left: 10px;"
              @click="deleteAgenda(alguem.id)" />
      </Div>
  </Dialog>

</template>

<script>
import axios from "axios";
import Cookies from "js-cookie";
export default {
  name: "Test",
  created() {
   
  },
  data() {
    return {
       visible: false,
      pessoa: {
        cpfCnpj: '',
        email: '',
        id: '',
        razaoSocial: '',
        responsavel: '',
        telefone: ''
      },
      anuncio: {
        avaliacao: 0,
        dtPostagem: '',
        email: '',
        empresa: {
          id: 12,
        },
        id: '',
        mensagem: '',
        telefone: '',
        texto: '',
        tipo: '',
        titulo: '',
        valor: '',
      },

      pessoaID: 0,
      displayCreate: false,
      deleteDialog: false,
      displayEdit:false,

       

      anuncios:[],
    };
  },
  
    async mounted() {
        this.resultado(),
   this.getAnunciosEmp()
        

    },
  props: {},
  methods: {
    async resultado() {
      const usuario = await axios
        .get("http://localhost:8080/api/empresa/alguem/" + Cookies.get("empresa"))
        .then((resultado) => {
          const pessoa = resultado.data;

          this.pessoaID = pessoa.id;
         

          
          
        })
        .catch((erro) => {
          alert("empresa não encontrada");
        });
    },
    test(){
      console.log(this.pessoaID);
    }
    ,
    async getAnunciosEmp(){

      const value1 = this.pessoaID.toString;
      const chamada = parseInt(value1);
      
      
      await axios
      .get("http://localhost:8080/api/empresa/anuncios/" + 12)
      .then(anun =>{

        this.anuncios = anun.data;

        

      })
      .catch(erro =>{
        console.log(erro)
      })
    },
    async addAnuncio(){
      this.anuncio.dtPostagem = new Date().toISOString().split('T')[0];
      await axios
      .post("http://localhost:8080/api/anuncio", this.anuncio)
      .then(resposta =>{
        alert("cadastrado com sucesso");
      })
      .catch(error =>{
        alert(error);
      })
    },
    deleteDialogbox(coisas){
      this.alguem = {...coisas};
      this.deleteDialog = true;

    },
    closeDelete(){
      this.deleteDialog = false;

    },
    async deleteAgenda(id) {
            let result = await axios.delete('http://localhost:8080/api/anuncio/' + id)
            .then(response =>{
              this.deleteDialog = false;
             
              location.reload();

            })
            .catch(err=>{
              console.lod(err)
            })
            
        },
        openEdit(conteudo){
          this.cont = {...conteudo};
          this.displayEdit = true;
        },
        async updateAgenda(id){
          this.cont.dtPostagem = new Date().toISOString().split('T')[0];
          await axios
          .put('http://localhost:8080/api/anuncio/' + id ,{
           telefone: this.cont.telefone,
           texto: this.cont.texto,
           tipo: this.cont.tipo,
           titulo: this.cont.titulo,
           valor: this.cont.valor,
           email: this.cont.email

          })
          .then(resp =>{
            alert("atualizado com sucesso")            
            location.reload();
          })
          .catch(err=>{
            alert(err);
          })

          
        }
  },
};
</script>

<style lang="scss" scoped></style>
