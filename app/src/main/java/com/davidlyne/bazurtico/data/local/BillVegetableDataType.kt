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
@Entity(tableName = "vegetable")
data class BillVegetableDataType(
        @ColumnInfo(name = "vegetableId")
        var vegetableId: Int,
        @ColumnInfo(name = "name")
        var grams: Int,
        @ColumnInfo(name = "price")
        var price: Double
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
