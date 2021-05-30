package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import com.example.todo.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(description = "일정 관리") //swagger description 추가
@RestController
@RequestMapping("/api/todo")
class TodoApiController(
        val todoService : TodoService
) {

    //R
    @ApiOperation(value = "일정확인", notes = "일정 확인 GET API")
    @GetMapping(path = [""])
    fun read(
            @ApiParam(name = "index")
            @RequestParam(required = false) index:Int?): ResponseEntity<Any?>{

        //index가 있다면 return, 아니라면 전체 출력 부분으로 redirect
        return index?.let{
            todoService.read(index)
        }?.let{
            return ResponseEntity.ok(it)
        }?: kotlin.run{
            //302 error를 일으켜 redirect
            //이는 return type 이 다르기 때문에 이렇게 처리 하는 것이다
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/api/todo/all").build()
        }

    }

    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    //C

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto:TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    //TODO : create = 201, update = 200 이 떨어지도록 수정해야한다
    //TODO : ResponseEntity로 바꿔줘야한다
    //U
    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDto:TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }


    //D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index:Int) : ResponseEntity<Any>{

        if(!todoService.delete(_index)){
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()
    }

}