<template>
  <div
    class="flex felx-row gap-5 text-white  md:max-w-full"
    style="
      width: 100%;
      height: 14rem;
      background: #f45d2d;
      bottom: 0;
      border-top: 4px solid black;
      box-shadow: -120px, 400px, -4px, -1px black;
    "
  >
    <div id="correio" class="flex flex-column justify-content-start  mr-4 mt-1" style="margin-left: 1rem;">
      <h2 class="flex mb-2">Receba nossas novidades</h2>
      <div class="card flex w-3 ml-2 mt-1 justify-content-start">
        <InputText type="text" placeholder="Email" />
      </div>
      <div class="card flex w-3 ml-2 mt-2 justify-content-start">
        <InputText type="text" placeholder="WathsApp" />
      </div>
      <Button
        label="Enviar"
        icon="pi pi-check"
        class="cursor-pointer w-5 mt-2 ml-2 mb-2 border-none"
        style="background: 	#003366;"
        
      />
    </div>

    <div id="redesSociais" class="flex justify-content-center flex-column gap-5  align-content-center" style="margin-left: 12rem;">
      <h2>Siga-nos nas redes:</h2>
      <div class="flex flex-row align-content-center">
        <i class="pi pi-instagram mr-3" style="font-size: 3rem"><a href="#"></a></i>
        <i class="pi pi-youtube mr-3" style="font-size: 3rem"></i>
        <i class="pi pi-twitter mr-3" style="font-size: 3rem"></i>
        <i class="pi pi-facebook mr-3" style="font-size: 3rem"></i>
        <i class="pi pi-google mr-3" style="font-size: 3rem"></i>
      </div>
    </div>

    <div id="escrita" class="flex flex-column   justify-content-end aligin-itens-end" style=" margin-left: 20rem;">
      <h5>Copyright © 2023</h5>
        <h5>Todos os direitos reservados</h5>

    </div>
  </div>
</template>

<script></script>

<style></style>
