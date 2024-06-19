<template>
    <div class="customer">
   
       <Customer />
     </div>
   </template>
   <script>
   
   // @ is an alias to /src
   import Customer from '@/components/Customer.vue'
   
   export default {
     name: 'CustomerView',
     components: {
        Customer
     }
   }
   </script>