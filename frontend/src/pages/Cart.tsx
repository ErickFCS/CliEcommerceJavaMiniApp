import React from 'react';
import { Minus, Plus, Trash2, ShoppingBag, Loader2 } from 'lucide-react';
import { useAppSelector, useAppDispatch } from '../hooks/redux';
import { removeFromCart, updateCartQuantity, clearCart, clearCartError } from '../store/slices/cartSlice';
import { createOrder } from '../store/slices/ordersSlice';

const Cart: React.FC = () => {
  const dispatch = useAppDispatch();
  const cartItems = useAppSelector((state) => state.cart.items);
  const cartError = useAppSelector((state) => state.cart.error);
  const { loading: orderLoading } = useAppSelector((state) => state.orders);

  const updateQuantity = (id: string, quantity: number) => {
    if (quantity <= 0) {
      dispatch(removeFromCart(id));
    } else {
      dispatch(updateCartQuantity({ id, quantity }));
    }
  };

  const removeItem = (id: string) => {
    dispatch(removeFromCart(id));
  };

  const confirmOrder = async () => {
    const total = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
    try {
      await dispatch(createOrder({ items: cartItems, total })).unwrap();
      dispatch(clearCart());
      alert('Order confirmed! Check your orders page to track it.');
    } catch (error) {
      alert('Failed to create order. Please try again.');
    }
  };

  const total = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);

  if (cartItems.length === 0) {
    return (
      <div className="py-12">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center py-20">
            <ShoppingBag className="h-24 w-24 text-gray-400 mx-auto mb-6" />
            <h2 className="text-3xl font-bold text-white mb-4">Your cart is empty</h2>
            <p className="text-xl text-gray-300 mb-8">Add some futuristic products to get started!</p>
            <a
              href="/products"
              className="inline-flex items-center px-8 py-4 rounded-lg bg-gradient-to-r from-cyan-500 to-purple-500 text-white font-semibold hover:from-purple-500 hover:to-cyan-500 transition-all duration-300"
            >
              Browse Products
            </a>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="py-12">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
        <h1 className="text-3xl font-bold text-white mb-8">Shopping Cart</h1>

        {cartError && (
          <div className="mb-6 p-4 rounded-lg bg-red-500/10 border border-red-500/20 flex items-center justify-between">
            <p className="text-red-400">{cartError}</p>
            <button
              onClick={() => dispatch(clearCartError())}
              className="text-red-400 hover:text-red-300"
            >
              ×
            </button>
          </div>
        )}

        <div className="space-y-6">
          {cartItems.map((item) => (
            <div
              key={item.id}
              className="backdrop-blur-md bg-white/5 rounded-xl p-6 border border-cyan-500/20"
            >
              <div className="flex flex-col md:flex-row items-center space-y-4 md:space-y-0 md:space-x-6">
                <img
                  src={item.thumbnail}
                  alt={item.name}
                  className="w-24 h-24 object-cover rounded-lg"
                />
                
                <div className="flex-1 text-center md:text-left">
                  <h3 className="text-xl font-semibold text-white mb-2">{item.name}</h3>
                  <p className="text-gray-300 text-sm mb-2">{item.description}</p>
                  <p className="text-lg font-bold bg-gradient-to-r from-cyan-400 to-purple-400 bg-clip-text text-transparent">
                    ${item.price}
                  </p>
                </div>

                <div className="flex items-center space-x-4">
                  <div className="flex items-center space-x-3 backdrop-blur-md bg-white/10 rounded-lg p-2">
                    <button
                      onClick={() => updateQuantity(item.id, item.quantity - 1)}
                      className="p-1 rounded text-gray-300 hover:text-cyan-400 transition-colors duration-200"
                    >
                      <Minus className="h-4 w-4" />
                    </button>
                    <span className="text-white font-semibold w-8 text-center">{item.quantity}</span>
                    <button
                      onClick={() => updateQuantity(item.id, item.quantity + 1)}
                      className="p-1 rounded text-gray-300 hover:text-cyan-400 transition-colors duration-200"
                    >
                      <Plus className="h-4 w-4" />
                    </button>
                  </div>

                  <button
                    onClick={() => removeItem(item.id)}
                    className="p-2 rounded-lg text-gray-400 hover:text-red-400 hover:bg-red-400/10 transition-all duration-200"
                  >
                    <Trash2 className="h-5 w-5" />
                  </button>
                </div>

                <div className="text-right">
                  <p className="text-xl font-bold text-white">
                    ${(item.price * item.quantity).toFixed(2)}
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* Total and Checkout */}
        <div className="mt-8 backdrop-blur-md bg-white/5 rounded-xl p-6 border border-cyan-500/20">
          <div className="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
            <div className="text-center md:text-left">
              <p className="text-gray-300">Total Items: {cartItems.reduce((sum, item) => sum + item.quantity, 0)}</p>
              <p className="text-3xl font-bold bg-gradient-to-r from-cyan-400 to-purple-400 bg-clip-text text-transparent">
                Total: ${total.toFixed(2)}
              </p>
            </div>
            
            <button
              onClick={confirmOrder}
              disabled={orderLoading}
              className="px-8 py-4 rounded-lg bg-gradient-to-r from-green-500 to-emerald-500 text-white font-semibold hover:from-emerald-500 hover:to-green-500 transition-all duration-300 transform hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none flex items-center"
            >
              {orderLoading ? (
                <>
                  <Loader2 className="mr-2 h-5 w-5 animate-spin" />
                  Processing...
                </>
              ) : (
                'Confirm Order'
              )}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;