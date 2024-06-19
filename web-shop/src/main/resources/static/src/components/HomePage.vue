<template>
  <div id="products">
    <h1>Products</h1>
    <div v-if="error" class="error">{{ error }}</div>
    <div v-if="loading" class="loading">Loading...</div>
    <div class="product-grid" v-if="products.length">
      <div class="product-card" v-for="product in products" :key="product.id">
        <h2>{{ product.name }}</h2>
        <img v-if="product.imagePath" :src="getProductImage(product.imagePath)" alt="Product Image" />
        <p>Price: {{ product.price }}</p>
        <p>Description: {{ product.description }}</p>
        <button class="btn btn-outline-dark" type="button" @click.prevent="loadProduct(product)">
          Details
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'HomePage',
  data() {
    return {
      products: [],
      loading: false,
      error: ''
    };
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    getProductImage(imagePath) {
      // Adjust the path based on where your images are located
      return require(`@/assets/${imagePath}`);
    },
    async fetchProducts() {
      this.loading = true;
      try {
        const response = await axios.get('http://localhost:8181/api/products', {
          withCredentials: true
        });
        this.products = response.data;
      } catch (error) {
        console.error('Error fetching products:', error);
        if (error.response) {
          const errorMessage = error.response.data.message || 'An error occurred while fetching the products';
          this.error = `Failed to load products: ${errorMessage}`;
        } else {
          this.error = 'Failed to load products: Network Error';
        }
      } finally {
        this.loading = false;
      }
    },
    loadProduct(product) {
      this.$router.push(`/products/${product.id}`);
    }
  }
};
</script>

<style scoped>
#products {
  padding: 20px;
  align-items: center;
}
h1 {
  margin: 20px 0;
}
.product-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
}
.product-card {
  border: 1px solid #ddd;
  padding: 20px;
  width: calc(33.333% - 40px);
  box-sizing: border-box;
  text-align: center;
  background-color: pink;
}
.product-card h2 {
  margin: 10px 0;
  font-size: 20px;
}
.product-card p {
  margin: 10px 0;
  font-size: 16px;
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
