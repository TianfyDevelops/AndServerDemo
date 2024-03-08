package com.kcst.retrofit

import java.lang.reflect.Array
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.lang.reflect.WildcardType

fun getRawType(type: Type?): Class<*>? {
    if (type is Class<*>) {
        return type
    } else if (type is ParameterizedType) {

        // I'm not exactly sure why getRawType() returns Type instead of Class.
        // Neal isn't either but suspects some pathological case related
        // to nested classes exists.
        val rawType = type.rawType
        return rawType as Class<*>
    } else if (type is GenericArrayType) {
        val componentType = type.genericComponentType
        return Array.newInstance(getRawType(componentType), 0).javaClass
    } else if (type is TypeVariable<*>) {
        // we could use the variable's bounds, but that won't work if there are multiple.
        // having a raw type that's more general than necessary is okay
        return Any::class.java
    } else if (type is WildcardType) {
        return getRawType(type.upperBounds[0])
    } else {
        val className = if (type == null) "null" else type.javaClass.name
        throw IllegalArgumentException(
            "Expected a Class, ParameterizedType, or "
                    + "GenericArrayType, but <" + type + "> is of type " + className
        )
    }
}