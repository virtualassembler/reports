package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Client data class
 *
 * data class for bill entity
 *
 * @author david.lyne
 */

@Entity(tableName = "bill")
data class BillDataType(
        @ColumnInfo(name = "clientId")
        var clientId: Int,
        @ColumnInfo(name = "state")
        var state: Int,
        @ColumnInfo(name = "created_at") var createdAt: Long,
        @ColumnInfo(name = "modified_at") var modifiedAt: Long
){
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0
}
