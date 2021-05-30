package com.example.todo.database

//data class 로 설정
data class TodoDateBase(

    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf()
){

    fun init(){

        //db 뿐만 아니라 index 초기화도 필요
        this.index = 0
        this.todoList = mutableListOf()
        println("[DEBUG] todo database init")


    }
}