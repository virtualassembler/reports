package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Dataclass
 *
 * data class for BillVegetable
 *
 * @author david.lyne
 */

@Entity(tableName = "bill_vegetable")
data class BillVegetableDataType(
        @ColumnInfo(name = "vegetableId")
        var vegetableId: Int,
        @ColumnInfo(name = "billId")
        var billId: Int,
        @ColumnInfo(name = "grams")
        var grams: Int,
        @ColumnInfo(name = "price")
        var price: Float
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
