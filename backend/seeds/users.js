import axios from 'axios'
import fs from 'fs/promises'

const users = JSON.parse(await fs.readFile('./data/users.json', 'utf-8'))

const instance = axios.create({
    baseURL: "http://localhost:3000/api",
})

let queue = []

users.forEach((user) => {
    queue.push(
        instance.post('/users', {
            username: user.username,
            password: user.password,
            role: user.role,
        })
            .then(response => {
                console.log(`User ${user.username} created successfully:`, response.data);
            })
            .catch(error => {
                console.error(`Error creating user ${user.username}:`, error.response ? error.response.data : error.message);
            })
    );
})

await Promise.all(queue)

console.log('All user creation requests have been processed.');