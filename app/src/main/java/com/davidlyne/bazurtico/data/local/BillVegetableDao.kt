package com.davidlyne.bazurtico.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * VegetableDao
 *
 * to Access Database @Entity(tableName = "vegetable_table")
 * facilitates access to stored data with this methods
 *
 * @author david.lyne
 */

@Dao
interface BillVegetableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(billVegetableDataType: BillVegetableDataType)

    @Query("SELECT * FROM bill_vegetable")
    fun getBillVegetableList(): List<BillVegetableDataType>

    @Query("SELECT * FROM bill_vegetable " +
            "JOIN vegetable ON bill_vegetable.vegetableId = vegetable.id " +
            "JOIN bill ON bill_vegetable.billId = bill.id " +
            "WHERE bill_vegetable.id = 1")
    fun getSelectedVegetableList(): List<SelectedVegetableDataType>

    @Query("SELECT * FROM bill_vegetable " +
            "JOIN vegetable ON bill_vegetable.vegetableId = vegetable.id " +
            "JOIN bill ON bill_vegetable.billId = bill.id " +
            "WHERE bill_vegetable.id = 1" +
            " AND bill.year = :year" +
            " AND bill.month = :month" +
            " AND bill.day = :day ")
    fun getTodaySelectedVegetableList(year:Int,month:Int,day:Int): List<SelectedVegetableDataType>

//    @Query("SELECT * FROM bill_vegetable JOIN vegetable ON bill_vegetable.vegetableId = vegetable.id WHERE bill_vegetable.id = 1")
//    fun getSelectedVegetableList(): List<SelectedVegetableDataType>

    @Query("SELECT * FROM bill_vegetable WHERE id=:id")
    fun getBillVegetableDetail(id: Int): BillVegetableDataType

    @Query("DELETE FROM bill_vegetable")
    fun deleteAllBillVegetables()

    @Query("DELETE FROM bill_vegetable WHERE billId =:id")
    fun clearUnsavedBillVegetableList(id: Int)

    @Query("DELETE FROM bill_vegetable WHERE billId =:id")
    fun clearBillVegetableListById(id: Int)
}
