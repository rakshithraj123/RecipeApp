package com.app.debugmyapp.repo.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
//                     @PrimaryKey(autoGenerate = true)
//                     val id : Int,
                     @PrimaryKey
                     val idCategory : String,
                     val strCategory : String,
                     val strCategoryThumb : String,
                     val strCategoryDescription : String) {
}