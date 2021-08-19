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

    @Query("SELECT * FROM bill")
    fun getBillList(): List<BillDataType>

    @Query("SELECT * FROM bill WHERE state = 2")
    fun getUnsavedBillList(): List<BillDataType>

    @Query("SELECT * FROM bill WHERE state = 1")
    fun getSavedBillList(): List<BillDataType>

    @Query("SELECT *,`id` FROM bill")
    fun getBillListWithId(): List<BillDataType>

    @Query("UPDATE bill SET state = 1 WHERE id = :billId")
    fun updateStatus(billId:Int)

    @Query("SELECT * FROM bill WHERE bill.id=:clientId")
    fun getBill(clientId: Int): BillDataType

    //@Query("SELECT client.nameClient FROM client WHERE id=1")
    @Query("SELECT client.nameClient FROM client JOIN bill ON bill.clientId = client.id WHERE bill.id=:billId")
    fun getClientNameByBillId(billId: String): String

    /*
    @Query("SELECT client.nameClient FROM client JOIN bill ON client.id = bill.clientId  WHERE bill.id=:billId")
    fun getClientNameByBillId(billId: Int): String
    */

    @Query("SELECT * FROM bill WHERE state = 2")
    fun getUnsavedBill(): BillDataType

    @Query("SELECT *,id FROM bill WHERE state=2")
    fun getCurrentBill(): BillDataType

    @Query("SELECT * FROM bill LIMIT 1")
    fun getLastBillId(): BillDataType

    @Query("SELECT * FROM bill WHERE bill.id=:id")
    fun getBillDetail(id: Int): BillDataType

    @Query("DELETE FROM bill WHERE id = :id")
    fun clearUnsavedBillList(id : Int)

    /*
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
    */

    /*
    @Query("DELETE FROM bill")
    fun deleteAllBills()
    */
}
