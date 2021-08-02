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
 * @author david.mazo
 */
@Dao
interface BillVegetableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vegetableDataType: VegetableDataType)

    @Query("SELECT * FROM vegetable")
    fun getBillVegetableList(): List<VegetableDataType>

    @Query("SELECT * FROM vegetable WHERE vegetable.id=:id")
    fun getBillVegetableDetail(id: Int): VegetableDataType

    @Query("DELETE FROM vegetable")
    fun deleteAllBillVegetables()
}
