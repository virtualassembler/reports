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
data class ClientDataClass(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,
        @SerializedName("nameClient")
        var nameClient: String?,
        @SerializedName("telClient")
        var telClient: String?
)
