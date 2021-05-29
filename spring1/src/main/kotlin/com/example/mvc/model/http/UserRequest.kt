package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

//해당 case는 snake case로 동작할 것이다
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class UserRequest {

    //kotlin에서는 field 라는 annotation을 사용해 field 에 대한 값을 정의해준다
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String? = null

    @field:PositiveOrZero // 0보다 큰 숫자를 검증한다 (양수)
    var age: Int? = null

    @field:Email // email 양식을 검증해준다
    var email: String? = null

    @field:NotBlank
    var address: String? = null
    //api 는 대부분 snake case
    //@JsonProperty("phone_number")

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") //정규식 검증
    var phoneNumber : String?=null // phone_number

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다")
    var createdAt:String?=null  // yyyy-MM-dd HH:mm:ss ex) 2020-10-02 13:00:00 형태로 시간을 받을 것이다

    // kotlin에서는 변수명에 -를 넣을 수 없다
    //var address-hello: ?=null


    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 이여야 합니다") // 검증 과정에서 메소드가 실행될 수 있도록
    private fun isValidCreatedAt():Boolean{ //정상 true, 비정상 false

        return try{

            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true

        }catch(e:Exception){
            false
        }
    }


}



