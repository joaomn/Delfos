<template>
    <div class="barra">
        <img src="../assets/WhatsApp_Image_2023-05-21_at_18.51 1.png" alt="" class="logo">
    </div>
</template>

<script></script>

<style >
* {
    
        margin:0;
        padding:0;
        border:0;
    
}

.barra{
    background: #F45D2D;
   margin: 0rem;
   border-bottom: 8px solid rgb(34, 33, 33);
   width: 100% ;
   height: 8rem;
   margin-bottom: 0px;
   box-shadow: -5px 4px 2px 1px rgb(133, 104, 104);
}

.logo{
    display: inline-block;
    animation-name: logo;
    animation-duration: 4s;
    animation-timing-function: ease-in-out;
    animation-iteration-count: infinite;
  
    width: 220px;
    
}


@keyframes logo {
    0% { transform: translateY(0); }
    50% { transform: translateY(-4px); }
    100% { transform: translateY(0); }
  }
</style>
