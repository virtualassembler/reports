package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Dataclass
 *
 * data class for vegetable
 *
 * @author david.lyne
 */
@Entity(tableName = "vegetable")
data class Vegetable(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,
        @ColumnInfo(name = "name")
        var name: String?,
        @ColumnInfo(name = "price")
        var price: Int
//        @SerializedName("strEvent")
//        var strEvent: String?
) {
    /*
    override fun toString(): String {
        return strEvent.toString()
    }
    */
}
