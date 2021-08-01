package com.davidlyne.bazurtico.data.local

/**
 * Vegetable List
 *
 * This is the source of Vegetable Objects
 *
 */

val vegetableList = listOf(
    VegetableDataType(1,"Limon",80),
    VegetableDataType(2,"Apio",2),
    VegetableDataType(3,"Cilantro",8),
    VegetableDataType(4,"Cebollin",3)
)

fun getDefaultVegetableList(): List<VegetableDataType> {
    return vegetableList
}