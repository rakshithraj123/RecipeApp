package com.app.debugmyapp.repo.local

import com.app.debugmyapp.model.Category
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail

interface LocalRepository {
     suspend fun fetchCategoryList(offset : Int) : List<Category>
     suspend fun saveCategoryList(categoryList :  List<Category>)

     fun fetchRecipeList(categoryName: String): List<Recipe>
     fun saveRecipeList(categoryName: String, recipeList : List<Recipe>)

     fun fetchRecipeDetail(recipeId: String): RecipeDetail
     fun saveRecipeDetail(recipeDetail: RecipeDetail)
}