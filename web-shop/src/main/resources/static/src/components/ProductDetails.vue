<template>
  <div class="modal">
    <div class="modal-content">
      <button class="modal-close" @click="$emit('close')">&times;</button>
      <div id="product-details">
        <h1 v-if="product">{{ product.name }}</h1>
        <div v-if="error" class="error">{{ error }}</div>
        <div v-if="loading" class="loading">Loading...</div>
        <div v-if="product">
          <img v-if="product.imagePath" :src="getProductImage(product.imagePath)" alt="Product Image" />
          <p>Price: {{ product.price }}</p>
          <p>Description: {{ product.description }}</p>
          <p>Category: {{ product.categoryName }}</p>
          <p>Sales Type: {{ product.salesType }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProductDetails',
  props: ['productId'],
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
    getProductImage(imagePath) {
      return require(`@/assets/${imagePath}`);
    },
    fetchProduct() {
      const productId = this.productId;
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
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 600px;
}

.modal-close {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
}
</style>
