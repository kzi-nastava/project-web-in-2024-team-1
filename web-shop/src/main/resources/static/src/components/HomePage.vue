<template>
  <div id="app">
    <div id="sidebar">
      <h2>Categories</h2>
      <div class="search-category">
        <div class="input-button-wrapper">
          <input v-model="searchQuery" placeholder="Search Category" />
          <button style="color: crimson; background-color: lightyellow; border-radius: 50%; font-weight: bolder; border-color: crimson;" @click="searchCategory" class="btn btn-outline-dark">🔍</button>
        </div>
        <div v-if="categoryError" class="error">{{ categoryError }}</div>
      </div>
      <ul style="margin-left: -25px;">
  <li style="text-align: left;" v-for="category in categories" :key="category.id" @click="filterByCategory(category.categoryName)">
    {{ category.categoryName }}
  </li>
     </ul>
    </div>
    <div id="main-content">
      <input v-model="productSearchQuery" placeholder="Search Products..." class="search-input"/>
      <input v-model="minPrice" type="number" placeholder="Min Price" class="search-input"/>
      <input v-model="maxPrice" type="number" placeholder="Max Price" class="search-input"/>
      <button style="font-size: 20px; color: crimson; background-color: lightyellow; border-radius: 50%; font-weight: bolder; border-color: crimson;" @click="filterProducts" class="btn btn-outline-dark">🧐</button>
      <div v-if="productFilterError" class="error">{{ productFilterError }}</div>
      <div id="products">
        <div v-if="error" class="error">{{ error }}</div>
        <div v-if="loading" class="loading">Loading...</div>
        <div class="product-grid">
          <div class="product-card" v-for="product in displayedProducts" :key="product.id">
            <h2>{{ product.name }}</h2>
            <img v-if="product.imagePath" :src="getProductImage(product.imagePath)" alt="Product Image" />
            <div class="product-info">
              <p>Description: <br>{{ product.description }}</p>
              <p class="price">{{ product.price }} $$</p>
            </div>
            <div class="button-container">
              <button id="button" class="btn btn-outline-dark" type="button" @click.prevent="loadProduct(product)">
                Details
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <product-details v-if="showModal" :productId="selectedProductId" @close="closeModal"></product-details>
  </div>
</template>

<script>
import axios from 'axios';
import ProductDetails from './ProductDetails.vue';

