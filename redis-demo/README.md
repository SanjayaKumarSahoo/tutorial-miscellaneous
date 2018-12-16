# redis-demo
A sample application for REDIS with spring boot with key value store, it stores in redis first and the push to DB.
    
    

    Step to run
      1 - Install redis, follow steps in https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-redis-on-ubuntu-16-04
      2 - $ mvn spring-boot:run
      3 - curl -H "Content-Type: application/json" -X POST -d '{"id":100,"firstName":"firstName","lastName":"lastName","address":{"id":null,"city":"city","street":"street"}}' http://localhost:8080/redis/person/create
      4-  curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/redis/person/all
          You should see below output  
          [
             {
                "id":100,
                "firstName":"firstName",
                "lastName":"lastName",
                "address":{
                   "id":null,
                   "city":"city",
                   "street":"street"
                }
             }
          ]
                  
   