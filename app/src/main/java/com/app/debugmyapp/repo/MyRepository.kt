package com.app.debugmyapp.repo

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail

interface MyRepository {
    suspend fun fetchCategoryList(offset : Int) : List<Category>

    suspend fun fetchRecipeList(categoryName: String): List<Recipe>

    suspend fun fetchRecipeDetail(recipeId: String): RecipeDetail

    suspend fun saveCategoryList(categoryList :  List<Category>)
    fun saveRecipeList(categoryName: String, recipeList : List<Recipe>)
    fun saveRecipeDetail(recipeDetail: RecipeDetail)


}