export interface Product {
  id: string;
  name: string;
  price: number;
  thumbnail: string;
  description: string;
  stock: number;
  category: string;
}

export interface CartItem extends Product {
  quantity: number;
}

export interface Order {
  id: string;
  items: CartItem[];
  total: number;
  status: 'pending' | 'processing' | 'shipped' | 'delivered' | 'cancelled';
  date: string;
}

export interface User {
  username: string;
  isAuthenticated: boolean;
}