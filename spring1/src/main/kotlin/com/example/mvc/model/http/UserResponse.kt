package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

//root는 class이름을 사용하지 않는다
data class UserResponse(
        //패키지 잘 봐서 import
        var result:Result?=null,
        var description:String?=null,

        //UserRequest값이 list 형태로 들어가야한다(kotlin의 mutablelist로 사용)
        //json에는 user로 출력되어야한다
        @JsonProperty("user")
        var userRequest:MutableList<UserRequest> ?= null
        //사실 user:MutableList<UserRequest> ?= null 이렇게 해도 동일한 결과가 나온다

)


/*
    //둘다 snake_case
    "result" : {
        "result_code" : "OK",
        "result_message" : "성공"
    }

 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Result(
    var resultCode:String?=null, // result_code
    var resultMessage:String?=null //result_message
)


