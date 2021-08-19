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

@Entity(tableName = "selected_vegetable")
data class SelectedVegetableDataType(
        @ColumnInfo(name = "name")
        var name: String?,
        @ColumnInfo(name = "price")
        var price: Double,
        @ColumnInfo(name = "isUnit")
        var isUnit: Int,
        @ColumnInfo(name = "vegetableId")
        var vegetableId: Int,
        @ColumnInfo(name = "billId")
        var billId: Int,
        @ColumnInfo(name = "grams")
        var grams: Int
)
