package com.app.debugmyapp.ui.recipe.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.debugmyapp.model.Category
import com.app.debugmyapp.repo.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val myRespository : MyRepository) : ViewModel() {
   // private val _items = MutableStateFlow((emptyList<Category>()))
    //val items: StateFlow<List<Category>> = _items

//    private val _items = MutableStateFlow<UiState<PagingData<Category>>>(UiState.Loading)
//    val items: StateFlow<UiState<PagingData<Category>>> = _items


    private val _pagingData: MutableStateFlow<PagingData<Category>> =
        MutableStateFlow(PagingData.empty())
    var pagingData = _pagingData.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val uiState: StateFlow<UiState<String>> = _uiState

//    val pagingData: Flow<PagingData<Category>> = Pager(PagingConfig(pageSize = 5)) {
//        MockDataSource(myRespository)
//    }.flow

    fun fetchCategoryList(){
        if(uiState.value is UiState.Success){
            return
        }

//        viewModelScope.launch(Dispatchers.IO) {
//            _items.value = UiState.Loading
//            try {
//                myRespository.fetchCategoryList()?.let {
//                    _items.value = UiState.Success(it)
//                }?: run {
//                    _items.value = UiState.Error("Something went wrong")
//                }
//
//            } catch (e: Exception) {
//                 Log.d("MainViewModel", "fetchCategoryList: ${e.message}")
//                _items.value = UiState.Error(e.message ?: "Unknown Error")
//            }
//        }
      try {
          viewModelScope.launch(Dispatchers.IO) {
              handleUISate(UiState.Loading)
              Pager(
                  config = PagingConfig(
                      10, enablePlaceholders = true
                  )
              ) {
                  MockDataSource(myRespository)
              }.flow.cachedIn(viewModelScope).collect {
                  handleUISate(UiState.Success("Success"))
                  _pagingData.value = it
              }
//            cachedIn(viewModelScope)
          }
      }catch (e : Error){
          handleUISate(UiState.Error(e.message ?: "Unknown Error"))
      }
    }

    private fun handleUISate(uiState: UiState<Any>) {
        _uiState.value = uiState as UiState<String>
    }

}

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}