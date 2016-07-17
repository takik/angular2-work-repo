# angular2-work-repo


Project WS
PRoject Angular 2

1/Create Hero :

 curl -H "Content-Type: application/json" -X POST -d '{"id":"1","name":"Hero 1"}' "http://localhost:8090/heroes-ws/create"
 
 2/List Heroes
 
curl -H "Content-Type: application/json" http://localhost:8090/heroes-ws/list

3/ Get Hero

curl -H "Content-Type: application/json" http://localhost:8090/heroes-ws/get/1

4/Update Hero

curl -H "Content-Type: application/json" -X PUT -d '{"id":"1","name":"Hero 1.1"}' "http://localhost:8090/heroes-ws/update/1"

5/Delete Hero

curl -H "Content-Type: application/json" -X DELETE  "http://localhost:8090/heroes-ws/delete/1"

