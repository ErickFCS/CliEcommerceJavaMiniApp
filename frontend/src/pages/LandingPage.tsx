import React from 'react';
import { Link } from 'react-router-dom';
import { ArrowRight, Zap, Shield, Rocket, Star } from 'lucide-react';
import { useAppSelector } from '../hooks/redux';

const LandingPage: React.FC = () => {
  const { user } = useAppSelector((state) => state.auth);
  const products = useAppSelector((state) => state.products.items);
  const featuredProducts = products.slice(0, 3);

  return (
    <div className="space-y-20">
      {/* Hero Section */}
      <section className="relative overflow-hidden py-20">
        <div className="absolute inset-0 bg-gradient-to-r from-cyan-500/10 to-purple-500/10"></div>
        <div className="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center">
            <h1 className="text-5xl md:text-7xl font-bold mb-6">
              <span className="bg-gradient-to-r from-cyan-400 via-purple-400 to-pink-400 bg-clip-text text-transparent">
                Future Tech
              </span>
              <br />
              <span className="text-white">Today</span>
            </h1>
            <p className="text-xl text-gray-300 mb-8 max-w-3xl mx-auto">
              Experience the next generation of technology with our curated collection of cutting-edge products. 
              From neural interfaces to quantum computing, we bring the future to your doorstep.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link
                to="/products"
                className="inline-flex items-center px-8 py-4 rounded-lg bg-gradient-to-r from-cyan-500 to-purple-500 text-white font-semibold hover:from-purple-500 hover:to-cyan-500 transition-all duration-300 transform hover:scale-105"
              >
                Explore Products
                <ArrowRight className="ml-2 h-5 w-5" />
              </Link>
              {!user.isAuthenticated && (
                <Link
                  to="/signup"
                  className="inline-flex items-center px-8 py-4 rounded-lg border-2 border-cyan-500 text-cyan-400 font-semibold hover:bg-cyan-500 hover:text-white transition-all duration-300"
                >
                  Join TechnoShop
                </Link>
              )}
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-white mb-4">Why Choose TechnoShop?</h2>
            <p className="text-xl text-gray-300">Experience the future of shopping</p>
          </div>
          <div className="grid md:grid-cols-3 gap-8">
            {[
              {
                icon: Zap,
                title: 'Cutting-Edge Technology',
                description: 'Access the latest innovations in tech before they hit mainstream markets.'
              },
              {
                icon: Shield,
                title: 'Quantum Security',
                description: 'Your data and transactions are protected by advanced quantum encryption.'
              },
              {
                icon: Rocket,
                title: 'Lightning Fast Delivery',
                description: 'Get your futuristic gadgets delivered in record time with our hyperloop network.'
              }
            ].map((feature, index) => (
              <div
                key={index}
                className="backdrop-blur-md bg-white/5 rounded-xl p-8 border border-cyan-500/20 hover:border-cyan-500/40 transition-all duration-300 group"
              >
                <div className="mb-6 inline-flex p-3 rounded-lg bg-gradient-to-r from-cyan-500/20 to-purple-500/20 group-hover:from-cyan-500/30 group-hover:to-purple-500/30 transition-all duration-300">
                  <feature.icon className="h-8 w-8 text-cyan-400" />
                </div>
                <h3 className="text-xl font-semibold text-white mb-4">{feature.title}</h3>
                <p className="text-gray-300">{feature.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Featured Products */}
      <section className="py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-white mb-4">Featured Products</h2>
            <p className="text-xl text-gray-300">Discover our most popular tech innovations</p>
          </div>
          <div className="grid md:grid-cols-3 gap-8">
            {featuredProducts.map((product) => (
              <div
                key={product.id}
                className="backdrop-blur-md bg-white/5 rounded-xl overflow-hidden border border-cyan-500/20 hover:border-cyan-500/40 transition-all duration-300 group hover:scale-105"
              >
                <div className="aspect-w-16 aspect-h-9 relative overflow-hidden">
                  <img
                    src={product.thumbnail}
                    alt={product.name}
                    className="w-full h-48 object-cover group-hover:scale-110 transition-transform duration-300"
                  />
                  <div className="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent"></div>
                </div>
                <div className="p-6">
                  <h3 className="text-xl font-semibold text-white mb-2">{product.name}</h3>
                  <p className="text-gray-300 text-sm mb-4 line-clamp-2">{product.description}</p>
                  <div className="flex items-center justify-between">
                    <span className="text-2xl font-bold bg-gradient-to-r from-cyan-400 to-purple-400 bg-clip-text text-transparent">
                      ${product.price}
                    </span>
                    <div className="flex items-center space-x-1">
                      {[...Array(5)].map((_, i) => (
                        <Star key={i} className="h-4 w-4 text-yellow-400 fill-current" />
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="text-center mt-12">
            <Link
              to="/products"
              className="inline-flex items-center px-8 py-4 rounded-lg bg-gradient-to-r from-cyan-500 to-purple-500 text-white font-semibold hover:from-purple-500 hover:to-cyan-500 transition-all duration-300"
            >
              View All Products
              <ArrowRight className="ml-2 h-5 w-5" />
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
};

export default LandingPage;