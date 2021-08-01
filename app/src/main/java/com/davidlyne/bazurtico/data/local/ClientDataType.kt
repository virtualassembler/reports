package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Client data class
 *
 * data class for client entity
 *
 * @author david.lyne
 */

@Entity(tableName = "client")
data class ClientDataType(
        @SerializedName("nameClient")
        var nameClient: String?,
        @SerializedName("telClient")
        var telClient: String?
){
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0
}
