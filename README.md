# Description of submission

The submission for assignment is made using the following :

* Java 12 
* Micronaut framework with CLI support using PicoCli
* Jetty Websocket Client
* SLF4J Logging
* RxHttpClient REST operations
* Maven

The project is built using Maven and the project may be built using

```
./mvnw clean package
```

To execute the program, the following command may be issued with suitable changes to the startup parameters.

```
java -jar bux-assignment-0.1.jar --productId sb26493 --buyPrice 11669.5 --upperSellPrice 11673 --lowerSellPrice 11670
```

# Assumptions made
1. At the start of the program, there are no holdings with us. When the current price <= buying price, the buy order is executed.
2. Beyong #1, if the current price were to fall further, we will not buy any more
3. The amount of Product available for selling may be lesser than the amount needed. Yet, the buy will be processed.
4. Once the selling condition is reached, ie. current price is between the upper and lower selling prices, the sell of the whole holding is performed.
5. Once the selling is performed, the WebSocket client will process the close request and the program will close down.
6. Currency, source, decimals, and leverage are assumed to be those from the documents shared.
7. No multi-linual considerations are needed for this development. 
