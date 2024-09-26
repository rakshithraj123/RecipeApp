package com.app.debugmyapp.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.debugmyapp.repo.local.dao.CategoryDao
import com.app.debugmyapp.repo.local.entity.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1)
abstract class LocalDataBase : RoomDatabase(){
    abstract fun categoryDao(): CategoryDao
}