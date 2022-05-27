package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Hero::class], version = 1)
abstract class HeroDatabase: RoomDatabase(){
    abstract fun heroDao(): HeroDAO

    companion object{
        private  var INSTANSE: HeroDatabase? = null
        private  val lock = Any()

        fun getInstance(context: Context): HeroDatabase{
            synchronized(lock){
                if(INSTANSE == null){
                    //エルビス演算子
                    INSTANSE = Room.databaseBuilder(
                        context.applicationContext,
                        HeroDatabase::class.java, "Herodata.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANSE!!
            }
        }

    }
}