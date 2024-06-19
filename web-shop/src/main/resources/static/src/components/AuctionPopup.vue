<template>
    <div v-if="show" class="popup">
      <div class="popup-content">
        <span class="close" @click="closePopup">&times;</span>
        <h2>{{ product.name }}</h2>
        <img v-if="product.imagePath" :src="getProductImage(product.imagePath)" alt="Product Image" />
        <p>Price: {{ product.price }}</p>
        <label for="bidAmount">Your Bid:</label>
        <input type="number" v-model="bidAmount" id="bidAmount" />
        <button @click="placeBid" class="btn btn-outline-dark">Place Bid</button>
        <div v-if="error" class="error">{{ error }}</div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: {
      show: {
        type: Boolean,
        required: true
      },
      product: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        bidAmount: '',
        error: ''
      };
    },
    methods: {
      getProductImage(imagePath) {
        return require(`@/assets/${imagePath}`);
      },
      closePopup() {
        this.$emit('close');
      },
      async placeBid() {
        this.error = '';
        try {
            if (this.bidAmount <= 0) {
            this.error = 'Please enter a valid bid amount';
            return;
            }
            const response = await axios.post(`http://localhost:8181/api/auction/${this.product.id}`, {
            priceOffer: this.bidAmount 
            }, {
            withCredentials: true
            });
            this.$emit('bid-placed', this.product.id);
            this.closePopup();
        } catch (error) {
            console.error('Error placing bid:', error);
            if (error.response && error.response.data) {
            this.error = `Failed to place bid: ${error.response.data || 'An error occurred'}`;
            } else {
            this.error = 'Failed to place bid: Network Error';
            }
        }
    }

    }
  };
  </script>
  
  <style scoped>
  .popup {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .popup-content {
    background: white;
    padding: 20px;
    border-radius: 5px;
    text-align: center;
    position: relative;
    max-width: 500px;
    width: 100%;
  }
  
  .close {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 24px;
    cursor: pointer;
  }
  
  .error {
    color: red;
    margin-top: 10px;
  }
  </style>
  