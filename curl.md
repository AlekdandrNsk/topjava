### MealRestControllerTest



#### testGetAll
curl "http://localhost:8080/topjava/rest/meals"

#### testGet
curl "http://localhost:8080/topjava/rest/meals/100005"

#### testGetBetween
curl "http://localhost:8080/topjava/rest/meals/filter?startDate=2015-05-30&startTime=11:00:00&endDate=2015-05-31&endTime=14:00:00"

#### testDelete
curl -X DELETE "http://localhost:8080/topjava/rest/meals/100004"

#### testCreate
curl -d '{"dateTime":"2018-05-31T20:00:00","description": "NewMeal","calories": 888}' -H 'Content-Type:application/json' -X POST http://localhost:8080/topjava/rest/meals 

#### testUpdate
curl -d '{"dateTime":"2015-05-30T07:00", "description":"Updated breakfast", "calories":200}' -H 'Content-Type:application/json' -X PUT  http://localhost:8080/topjava/rest/meals/100006