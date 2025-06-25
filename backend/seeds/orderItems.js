import axios from 'axios'
import fs from 'fs/promises'

const { data: products } = await axios.get("http://localhost:3000/api/products")

const instance = axios.create({
    baseURL: "http://localhost:3000/api",
})

let queue = []

for (let orderId = 1; orderId <= 30; orderId++) {
    for (let ii = 0; ii < 10; ii++) {
        let randomIndex = Math.floor(Math.random() * products.length);
        let product = products[randomIndex];
        let quantity = Math.floor(Math.random() * 5) + 1;
        if (product.quantity < quantity) {
            continue;
        }
        product.quantity -= quantity;
        queue.push(
            instance.post("/orderItems", {
                orderId: orderId,
                productId: product.id,
                quantity: quantity
            })
                .then(response => {
                    console.log(`OrderItem ${orderId} created successfully:`, response.data);
                })
                .catch(error => {
                    console.error(`Error creating order ${orderId}:`, error.response ? error.response.data : error.message);
                })
        )
    }
}

await Promise.all(queue)

console.log('All product creation requests have been processed.');