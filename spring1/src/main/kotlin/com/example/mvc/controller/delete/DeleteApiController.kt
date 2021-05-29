package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
//@Validation은 bean 기준으로 되며, age는 bean 이 아니기 때문에 @Validation을 걸어줘야 검증이 된다
@RestController
@RequestMapping("/api")
@Validated // 이 annotation을 이용해 하위의 validation annotation들이 작동할 수 있다
class DeleteApiController {

    //delete가 가질 수 있는 method의 형태
    //1. path variable
    //2. request param

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(@RequestParam(value = "name") _name:String,
                      @NotNull(message = "age 값이 누락되었습니다")
                      @Min(value = 20, message = "age는 20보다 커야합니다")
                      @RequestParam(name = "age") _age:Int): String {

        println(_name)
        println(_age)

        return _name + " " + _age

    }

    //path variable은 중괄호로 바로 지정을 해준다
    // name/{name}/age/{age} 이런것처럼 앞에 어떤 이름, 어떤 나이가 들어오는지 명시해준다
    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun delteMappingPath(@PathVariable(value = "name")
                         @Size(min = 2, max = 5, message = "name 의 길이는 2~5") // aa ~ aaaaa
                         @NotNull _name:String,

                         @NotNull(message = "age 값이 누락되었습니다")
                         @Min(value = 20, message = "20보다 커야합니다")
                         @PathVariable(name = "age")
                         _age:Int
    ): String {

        println(_name)
        println(_age)

        return _name + " " + _age

    }


}