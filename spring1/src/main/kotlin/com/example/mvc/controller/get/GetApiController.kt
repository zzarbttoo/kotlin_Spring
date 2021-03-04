package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*


@RestController // REST API Controller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    //현재
    @GetMapping("/hello") //http://localhost:8080/api/hello
    fun hello(): String {
        return "hello kotlin"

    }

    //옛날
    //path 로 지정할 경우 여러가지 형태로 들어갈 수 있다
    @RequestMapping(method = [ RequestMethod.GET], path = ["request-mapping", "/abcd"])
    fun requestMapping(): String {

        return "request-mapping"

    }

    //type이 맞지 않으면 casting error 발생
    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/steve
    fun pathVariable(@PathVariable name:String, @PathVariable age:Int): String {

        println("${name}, ${age}")

        return name + " " + age
    }
    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/steve
    fun pathVariable2(@PathVariable(value= "name") _name:String, @PathVariable(name="age") age:Int): String {

        val name = "kotiln"
        println("${_name}, ${age}")

        return name + " " + age
    }

    //https://localhost:8080/api/page?key=value&key=value&key=value
    @GetMapping("/get-mapping/query-param")
    fun queryParam(@RequestParam name:String, @RequestParam(value = "age") age:Int) : String{

        println("${name}, ${age}")
        return name + " " + age
    }

    // name, age, address, email
    // url에서는 - 쓸 수 없음
    // phoneNumber(대문자 불가) -> phone-number , phonenumber
    // phone-number 같은 것은 파싱할 수 없다
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {

        println(userRequest)
        return userRequest

    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map.values)

        val phoneNumber = map.get("phone-number")
        println(phoneNumber)
        return map
    }


}