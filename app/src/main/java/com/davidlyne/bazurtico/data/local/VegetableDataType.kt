package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Dataclass
 *
 * data class for vegetable
 *
 * @author david.lyne
 */
@Entity(tableName = "vegetable")
data class VegetableDataType(
        @ColumnInfo(name = "name")
        var name: String?,
        @ColumnInfo(name = "price")
        var price: Double
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
