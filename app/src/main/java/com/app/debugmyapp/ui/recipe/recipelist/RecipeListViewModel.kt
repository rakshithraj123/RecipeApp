package com.app.debugmyapp.ui.recipe.recipelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.repo.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(val myRespository : MyRepository) : ViewModel() {
   // private val _items = MutableStateFlow((emptyList<Category>()))
    //val items: StateFlow<List<Category>> = _items

    private val _items = MutableStateFlow<UiState<List<Recipe>>>(UiState.Loading)
    val items: StateFlow<UiState<List<Recipe>>> = _items


    fun fetchRecipeList(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = UiState.Loading
            try {
                myRespository.fetchRecipeList(categoryName)?.let {
                    _items.value = UiState.Success(it)
                }?: run {
                    _items.value = UiState.Error("Something went wrong")
                }

            } catch (e: Exception) {
                _items.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }

    }


}

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}