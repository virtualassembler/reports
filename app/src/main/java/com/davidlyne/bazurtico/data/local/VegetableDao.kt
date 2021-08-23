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
interface VegetableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vegetableDataType: VegetableDataType)

    @Query("SELECT * FROM vegetable")
    fun getVegetableList(): List<VegetableDataType>

    @Query("SELECT vegetable.name,vegetable.id,vegetable.price,vegetable.isUnit FROM vegetable JOIN bill_vegetable JOIN bill WHERE bill.state = 2 AND vegetable.id=1")
    //@Query("SELECT * FROM vegetable WHERE vegetable.id = 1")
    fun getSelectedVegetableList(): List<VegetableDataType>

    @Query("SELECT * FROM vegetable WHERE vegetable.id=:id")
    fun getVegetableById(id: String): VegetableDataType

    @Query("DELETE FROM vegetable")
    fun deleteAllVegetables()

//    @Query("ALTER TABLE vegetable ADD COLUMN isUnit INTEGER NOT NULL DEFAULT (0)")
//    fun alterVegetableTable()
}
