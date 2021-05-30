package com.example.todo.database

import com.example.todo.model.http.TodoDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Todo (

        var index : Int ?= null, //일정 index
        var title : String ?= null, //일정 타이틀
        var description:String ?= null, //일정 설명
        var schedule: LocalDateTime ?= null, //일정 시간
        var createdAt : LocalDateTime ?= null, //생성 시간
        var updatedAt : LocalDateTime ?= null // 업데이트 시간
)

fun Todo.convertTodo(todoDto : TodoDto): Todo{
    //코드, modelMapper, kotlin reflection 등 있음
    return Todo().apply{
        this.index = todoDto.index
        this.title = todoDto.title
        this.description = todoDto.description
        this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createdAt = todoDto.createdAt
        this.updatedAt = todoDto.updatedAt
    }
}


