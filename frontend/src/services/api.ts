import axios from 'axios';
import { Product, Order, CartItem } from '../types';

// Create axios instance with base configuration
const api = axios.create({
  baseURL: 'https://api.technoshop.com', // Replace with your actual API base URL
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor for adding auth token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor for handling errors
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('authToken');
      window.location.href = '/signin';
    }
    return Promise.reject(error);
  }
);

// Products API
export const productsApi = {
  getAll: () => api.get<Product[]>('/products'),
  getById: (id: string) => api.get<Product>(`/products/${id}`),
  getByCategory: (category: string) => api.get<Product[]>(`/products/category/${category}`),
  search: (query: string) => api.get<Product[]>(`/products/search?q=${query}`),
};

// Orders API
export const ordersApi = {
  getAll: () => api.get<Order[]>('/orders'),
  getById: (id: string) => api.get<Order>(`/orders/${id}`),
  create: (orderData: { items: CartItem[]; total: number }) => 
    api.post<Order>('/orders', orderData),
  updateStatus: (id: string, status: Order['status']) => 
    api.patch<Order>(`/orders/${id}/status`, { status }),
  delete: (id: string) => api.delete(`/orders/${id}`),
};

// Auth API
export const authApi = {
  login: (credentials: { username: string; password: string }) => 
    api.post<{ token: string; user: { username: string } }>('/auth/login', credentials),
  register: (userData: { username: string; password: string }) => 
    api.post<{ token: string; user: { username: string } }>('/auth/register', userData),
  logout: () => api.post('/auth/logout'),
  refreshToken: () => api.post<{ token: string }>('/auth/refresh'),
};

export default api;