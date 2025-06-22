# Cli ecommerce mini app

This is a mini ecommerce cli app, it allows in memory product creation with an initial stock and price. It also allows simple order creation.

## Status

This is only a simple app example and should not be trated as a complete project.

## Why?

This is my pre submission for the "Projecto Tech Java backend" project

## Use Process

Once runned, you should type the number of the option you want to execute. Then you should type all the data it ask.

## Installation

You should have JDK21 and Maven in your system. Then in the root folder run:
```sh
   mvn package
   java -jar target/ecommerceminiapp-1.0.0.jar
```
You can also compile it only with java (also in the root folder) with:
```sh
   javac -d ./target/classes src/main/java/com/menu/Main.java src/main/java/com/product/Product.java src/main/java/com/order/Order.java
   java -cp ./target/classes src/main/java/com/menu/Main.java
```

## License

This project is licensed under the BSD 3-Clause License - see the [LICENSE](LICENSE.md) file for details.

## Support

If you encounter any issues or have questions, please file an issue on the [GitHub issue tracker](https://github.com/).