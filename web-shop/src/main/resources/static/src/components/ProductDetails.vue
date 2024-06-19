<template>
    <div id="product-details">
    <h1 v-if="product">{{ product.name }}</h1>
    <div v-if="error" class="error">{{ error }}</div>
    <div v-if="loading" class="loading">Loading...</div>
    <div v-if="product">
      <p>Price: {{ product.price }}</p>
      <p>Description: {{ product.description }}</p>
      <p>Category: {{ product.categoryName }}</p>
      <p>Sales Type: {{ product.salesType }}</p>
      <img v-if="product.imagePath" :src="product.imagePath" alt="Product Image" />

      <!-- Add more fields as needed -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProductDetails',
  data() {
    return {
      product: null,
      loading: false,
      error: ''
    };
  },
  mounted() {
    this.fetchProduct();
  },
  methods: {
    fetchProduct() {
      const productId = this.$route.params.id;
      this.loading = true;
      fetch(`http://localhost:8181/api/product/${productId}`, {
        credentials: 'include'
      })
      .then(response => {
        if (!response.ok) {
          return response.json().then(errorData => {
            const errorMessage = errorData.message || 'An error occurred';
            throw new Error(`Error ${response.status}: ${errorMessage}`);
          });
        }
        return response.json();
      })
      .then(data => {
        console.log("Success:", data);
        this.product = data;
      })
      .catch((error) => {
        console.error("Error:", error);
        this.error = `Failed to load product: ${error.message}`;
      })
      .finally(() => {
        this.loading = false;
      });
    }
  }
};
</script>

<style scoped>
#product-details {
  padding: 20px;
}
h1 {
  margin: 20px 0;
}
.loading {
  font-size: 18px;
  color: #888;
}
.error {
  font-size: 18px;
  color: red;
}
</style>
