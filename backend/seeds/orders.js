import axios from 'axios'
import fs from 'fs/promises'

const products = JSON.parse(await fs.readFile('./data/orders.json', 'utf-8'))

const instance = axios.create({
    baseURL: "http://localhost:3000/api",
})

let queue = []

products.forEach((order) => {
    queue.push(
        instance.post('/orders', order)
            .then(response => {
                console.log(`Order ${order.userId} created successfully:`, response.data);
            })
            .catch(error => {
                console.error(`Error creating order ${order.userId}:`, error.response ? error.response.data : error.message);
            })
    );
})

await Promise.all(queue)

console.log('All order creation requests have been processed.');