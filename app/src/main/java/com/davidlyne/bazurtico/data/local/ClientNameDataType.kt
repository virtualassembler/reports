package com.davidlyne.bazurtico.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Client data class
 *
 * data class for bill entity
 *
 * @author david.lyne
 */

@Entity(tableName = "client_name")
data class ClientNameDataType(
        @SerializedName("nameClient")
        var nameClient: String?

//        ,
//        @SerializedName("telClient")
//        var telClient: String?,
//        @ColumnInfo(name = "clientId")
//        var clientId: Int,
//        @ColumnInfo(name = "billId")
//        var billId: Int,
//        @ColumnInfo(name = "id")
//        var id: Int,
//        @ColumnInfo(name = "state")
//        var state: Int,
//        @ColumnInfo(name = "created_at") var createdAt: Long,
//        @ColumnInfo(name = "modified_at") var modifiedAt: Long
)
