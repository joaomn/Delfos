<script setup>
import BarraSuperior from './components/BarraSuperior.vue'
import BarraInferior from './components/BarraInferior.vue'

import Teste from './components/Teste.vue'
</script>

<template>
 <BarraSuperior /> 

 

<RouterView />

 <BarraInferior />
 





</template>

<style >
@import url('https://fonts.googleapis.com/css2?family=Kanit&display=swap');

@import url('https://fonts.googleapis.com/css2?family=Titan+One&family=Ubuntu&display=swap');

*{
  margin: 0;
  padding: 0;
  border: 0;
  font-family: 'Ubuntu', sans-serif;
  font-weight: 0;
  
}


@media (min-width: 576px) {
}

   

    @media (min-width: 768px) {

        #wrapper {
            width: 90%;
            margin: 0 auto;
        }

        #content {
            width: 70%;
            float: left;
        }

        #sidebar {
            width: 30%;
            float: right;
        }

        @media (min-width: 992px) {
            
        }

    }

    

    @media (min-width: 1200px) {

        #wrapper {
            width: 90%;
            margin: 0 auto;
        }

    }




</style>
