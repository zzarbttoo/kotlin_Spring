package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

//해당 case는 snake case로 동작할 것이다
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class UserRequest {

    var name: String? = null
    var age: Int? = null
    var email: String? = null
    var address: String? = null
    //api 는 대부분 snake case
    //@JsonProperty("phone_number")
    var phoneNumber : String?=null // phone_number

    // kotlin에서는 변수명에 -를 넣을 수 없다
    //var address-hello: ?=null

}
