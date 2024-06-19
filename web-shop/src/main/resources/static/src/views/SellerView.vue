<template>
    <div class="seller">
   
       <Seller />
     </div>
   </template>
   <script>
   
   // @ is an alias to /src
   import Seller from '@/components/Seller.vue'
   
   export default {
     name: 'SellerView',
     components: {
        Seller
     }
   }
   </script>