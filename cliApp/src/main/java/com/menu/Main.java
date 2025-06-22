package com.menu;

import java.util.Scanner;

import com.order.Order;
import com.product.Product;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        System.out.println("Bienvenido a mi pequeña aplicación de ecommerce en Java");
        System.out.println(
                "Esta aplicación es un ejercicio de programación y no está destinada a ser utilizada en producción");
        System.out.println("Por favor, no ingreses información sensible o personal en esta aplicación");
        System.out.println("Disfruta de la experiencia y aprende algo nuevo :)");
        System.out.println("--------------------------------------------------");
        while (isRunning) {
            isRunning = mainMenu();
        }
        System.out.println("Gracias por usar mi aplicación de ecommerce en Java. ¡Hasta luego!");
        System.out.println("--------------------------------------------------");
    }

    private static boolean mainMenu() {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Crear Producto");
        System.out.println("2. Listar Productos");
        System.out.println("3. Buscar y Actualizar Producto");
        System.out.println("4. Eliminar Producto");
        System.out.println("5. Crear Pedido");
        System.out.println("6. Listar Pedidos");
        System.out.println("7. Salir");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (option) {
            case 1:
                createProductMenu();
                break;
            case 2:
                listAllProducts();
                break;
            case 3:
                searchAndUpdateProductMenu();
                break;
            case 4:
                deleteProductMenu();
                break;
            case 5:
                createOrderMenu();
                break;
            case 6:
                listAllOrders();
                break;
            case 7:
                return false;
            default:
                System.out.println("Opción no válida, por favor intenta de nuevo");
                break;
        }
        return true;
    }

    public static void createProductMenu() {
        System.out.println("Ingrese el nombre: ");
        final String name = scanner.nextLine();
        System.out.println("Ingrese el precio unitario: ");
        final Double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Ingrese el stock inicial: ");
        final Integer initialStock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Crear objeto con estos datos? Y/n");
        final String confirmation = scanner.next();
        scanner.nextLine();
        if (confirmation.equalsIgnoreCase("Y")) {
            final Product newProduct = new Product(name, price, initialStock);
            System.out.println("Objeto creado con id: " + newProduct.getId());
        } else {
            System.out.println("Cancelado con éxito");
        }
    }

    public static void deleteProductMenu() {
        System.out.println("Buscar por:");
        System.out.println("1. id");
        System.out.println("2. nombre");
        final Integer option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                System.out.println("Ingrese el id del producto");
                final Integer id = scanner.nextInt();
                scanner.nextLine();
                final Product removedProduct = Product.deleteProductById(id);
                if (removedProduct != null) {
                    System.out.println("El producto " + removedProduct.getName() + " con id " + removedProduct.getId()
                            + " fue eliminado");
                    Order.cleanNullProducts();
                } else {
                    System.out.println("Un producto con id " + id + " no existe");
                }
                break;
            case 2:
                System.out.println("Ingrese el nombre del producto");
                final String name = scanner.next();
                scanner.nextLine();
                final Product targetProduct = Product.getProductByName(name);
                if (targetProduct != null) {
                    Product.deleteProductById(targetProduct.getId());
                    System.out.println("El producto " + targetProduct.getName() + " con id " + targetProduct.getId()
                            + " fue eliminado");
                    Order.cleanNullProducts();
                } else {
                    System.out.println("Un producto con nombre " + name + " no existe");
                }
                break;
            default:
                System.out.println("Opción no válida, por favor intenta de nuevo");
                break;
        }
    }

    public static void searchAndUpdateProductMenu() {
        System.out.println("Buscar por:");
        System.out.println("1. id");
        System.out.println("2. nombre");
        final Integer option = scanner.nextInt();
        scanner.nextLine();
        Product targetProduct;
        switch (option) {
            case 1:
                System.out.println("Ingrese el id del producto");
                final Integer id = scanner.nextInt();
                scanner.nextLine();
                targetProduct = Product.getProductById(id);
                if (targetProduct != null) {
                    System.out.println("Producto encontrado");
                    System.out.println("Nombre: " + targetProduct.getName());
                    System.out.println("Precio unitario: " + targetProduct.getPrice());
                    System.out.println("Stock: " + targetProduct.getStock());
                    System.out.println("¿Desea actualizar este producto? (Y/n)");
                    final String confirmation = scanner.next();
                    scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("n")) {
                        break;
                    }
                    System.out.println("Ingrese el nuevo nombre (dejar en blanco para no cambiar): ");
                    final String newName = scanner.nextLine();
                    if (!newName.isBlank()) {
                        targetProduct.setName(newName);
                    }
                    System.out.println("Ingrese el nuevo precio unitario (dejar en blanco para no cambiar): ");
                    final String newPriceInput = scanner.nextLine();
                    if (!newPriceInput.isBlank()) {
                        final Double newPrice = Double.parseDouble(newPriceInput);
                        targetProduct.setPrice(newPrice);
                    }
                    System.out.println("Ingrese el nuevo stock (dejar en blanco para no cambiar): ");
                    final String newStockInput = scanner.nextLine();
                    if (!newStockInput.isBlank()) {
                        final Integer newStock = Integer.parseInt(newStockInput);
                        targetProduct.setStock(newStock);
                    }
                    System.out.println("Producto actualizado con éxito");
                    System.out.println("Nuevo nombre: " + targetProduct.getName());
                    System.out.println("Nuevo precio unitario: " + targetProduct.getPrice());
                    System.out.println("Nuevo stock: " + targetProduct.getStock());
                } else {
                    System.out.println("Un producto con id " + id + " no existe");
                }
                break;
            case 2:
                System.out.println("Ingrese el nombre del producto");
                final String name = scanner.next();
                scanner.nextLine();
                targetProduct = Product.getProductByName(name);
                if (targetProduct != null) {
                    System.out.println("Producto encontrado");
                    System.out.println("Nombre: " + targetProduct.getName());
                    System.out.println("Precio unitario: " + targetProduct.getPrice());
                    System.out.println("Stock: " + targetProduct.getStock());
                    System.out.println("¿Desea actualizar este producto? (Y/n)");
                    final String confirmation = scanner.next();
                    scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("n")) {
                        break;
                    }
                    System.out.println("Ingrese el nuevo nombre (dejar en blanco para no cambiar): ");
                    final String newName = scanner.nextLine();
                    if (!newName.isBlank()) {
                        targetProduct.setName(newName);
                    }
                    System.out.println("Ingrese el nuevo precio unitario (dejar en blanco para no cambiar): ");
                    final String newPriceInput = scanner.nextLine();
                    if (!newPriceInput.isBlank()) {
                        final Double newPrice = Double.parseDouble(newPriceInput);
                        targetProduct.setPrice(newPrice);
                    }
                    System.out.println("Ingrese el nuevo stock (dejar en blanco para no cambiar): ");
                    final String newStockInput = scanner.nextLine();
                    if (!newStockInput.isBlank()) {
                        final Integer newStock = Integer.parseInt(newStockInput);
                        targetProduct.setStock(newStock);
                    }
                    System.out.println("Producto actualizado con éxito");
                    System.out.println("Nuevo nombre: " + targetProduct.getName());
                    System.out.println("Nuevo precio unitario: " + targetProduct.getPrice());
                    System.out.println("Nuevo stock: " + targetProduct.getStock());
                } else {
                    System.out.println("Un producto con nombre" + name + " no existe");
                }
                break;
            default:
                System.out.println("Opción no válida, por favor intenta de nuevo");
                break;
        }
    }

    public static void listAllProducts() {
        if (Product.getProducts().isEmpty()) {
            System.out.println("No hay productos disponibles");
        } else {
            System.out.println("Lista de productos:");
            for (Product product : Product.getProducts().values()) {
                System.out.println("id: " + product.getId() + "\tNombre: " + product.getName() +
                        "\tPrecio: " + product.getPrice() + "\tStock: " + product.getStock());
            }
        }
        System.out.println("Total de productos: " + Product.getProducts().size());
    }

    public static void createOrderMenu() {
        Order newOrder = new Order();
        Boolean isAddingProducts = true;
        while (isAddingProducts) {
            System.out.println("Agragar producto buscando por:");
            System.out.println("1. id");
            System.out.println("2. nombre");
            System.out.println("3. Salir");
            final Integer option = scanner.nextInt();
            scanner.nextLine();
            Product targetProduct;
            switch (option) {
                case 1:
                    System.out.println("Ingrese el id del producto");
                    final Integer id = scanner.nextInt();
                    scanner.nextLine();
                    targetProduct = Product.getProductById(id);
                    if (targetProduct != null) {
                        System.out.println("Ingrese la cantidad a agregar: ");
                        final Integer quantity = scanner.nextInt();
                        scanner.nextLine();
                        if (quantity <= targetProduct.getStock()) {
                            newOrder.addItem(targetProduct, quantity);
                            System.out.println("Producto agregado al pedido");
                            targetProduct.setStock(targetProduct.getStock() - quantity);
                            System.out.println("Sobran " + targetProduct.getStock() + " en stock");
                        } else {
                            System.out.println(
                                    "La cantidad solicitada excede el stock disponible de " + targetProduct.getStock());

                        }
                    } else {
                        System.out.println("Un producto con id " + id + " no existe");
                    }
                    System.out.println("¿Desea agregar otro producto? (Y/n)");
                    final String confirmation = scanner.next();
                    scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("n")) {
                        isAddingProducts = false;
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del producto");
                    final String name = scanner.next();
                    scanner.nextLine();
                    targetProduct = Product.getProductByName(name);
                    if (targetProduct != null) {
                        System.out.println("Ingrese la cantidad a agregar: ");
                        final Integer quantity = scanner.nextInt();
                        scanner.nextLine();
                        if (quantity <= targetProduct.getStock()) {
                            newOrder.addItem(targetProduct, quantity);
                            System.out.println("Producto agregado al pedido");
                            targetProduct.setStock(targetProduct.getStock() - quantity);
                            System.out.println("Sobran " + targetProduct.getStock() + " en stock");
                        } else {
                            System.out.println(
                                    "La cantidad solicitada excede el stock disponible de " + targetProduct.getStock());

                        }
                    } else {
                        System.out.println("Un producto con nombre " + name + " no existe");
                    }
                    System.out.println("¿Desea agregar otro producto? (Y/n)");
                    final String confirmation1 = scanner.next();
                    scanner.nextLine();
                    if (confirmation1.equalsIgnoreCase("n")) {
                        isAddingProducts = false;
                    }
                    break;
                case 3:
                    isAddingProducts = false;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo");
                    isAddingProducts = false;
                    break;
            }
        }
        System.out.println("Pedido guardado con id: " + newOrder.getId());
    }

    public static void listAllOrders() {
        if (Order.getOrders().isEmpty()) {
            System.out.println("No hay pedidos guardados");
        } else {
            System.out.println("Lista de pedidos:");
            for (Order order : Order.getOrders().values()) {
                Double totalPrice = 0.0d;
                System.out.println("Pedido con id " + order.getId());
                System.out.println("Productos en el pedido:");
                for (Integer itemId : order.getItems().keySet()) {
                    Product itemProduct = Product.getProductById(itemId);
                    Integer itemQuantity = order.getItems().get(itemId);
                    Double subTotal = itemProduct.getPrice() * itemQuantity;
                    totalPrice += subTotal;
                    System.out.println("\tid: " + itemProduct.getId() +
                            "\tNombre: " + itemProduct.getName() +
                            "\tCantidad: " + itemQuantity +
                            "\tSubtotal: " + subTotal);
                }
                System.out.println("Total del pedido: " + totalPrice);
                System.out.println("--------------------------------------------------");
            }
            System.out.println("Total de pedidos: " + Order.getOrders().size());
        }
    }

}