package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    // GET http://localhost:8080/api/response
    // required default : true
    // ? 붙이면 필수값이 아닐 수 있다
    @GetMapping("")
    fun getMapping(@RequestParam age : Int?): ResponseEntity<String> {


        //age 가 null 이 아닐경우
        return age?.let {

            if (it < 20){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락됐습니다")
            }

            ResponseEntity.ok("ok")

        }?: kotlin.run{
            // age is null

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락됐습니다")

        }
        /*
        // 0. age == null -> 400 error
        if (age == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락됐습니다")
        }
        // 1. age < 20 -> 400 error
        if (age < 20){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값은 20보다 커야합니다")
        }

         */



    }

    // 2. post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        // body를 비우도록 했다
        return ResponseEntity.status(200).body(userRequest) // object mapper -> object -> json
    }

    // 3. put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest:UserRequest?): ResponseEntity<UserRequest> {
        // 1. 기존 데이터가 없어서 새로 생성했다
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    //Nothing을 Any로 바꿔서 null 도 들어갈 수 있도록 했음
    fun deleteMapping(@PathVariable id:Int): ResponseEntity<Any> {

        return ResponseEntity.status(500).body(null)
    }

}