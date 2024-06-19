<template>
  <div id="app">
    <div id="sidebar">
      <h2>Categories</h2>
      <div class="search-category">
        <input v-model="searchQuery" placeholder="Search Category" />
        <button @click="searchCategory" class="btn btn-outline-dark">Search</button>
        <div v-if="categoryError" class="error">{{ categoryError }}</div>
      </div>
      <ul>
        <li v-for="category in categories" :key="category.id">{{ category.categoryName }}</li>
      </ul>
    </div>
    <div id="main-content">
      <input v-model="productSearchQuery" placeholder="Search Products..." class="search-input" />
      <input v-model="minPrice" type="number" placeholder="Min Price" class="search-input" />
      <input v-model="maxPrice" type="number" placeholder="Max Price" class="search-input" />
      <button @click="filterProducts" class="btn btn-outline-dark">Filter</button>
      <div v-if="productFilterError" class="error">{{ productFilterError }}</div>
      <div id="products">
        <div v-if="error" class="error">{{ error }}</div>
        <div v-if="loading" class="loading">Loading...</div>
        <div class="product-grid">
          <div class="product-card" v-for="product in displayedProducts" :key="product.id">
            <h2>{{ product.name }}</h2>
            <img v-if="product.imagePath" :src="getProductImage(product.imagePath)" alt="Product Image" />
            <p>Price: {{ product.price }}</p>
            <p>Description: {{ product.description }}</p>
            <button class="btn btn-outline-dark" type="button" @click.prevent="loadProduct(product)">Details</button>
            <button
              v-if="product.salesType === 'AUCTION'"
              class="btn btn-outline-dark"
              type="button"
              @click.prevent="openAuctionPopup(product)"
              :disabled="bidPlacedProducts.includes(product.id)"
            >
              {{ bidPlacedProducts.includes(product.id) ? 'Bid Placed' : 'Place Bid' }}
            </button>
            <button
              v-else
              class="btn btn-outline-dark"
              type="button"
              @click.prevent="purchaseProduct(product.id)"
              :disabled="purchasedProducts.includes(product.id)"
            >
              {{ purchasedProducts.includes(product.id) ? 'Purchased' : 'Purchase' }}
            </button>
          </div>
        </div>
      </div>
      <AuctionPopup
        v-if="showAuctionPopup"
        :show="showAuctionPopup"
        :product="selectedProduct"
        @close="closeAuctionPopup"
        @bid-placed="handleBidPlaced"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import AuctionPopup from './AuctionPopup.vue';

export default {
  name: 'HomePage',
  components: {
    AuctionPopup
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
      purchasedProducts: JSON.parse(localStorage.getItem('purchasedProducts')) || [],
      bidPlacedProducts: JSON.parse(localStorage.getItem('bidPlacedProducts')) || [],
      showAuctionPopup: false,
      selectedProduct: null
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
        this.error = 'We encountered an issue while loading products. Please try again later.';
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
          this.categoryError = 'No matching category found.';
          this.displayedProducts = [];
        }
      } catch (error) {
        console.error('Error searching category:', error);
        this.categoryError = 'We encountered an issue while searching for the category. Please try again later.';
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
        this.error = 'We encountered an issue while loading categories. Please try again later.';
      }
    },
    loadProduct(product) {
      this.$router.push(`/products/${product.id}`);
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
          this.productFilterError = 'No products match your criteria.';
        }
      } catch (error) {
        console.error('Error filtering products:', error);
        this.productFilterError = 'We encountered an issue while filtering products. Please try again later.';
      }
    },
    async purchaseProduct(productId) {
      try {
        await axios.post(`http://localhost:8181/api/purchase/${productId}`, {}, {
          withCredentials: true
        });
        alert('Product purchased successfully!');
        this.purchasedProducts.push(productId);
        localStorage.setItem('purchasedProducts', JSON.stringify(this.purchasedProducts));
      } catch (error) {
        console.error('Error purchasing product:', error);
        alert('We encountered an issue while purchasing the product. Please try again later.');
      }
    },
    openAuctionPopup(product) {
      this.selectedProduct = product;
      this.showAuctionPopup = true;
    },
    closeAuctionPopup() {
      this.showAuctionPopup = false;
      this.selectedProduct = null;
    },
    handleBidPlaced(productId) {
      this.bidPlacedProducts.push(productId);
      localStorage.setItem('bidPlacedProducts', JSON.stringify(this.bidPlacedProducts));
      this.showAuctionPopup = false;
      this.selectedProduct = null;
      this.fetchProducts();
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
  min-height: 100vh;
}

#sidebar {
  width: 200px;
  background-color: pink;
  padding: 20px;
}

#main-content {
  flex: 1;
  padding: 20px;
}

.search-input {
  width: auto;
  padding: 10px;
  margin-bottom: 5px;
  font-size: 16px;
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
