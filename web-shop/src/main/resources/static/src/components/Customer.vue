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
            <div class="product-info">
              <p>Description:<br> {{ product.description }}</p>
              <p class="price">{{ product.price }} $$</p>
            </div>
            <div class="button-container">
              <button id="button" class="btn btn-outline-dark" type="button" @click.prevent="loadProduct(product.id)">Details</button>
              <button
                v-if="product.salesType === 'AUCTION'"
                id="button"
                class="btn btn-outline-dark"
                type="button"
                @click.prevent="openAuctionPopup(product)"
              >
                {{ bidPlacedProducts.includes(product.id) ? 'Bid Placed' : 'Place Bid' }}
              </button>
              <button
                v-else
                id="button"
                class="btn btn-outline-dark"
                type="button"
                @click.prevent="purchaseProduct(product.id)"
              >
                {{ purchasedProducts.includes(product.id) ? 'Purchased' : 'Purchase' }}
              </button>
            </div>
          </div>
        </div>
      </div>
      <ProductDetails
        v-if="showProductModal"
        :productId="selectedProductId"
        @close="closeProductModal"
      />
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
import ProductDetails from './ProductDetails.vue';

export default {
  name: 'HomePage',
  components: {
    AuctionPopup,
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
      showAuctionPopup: false,
      showProductModal: false,
      selectedProduct: null,
      selectedProductId: null,
      purchasedProducts: [],
      bidPlacedProducts: []
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
        console.error('Error extracting categories:', error);
      }
    },
    filterProducts() {
      const min = parseFloat(this.minPrice);
      const max = parseFloat(this.maxPrice);

      if (isNaN(min) || isNaN(max) || min > max) {
        this.productFilterError = 'Invalid price range. Please enter valid minimum and maximum prices.';
        return;
      }

      this.productFilterError = '';

      this.displayedProducts = this.products.filter(product => {
        const price = parseFloat(product.price);
        return price >= min && price <= max && product.name.toLowerCase().includes(this.productSearchQuery.toLowerCase());
      });
    },
    openAuctionPopup(product) {
      if (this.bidPlacedProducts.includes(product.id)) {
        alert('You have already placed a bid on this product.');
      } else {
        this.selectedProduct = product;
        this.showAuctionPopup = true;
      }
    },
    closeAuctionPopup() {
      this.selectedProduct = null;
      this.showAuctionPopup = false;
    },
    handleBidPlaced(productId) {
      this.bidPlacedProducts.push(productId);
    },
    purchaseProduct(productId) {
      this.purchasedProducts.push(productId);
    },
    loadProduct(productId) {
      this.selectedProductId = productId;
      this.showProductModal = true;
    },
    closeProductModal() {
      this.selectedProductId = null;
      this.showProductModal = false;
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

.search-input {
  width:auto;
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
