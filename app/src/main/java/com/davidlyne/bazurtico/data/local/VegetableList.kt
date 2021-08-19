package com.davidlyne.bazurtico.data.local

/**
 * Vegetable List
 *
 * This is the source of Vegetable Objects
 *
 */

val vegetableList = listOf(
    VegetableDataType("Limon",80.0,1),
    VegetableDataType("Apio",3.0,0),
    VegetableDataType("Cilantro",6.6,0),
    VegetableDataType("Cebollin",3.6,0),
    VegetableDataType("Cebolla Blanca",2.9,0),
    VegetableDataType("Cebolla Roja",2.8,0),
    VegetableDataType("Platano Verde",4.8,1),
    VegetableDataType("Platano Amarillo",5.5,1),
    VegetableDataType("Name Diamante",1.2,0),
    VegetableDataType("Name Espina",2.5,0),
    VegetableDataType("Yuca",1.2,0),
    VegetableDataType("Papa",1.4,0),
    VegetableDataType("Papa medio bulto",27500.0,1),
    VegetableDataType("Papa bulto",55000.0,1),
    VegetableDataType("Lechuga",3.8,0),
    VegetableDataType("Tomate",2.9,0),
    VegetableDataType("Zanahoria",2.4,0),
    VegetableDataType("Habichuela",3.3,0),
    VegetableDataType("Ahuyama",1.5,0),
    VegetableDataType("Aji",5.8,0),
    VegetableDataType("Pimenton Rojo",4.4,0),
    VegetableDataType("Pimenton Verde",4.2,0),
    VegetableDataType("Ajo",9.7,0),
    VegetableDataType("Coco",2.8,1),
    VegetableDataType("Tomate de Arbol",3.8,0),
    VegetableDataType("Mora",2.4,0),
    VegetableDataType("Maracuya",3.8,0),
    VegetableDataType("Guayaba",2.8,0),
    VegetableDataType("Pepino",1.8,0)
)

//val vegetableList = listOf(
//    VegetableDataType("Limon",80.0,1),
//    VegetableDataType("Apio",3.0,1)
//)

fun getDefaultVegetableList(): List<VegetableDataType> {
    return vegetableList
}