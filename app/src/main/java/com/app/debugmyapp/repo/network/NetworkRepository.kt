package com.app.debugmyapp.repo.network

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail

interface NetworkRepository {
    suspend fun fetchCategoryList() : List<Category>

    suspend fun fetchRecipeList(categoryName: String): List<Recipe>

    abstract suspend fun fetchRecipeDetail(recipeId: String): RecipeDetail
}