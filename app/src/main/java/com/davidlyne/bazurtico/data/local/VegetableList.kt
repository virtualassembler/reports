package com.davidlyne.bazurtico.data.local

/**
 * Vegetable List
 *
 * This is the source of Vegetable Objects
 *
 */

val vegetableList = listOf(
    VegetableDataType("Limon",80.0),
    VegetableDataType("Apio",2.8),
    VegetableDataType("Cilantro",6.6),
    VegetableDataType("Cebollin",3.6),
    VegetableDataType("Cebolla Blanca",2.9),
    VegetableDataType("Cebolla Roja",2.8),
    VegetableDataType("Platano Verde",4.8),
    VegetableDataType("Platano Amarillo",5.5),
    VegetableDataType("Name Diamante",1.2),
    VegetableDataType("Name Espina",2.5),
    VegetableDataType("Yuca",1.2),
    VegetableDataType("Papa",1.4),
    VegetableDataType("Lechuga",3.8),
    VegetableDataType("Tomate",3.3),
    VegetableDataType("Zanahoria",2.4),
    VegetableDataType("Habichuela",3.3),
    VegetableDataType("Ahuyama",1.5),
    VegetableDataType("Aji",6.6),
    VegetableDataType("Pimenton Rojo",4.4),
    VegetableDataType("Pimenton Verde",4.2),
    VegetableDataType("Ajo",9.7),
    VegetableDataType("Coco",2.8),
    VegetableDataType("Tomate de Arbol",3.8),
    VegetableDataType("Mora",2.2),
    VegetableDataType("Maracuya",3.8),
    VegetableDataType("Guayaba",2.8),
    VegetableDataType("Pepino",1.5)

)

fun getDefaultVegetableList(): List<VegetableDataType> {
    return vegetableList
}