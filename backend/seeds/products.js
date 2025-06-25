import axios from 'axios'
import fs from 'fs/promises'

const products = JSON.parse(await fs.readFile('./data/products.json', 'utf-8'))

const instance = axios.create({
    baseURL: "http://localhost:3000/api",
})

let queue = []

products.forEach((product) => {
    queue.push(
        instance.post('/products', {
            name: product.title,
            description: product.description,
            price: product.price,
            category: product.category,
            imageUrl: product.thumbnail,
            stock: product.stock
        })
            .then(response => {
                console.log(`Product ${product.title} created successfully:`, response.data);
            })
            .catch(error => {
                console.error(`Error creating product ${product.username}:`, error.response ? error.response.data : error.message);
            })
    );
})

await Promise.all(queue)

console.log('All product creation requests have been processed.');