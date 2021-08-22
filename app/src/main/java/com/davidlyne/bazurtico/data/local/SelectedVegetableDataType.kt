package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Update

/**
 * Dataclass
 *
 * @author david.lyne
 */

@Entity(tableName = "selected_vegetable")
data class SelectedVegetableDataType(
        @ColumnInfo(name = "clientId")
        var clientId: Int,
        @ColumnInfo(name = "state")
        var state: Int,
        @ColumnInfo(name = "year")
        var year: Int,
        @ColumnInfo(name = "month")
        var month: Int,
        @ColumnInfo(name = "day")
        var day: Int,
        @ColumnInfo(name = "day_name")
        var day_name: String,
        @ColumnInfo(name = "created_at")
        var createdAt: Long,
        @ColumnInfo(name = "modified_at")
        var modifiedAt: Long,
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
