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

# Explanation of package structure
The project is a standard Maven project whose initial skeleton was creating using the Micronaut mn utility.
Purpose of different packages is detailed below. The classes are modeled around the Single Responsibility concept.

* cbo - Common Business Objects that are used for all operations
* client - Protocol specific clients for WebSocket and REST operation
* exceptions - Business exceptions
* trading - Classes that specialize in the actual trading operations or co-ordinating trading actions between differnt classes
* utils - Utilities for basic operations.

# Assumptions made

2. At the start of the program, there are no holdings with us. When the current price <= buying price, the buy order is executed.
3. Beyond #1, if the current price were to fall further, we will not buy any more
4. The amount of Product available for selling may be lesser than the amount needed. Yet, the buy will be processed.
5. Once the selling condition is reached, ie. current price is between the upper and lower selling prices, the sell of the whole holding is performed.
6. Once the selling is performed, the WebSocket client will process the close request and the program will close down.
7. Currency, source, decimals, and leverage are assumed to be those from the documents shared.
8. No multi-linual considerations are needed for this development. 
9. Ordering of trade status messages from the server is guaranteed.
