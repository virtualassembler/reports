package com.davidlyne.bazurtico.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * ClientDao
 *
 * This interface provides de DAO for client entity to Access Database @Entity(tableName = "client")
 *
 * @author david.lyne
 */
@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClient(clientDataType: ClientDataType)

    @Query("SELECT * FROM client")
    fun getClientList(): List<ClientDataType>

    @Query("SELECT * FROM client WHERE client.id=:id")
    fun getClientDetail(id: Int): ClientDataType

    /*
    @Query("DELETE FROM client")
    fun deleteAllClients()
    */
}
