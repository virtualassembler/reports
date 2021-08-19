package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Update

/**
 * Dataclass
 *
 * data class for vegetable
 *
 * @author david.lyne
 */


//@Update(entity = VegetableDataType::class)
//fun update(obj: VegetableDataType) {
//}


@Entity(tableName = "vegetable")
data class VegetableDataType(
        @ColumnInfo(name = "name")
        var name: String?,
        @ColumnInfo(name = "price")
        var price: Double,
        @ColumnInfo(name = "isUnit")
        var isUnit: Int
) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0
        /*
        override fun toString(): String {
        return strEvent.toString()
        }
        */

}
