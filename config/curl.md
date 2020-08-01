### curl methods (application deployed in application context `restaurant_voting`)

> ---------------- Please, use Postman to testing REST web-service --------------------------
>                  (Restaurant_voting.postman_collection.json)
>
### curl samples (application deployed in application context `topjava`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/restaurant_voting/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/restaurant_voting/rest/admin/users/100001 --user admin@gmail.com:admin`

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/register`

#### get Profile
`curl -s http://localhost:8080/restaurant_voting/rest/profile --user test@mail.ru:test-password`

#### get all reports by authUser
`curl -s http://localhost:8080/restaurant_voting/rest/admin/users/voting --user test@mail.ru:test-password`

#### create new authUser voting-report
`curl -s -i -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant_voting/rest/admin/users/voting/restaurants/100003`


> For more testing Api - use Restaurant_voting.postman_collection.json