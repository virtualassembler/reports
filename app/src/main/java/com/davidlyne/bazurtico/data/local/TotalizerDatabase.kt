package com.davidlyne.bazurtico.data.local

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * TotalizerLeagueDatabase
 *
 * We will use this class to create other classes
 * This class is abstract because is implemented by Room
 *
 * @author david.mazo
 */

@Database(entities = [ClientDataClass::class, Vegetable::class], version = 5, exportSchema = false)
abstract class TotalizerDatabase : RoomDatabase() {

    abstract fun getClientDAO(): ClientDao
    abstract fun getVegetableDAO(): VegetableDao

//    companion object {
//        fun getTotalizerDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
//                TotalizerDatabase::class.java, "TotalizerDatabase")
//                .allowMainThreadQueries()
//                .build()
//    }
companion object {
    private var instance: TotalizerDatabase? = null

    fun getInstance(context: Context): TotalizerDatabase? {
        if (instance == null) {
            synchronized(TotalizerDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TotalizerDatabase::class.java, "route_database"
                )
                    .fallbackToDestructiveMigration()
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
        }
    }

}