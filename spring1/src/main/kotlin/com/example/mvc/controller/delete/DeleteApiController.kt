package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    //delete가 가질 수 있는 method의 형태
    //1. path variable
    //2. request param

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(@RequestParam(value = "name") _name:String, @RequestParam(name = "age") _age:Int): String {

        println(_name)
        println(_age)

        return _name + " " + _age

    }

    //path variable은 중괄호로 바로 지정을 해준다
    // name/{name}/age/{age} 이런것처럼 앞에 어떤 이름, 어떤 나이가 들어오는지 명시해준다
    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun delteMappingPath(@PathVariable(value = "name") _name:String, @PathVariable(name = "age") _age:Int): String {

        println(_name)
        println(_age)

        return _name + " " + _age

    }


}