package com.davidlyne.bazurtico.data.local

import android.provider.SyncStateContract.Helpers.insert
import androidx.room.*

/**
 * BillDao
 *
 * This interface provides de DAO for client entity to Access Database @Entity(tableName = "bill")
 *
 * @author david.lyne
 */
@Dao
interface BillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(billDataType: BillDataType)

    @Query("SELECT * FROM client")
    fun getBillList(): List<ClientDataType>

    @Query("SELECT * FROM client WHERE client.id=:id")
    fun getBillDetail(id: Int): ClientDataType

    //abstract fun insert(apply: BillDataType)

    @Update
    abstract fun update(data: BillDataType)

    fun updateWithTimestamp(data: BillDataType) {
        insertBill(data.apply{
            modifiedAt = System.currentTimeMillis()
        })
    }

    fun insertWithTimestamp(billDataType: BillDataType) {
        insertBill(billDataType.apply{
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    /*
    @Query("DELETE FROM bill")
    fun deleteAllBills()
    */
}
