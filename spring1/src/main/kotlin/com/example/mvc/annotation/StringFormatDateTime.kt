package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

//해당 annotation 이 붙은 field, getter setter 은 이것으로 검증하겠다
@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
//따로 annotation 을 만들어서 관리하는 방법(validation 추가)
//interface -> annotation class
@Target(
    //이렇게 세곳에 annotation을 사용할 수 있도록 지정
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@Retention(AnnotationRetention.RUNTIME) // runtime 에만 annotation 이용 가능
@MustBeDocumented //kotlin에서 붙여야 함
annotation class StringFormatDateTime (
        val pattern: String = "yyyy-MM-dd HH:mm:ss" ,
        val message: String = "시간 형식이 유효하지 않습니다" ,
        val group:Array<KClass<*>> = [] , //default로 이런 값들이 들어간다
        val payload: Array<KClass<out Payload>> = []
)




