import { Product, Order, CartItem } from '../types';

// Mock data for development
const mockProducts: Product[] = [
  {
    id: '1',
    name: 'Neural VR Headset Pro',
    price: 899.99,
    thumbnail: 'https://images.pexels.com/photos/8721342/pexels-photo-8721342.jpeg',
    description: 'Next-generation VR headset with neural interface technology for immersive experiences.',
    stock: 15,
    category: 'VR Tech'
  },
  {
    id: '2',
    name: 'Quantum Gaming Laptop',
    price: 2499.99,
    thumbnail: 'https://images.pexels.com/photos/18105/pexels-photo.jpg',
    description: 'High-performance gaming laptop with quantum processors and holographic display.',
    stock: 8,
    category: 'Computers'
  },
  {
    id: '3',
    name: 'Cyber Security Suite',
    price: 199.99,
    thumbnail: 'https://images.pexels.com/photos/5380664/pexels-photo-5380664.jpeg',
    description: 'Advanced cybersecurity software with AI-powered threat detection.',
    stock: 50,
    category: 'Software'
  },
  {
    id: '4',
    name: 'Holographic Smartwatch',
    price: 699.99,
    thumbnail: 'https://images.pexels.com/photos/393047/pexels-photo-393047.jpeg',
    description: 'Futuristic smartwatch with holographic display and biometric sensors.',
    stock: 25,
    category: 'Wearables'
  },
  {
    id: '5',
    name: 'AI Assistant Robot',
    price: 1599.99,
    thumbnail: 'https://images.pexels.com/photos/8386440/pexels-photo-8386440.jpeg',
    description: 'Personal AI assistant robot with advanced learning capabilities.',
    stock: 5,
    category: 'Robotics'
  },
  {
    id: '6',
    name: 'Plasma Gaming Mouse',
    price: 149.99,
    thumbnail: 'https://images.pexels.com/photos/2115257/pexels-photo-2115257.jpeg',
    description: 'High-precision gaming mouse with plasma-infused sensors and RGB lighting.',
    stock: 30,
    category: 'Gaming'
  },
  {
    id: '7',
    name: 'Neural Interface Keyboard',
    price: 299.99,
    thumbnail: 'https://images.pexels.com/photos/1194713/pexels-photo-1194713.jpeg',
    description: 'Mechanical keyboard with neural interface for thought-to-text input.',
    stock: 20,
    category: 'Gaming'
  },
  {
    id: '8',
    name: 'Quantum Storage Drive',
    price: 399.99,
    thumbnail: 'https://images.pexels.com/photos/159304/network-cable-ethernet-computer-159304.jpeg',
    description: '10TB quantum storage drive with instant data access and encryption.',
    stock: 12,
    category: 'Storage'
  }
];

let mockOrders: Order[] = [];

// Simulate API delay
const delay = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));

// Mock API functions
export const mockProductsApi = {
  getAll: async (): Promise<{ data: Product[] }> => {
    await delay(500);
    return { data: mockProducts };
  },
  getById: async (id: string): Promise<{ data: Product }> => {
    await delay(300);
    const product = mockProducts.find(p => p.id === id);
    if (!product) throw new Error('Product not found');
    return { data: product };
  },
  getByCategory: async (category: string): Promise<{ data: Product[] }> => {
    await delay(400);
    const products = mockProducts.filter(p => p.category === category);
    return { data: products };
  },
  search: async (query: string): Promise<{ data: Product[] }> => {
    await delay(300);
    const products = mockProducts.filter(p => 
      p.name.toLowerCase().includes(query.toLowerCase()) ||
      p.description.toLowerCase().includes(query.toLowerCase())
    );
    return { data: products };
  },
};

export const mockOrdersApi = {
  getAll: async (): Promise<{ data: Order[] }> => {
    await delay(400);
    return { data: mockOrders };
  },
  getById: async (id: string): Promise<{ data: Order }> => {
    await delay(300);
    const order = mockOrders.find(o => o.id === id);
    if (!order) throw new Error('Order not found');
    return { data: order };
  },
  create: async (orderData: { items: CartItem[]; total: number }): Promise<{ data: Order }> => {
    await delay(600);
    const newOrder: Order = {
      id: Date.now().toString(),
      items: orderData.items,
      total: orderData.total,
      status: 'pending',
      date: new Date().toISOString()
    };
    mockOrders.unshift(newOrder);
    return { data: newOrder };
  },
  updateStatus: async (id: string, status: Order['status']): Promise<{ data: Order }> => {
    await delay(400);
    const orderIndex = mockOrders.findIndex(o => o.id === id);
    if (orderIndex === -1) throw new Error('Order not found');
    mockOrders[orderIndex].status = status;
    return { data: mockOrders[orderIndex] };
  },
  delete: async (id: string): Promise<void> => {
    await delay(300);
    const orderIndex = mockOrders.findIndex(o => o.id === id);
    if (orderIndex === -1) throw new Error('Order not found');
    mockOrders.splice(orderIndex, 1);
  },
};

export const mockAuthApi = {
  login: async (credentials: { username: string; password: string }): Promise<{ data: { token: string; user: { username: string } } }> => {
    await delay(800);
    // Simple mock validation
    if (credentials.username && credentials.password) {
      const token = `mock-token-${Date.now()}`;
      localStorage.setItem('authToken', token);
      return {
        data: {
          token,
          user: { username: credentials.username }
        }
      };
    }
    throw new Error('Invalid credentials');
  },
  register: async (userData: { username: string; password: string }): Promise<{ data: { token: string; user: { username: string } } }> => {
    await delay(800);
    if (userData.username && userData.password) {
      const token = `mock-token-${Date.now()}`;
      localStorage.setItem('authToken', token);
      return {
        data: {
          token,
          user: { username: userData.username }
        }
      };
    }
    throw new Error('Registration failed');
  },
  logout: async (): Promise<void> => {
    await delay(200);
    localStorage.removeItem('authToken');
  },
  refreshToken: async (): Promise<{ data: { token: string } }> => {
    await delay(300);
    const token = `mock-token-${Date.now()}`;
    localStorage.setItem('authToken', token);
    return { data: { token } };
  },
};