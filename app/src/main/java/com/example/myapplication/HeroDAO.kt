package com.example.myapplication

import androidx.room.*

@Dao
interface HeroDAO {

    @Query("select * from hero")
    fun getAll(): List<Hero>

//    @Query("INSERT INTO hero (id,image,name,detail) VALUES (0,\"aka\",\"ヒーロー1\",\"説明\")")
//    fun getAka(): List<Hero>

    @Insert
    fun insert(hero: Hero)

    @Query("delete from hero")
    fun deleteAll()

    @Update
    fun update(hero: Hero)
}