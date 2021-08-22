package com.davidlyne.bazurtico.data.local

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * TotalizerLeagueDatabase
 *
 * We will use this class to create other classes
 * This class is abstract because is implemented by Room
 *
 * @author david.lyne
 */

@Database(entities = [ClientDataType::class,BillDataType::class,BillVegetableDataType::class,VegetableDataType::class], version = 27, exportSchema = false)
abstract class TotalizerDatabase : RoomDatabase() {

    abstract fun getBillDAO(): BillDao
    abstract fun getClientDAO(): ClientDao
    abstract fun getVegetableDAO(): VegetableDao
    abstract fun getBillVegetableDAO(): BillVegetableDao

    //    companion object {
    //        fun getTotalizerDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
    //                TotalizerDatabase::class.java, "TotalizerDatabase")
    //                .allowMainThreadQueries()
    //                .build()
    //    }

    companion object {
        //fun getTotalizerDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,TotalizerDatabase::class.java, "TotalizerDatabase").allowMainThreadQueries().build()

        private var instance: TotalizerDatabase? = null

        val MIGRATION = object : Migration(27, 28) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //Integer values
                database.execSQL(
                    "ALTER TABLE bill ADD COLUMN year INTEGER NOT NULL DEFAULT (0)"
                )
                database.execSQL(
                    "ALTER TABLE bill ADD COLUMN month INTEGER NOT NULL DEFAULT (0)"
                )
                database.execSQL(
                        "ALTER TABLE bill ADD COLUMN day INTEGER NOT NULL DEFAULT (0)"
                        )
                database.execSQL(
                    "ALTER TABLE bill ADD COLUMN day_name STRING NOT NULL DEFAULT '' "
                )
            }
        }

        fun getInstance(context: Context): TotalizerDatabase? {
            if (instance == null) {
                synchronized(TotalizerDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,TotalizerDatabase::class.java, "bazurtico_db")
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION)
                        .addCallback(roomCallback)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: TotalizerDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val roadReference = db?.getVegetableDAO()
        override fun doInBackground(vararg p0: Unit?) {
            getDefaultVegetableList().forEach {
                roadReference?.insert(it)
            }
//            getDefaultBillList().forEach {
//                roadReference?.insert(it)
//
//            }
        }
    }





}

