package com.davidlyne.bazurtico.data.local

/**
 * Vegetable List
 *
 * This is the source of Vegetable Objects
 *
 */

val vegetableList = listOf(
    Vegetable(1,"Limon",80),
    Vegetable(2,"Apio",2),
    Vegetable(3,"Cilantro",8),
    Vegetable(4,"Cebollin",3)
)

fun getDefaultVegetableList(): List<Vegetable> {
    return vegetableList
}