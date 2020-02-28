# Nabucco's Pet Store - Log Access Analytics


#### Disclaimer
The java stack (reactivex + micronaut) and devops (vagrant) was learned at test time. Basically my most experience is based on docker, docker-compose, Spring boot, kubernetes and eks environment without large knowledge in route 53 and ELB / ALB.
So, I must thank you for the opportunity to learn a new framework meanwhile make this test for Ilegra :).
Sadly I didn't accomplish the automation task and the tests. Sorry. 

##### All the anwser are present on text folder



### TO RUN THE POC
cd into devops/environment/local and run vagrant up 

#### Request examples
```sh
ab -n 2 -c 2 -T application/json -p example.json http://localhost:8080/laa/ingest
curl http://localhost:8080/laa/health
curl http://localhost:8080/laa/metrics
curl -H "Content-Type: application/json" -v http://localhost:8080/laa/ingest -d '["/pets/exotic/dogs/cusco/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 1", "/pets/guaipeca/dogs/1 1037825323957 5b019db5-b3d0-46d2-9963-437860af707g 2", "/tiggers/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 3"]'
```