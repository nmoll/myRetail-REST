# myRetail-REST

myRetail-REST is a RESTful application which purpose is to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

The application aggregates it's product data from:
- A name lookup from redsky.target.com using an HTTP GET request
- A pricing lookup from it's internal NoSQL MongoDb source.

## Setting up

The tools used for this application are:
- Spring with Spring Boot
- Maven
- MongoDb

### Cloning the project
Clone the application using the command: `git clone https://github.com/nmoll/myRetail-REST.git`

### Installing and running MongoDb
If you don't already have MongoDb installed, follow the instructions for your OS at http://docs.mongodb.org/manual/installation/.

Once  it is installed, run `mongod` to start the mongo server. Then in a new terminal, run `mongo --host 127.0.0.1:27017`.

## Interacting with the Application
Launch the application by running `Application.java` class.

### Fetching a Product
To fetch a product, make an HTTP GET request at `localhost:8080/products/{id}`.

For example using curl with a product id that exists: `curl localhost:8080/product/13860428` will return a response containing product details similar to `{"id":13860428,"name":"The Big Lebowski (Blu-ray)","currentPrice":15.0}` 

and attempting to fetch a product which doesn't exist: `curl localhost:8080/product/13860428` will return a "Not Found" message similar to `{"timestamp":"2018-06-16T17:13:13.325+0000","status":404,"error":"Not Found","message":"Product not found","path":"/product/111"}`
