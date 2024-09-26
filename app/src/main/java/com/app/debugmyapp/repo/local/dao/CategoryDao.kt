package com.app.debugmyapp.repo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.debugmyapp.model.Category
import com.app.debugmyapp.repo.local.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: CategoryEntity)

    @Insert
    suspend fun insert(categoryList: List<CategoryEntity>)

    @Query("SELECT * FROM category_table")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category_table LIMIT :offset,5")
    fun get(offset : Int): List<CategoryEntity>
}