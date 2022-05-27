package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Hero (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val image: String,
    val name: String,
    val detail: String,
)