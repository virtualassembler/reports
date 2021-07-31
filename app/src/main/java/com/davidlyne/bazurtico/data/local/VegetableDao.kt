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
interface VegetableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vegetable: Vegetable)

    @Query("SELECT * FROM vegetable")
    fun getVegetableList(): List<Vegetable>

    @Query("SELECT * FROM vegetable WHERE vegetable.id=:id")
    fun getVegetableDetail(id: Int): Vegetable

    @Query("DELETE FROM vegetable")
    fun deleteAllVegetables()
}