export default {
  name: 'HomePage',
  components: {
    ProductDetails
  },
  data() {
    return {
      products: [],
      categories: [],
      displayedProducts: [],
      searchQuery: '',
      productSearchQuery: '',
      minPrice: '',
      maxPrice: '',
      loading: false,
      error: '',
      categoryError: '',
      productFilterError: '',
      showModal: false,
      selectedProductId: null
    };
  },
  mounted() {
    this.fetchProducts();
    this.extractCategories();
  },
  methods: {
    getProductImage(imagePath) {
      return require(`@/assets/${imagePath}`);
    },
    async fetchProducts() {
      this.loading = true;
      try {
        const response = await axios.get('http://localhost:8181/api/products', {
          withCredentials: true
        });
        this.products = response.data;
        this.displayedProducts = this.products;
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
    async searchCategory() {
      if (!this.searchQuery) {
        this.categoryError = '';
        this.displayedProducts = this.products;
        return;
      }

      this.loading = true;
      this.categoryError = '';

      try {
        const response = await axios.get('http://localhost:8181/api/category/getByName', {
          params: { categoryName: this.searchQuery },
          withCredentials: true
        });

        if (response.data && response.data.products) {
          this.displayedProducts = response.data.products;
        } else {
          this.categoryError = 'Category not found';
          this.displayedProducts = [];
        }
      } catch (error) {
        console.error('Error searching category:', error);
        this.categoryError = 'Failed to search category';
        this.displayedProducts = [];
      } finally {
        this.loading = false;
      }
    },
    async extractCategories() {
      try {
        const categorySet = await axios.get('http://localhost:8181/api/category/all', {
          withCredentials: true
        });
        this.categories = categorySet.data;
      } catch (error) {
        console.error('Error fetching categories:', error);
        this.error = 'Failed to load categories';
      }
    },
    loadProduct(product) {
      this.selectedProductId = product.id;
      this.showModal = true;
    },
    filterProducts() {
      this.productFilterError = '';
      try {
        const searchQuery = this.productSearchQuery.toLowerCase();
        const minPrice = parseFloat(this.minPrice);
        const maxPrice = parseFloat(this.maxPrice);
        this.displayedProducts = this.products.filter(product => {
          const matchesNameOrDescription = product.name.toLowerCase().includes(searchQuery) || product.description.toLowerCase().includes(searchQuery);
          const matchesPriceRange = (!isNaN(minPrice) ? product.price >= minPrice : true) && (!isNaN(maxPrice) ? product.price <= maxPrice : true);
          return matchesNameOrDescription && matchesPriceRange;
        });

        if (this.displayedProducts.length === 0) {
          this.productFilterError = 'No products match your criteria';
        }
      } catch (error) {
        console.error('Error filtering products:', error);
        this.productFilterError = 'Failed to filter products';
      }
    },
    async filterByCategory(categoryName) {
  this.loading = true;
  this.categoryError = '';

  try {
    const response = await axios.get('http://localhost:8181/api/category/category-with-products', {
      params: { categoryName: categoryName },
      withCredentials: true
    });

    if (response.data && response.data.products) {
      this.displayedProducts = response.data.products;
    } else {
      this.categoryError = 'No products found for this category';
      this.displayedProducts = [];
    }
  } catch (error) {
    console.error('Error fetching category products:', error);
    this.categoryError = 'Failed to load category products';
    this.displayedProducts = [];
  } finally {
    this.loading = false;
  }
}
,
    closeModal() {
      this.showModal = false;
      this.selectedProductId = null;
    }
  }
};
</script>

<style scoped>
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

#app {
  display: flex;
  min-height: 100vh; /* Ensure the app takes at least the full viewport height */
}

#sidebar {
  width: 200px;
  background-color: pink;
  padding: 20px;
  margin-top: 0.2%;
}

#main-content {
  flex: 1; /* Ensures the main content takes the remaining space */
  padding: 20px;
}

.search-category {
  margin-bottom: 20px; /* Add some space below the search category section */
}

.input-button-wrapper {
  display: flex;
  align-items: center;
  justify-content:flex-end; /* Align items to the start (left) */
  margin-right: -15px;
}

.input-button-wrapper input {
  flex: 1;
  margin-right: 5px; /* Add space between the input and button */
  border: crimson;
  border-style: solid;
    border-width: 2px;
}

.input-button-wrapper button {
  flex-shrink: 0; /* Prevent the button from shrinking */
}

#sidebar input, #sidebar button {
  font-size: 15px;
}

.search-input {
  width:150px;
  padding: 5px;
  font-size: 16px;
  margin: 5px;
  border: crimson;
    border-style: solid;
    border-width: 2px;
}

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
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border: 1px solid #ddd;
  padding: 20px;
  width: calc(33.333% - 40px);
  box-sizing: border-box;
  text-align: center;
  background-color: pink;
  border-color: crimson;
  border-style: solid;
  border-width: 2.5px;
  height: 550px; /* Set a fixed height for the product cards */
}

.product-card h2 {
  margin: 10px 0;
  font-size: 30px;
  text-decoration: underline;
}

.product-card img {
  border-color: crimson;
  border-style: double;
  width: 280px;
  height: 300px;
  background-color: lightgoldenrodyellow;
  align-self: center;
}

.product-info {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.product-info p {
  margin: 10px 0;
  font-size: 15px;
  text-align: left;
}

.product-info .price {
  text-align: center; /* Center the price */
  font-weight:bold ;
  color: darkred;
  font-size: large;
}

.button-container {
  display: flex;
  justify-content: center;
}

.loading {
  font-size: 18px;
  color: #888;
}

.error {
  font-size: 18px;
  color: red;
}

#button {
  color: crimson;
  background-color: lightyellow;
  border-radius: 50%;
  font-weight: bolder;  
  border-color: crimson;
}

</style>
