package com.devikiran.assignments.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devikiran.assignments.R
import com.devikiran.assignments.data.DishData
import com.devikiran.assignments.data.DishType
import com.devikiran.assignments.data.RecommendationData
import com.devikiran.assignments.utils.Utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: MainRepository) : ViewModel() {

    private val _dishDataDataList = MutableStateFlow<List<RecommendationData>>(listOf())
    val dishDataDataList: StateFlow<List<RecommendationData>> = _dishDataDataList

    private val _dishTypeDataList = MutableStateFlow<List<DishType>>(listOf())
    val dishTypeDataList: StateFlow<List<DishType>> = _dishTypeDataList

    private val _isEnableAlertDialogue= MutableStateFlow<Boolean>(false)
    val isEnableAlertDialogue: StateFlow<Boolean> = _isEnableAlertDialogue

    init {
        viewModelScope.launch {

            try {
                val dishDataList = repository.getDishes()
                updateRecommendationDataList(dishDataList)

                updateDishTypeList()
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    private fun updateRecommendationDataList(dishDataList: List<DishData>) {

        val recommendationDataList = mutableListOf<RecommendationData>()
        dishDataList.forEach { dishData ->
            val data = RecommendationData(
                dishData = dishData,
                isSelected = false,
                prepTime = "30 min",
                preparationLevel = "Medium Prep",
                ratings = "4.2"
            )
            recommendationDataList.add(data)
        }
        _dishDataDataList.update { recommendationDataList }

    }

    private fun updateDishTypeList() {
        val dishTypeList = mutableListOf<DishType>(
            DishType(
                imageResId = R.drawable.ic_rice_item,
                dishName = "Rice Items"
            ),
            DishType(
                imageResId = R.drawable.ic_curries,
                dishName = "Curries"
            ),
            DishType(
                imageResId = R.drawable.ic_soup,
                dishName = "Soups"
            ),
            DishType(
                imageResId = R.drawable.ic_desserts,
                dishName = "Desserts"
            ),
            DishType(
                imageResId = R.drawable.ic_italian,
                dishName = "Italian"
            ),
            DishType(
                imageResId = R.drawable.ic_chinease,
                dishName = "Chinese"
            ),
            DishType(
                imageResId = R.drawable.ic_snacks,
                dishName = "Snacks"
            ),
            DishType(
                imageResId = R.drawable.ic_amarican,
                dishName = "American"
            ),
            DishType(
                imageResId = R.drawable.ic_rice_item,
                dishName = "Indian"
            )
        )

        _dishTypeDataList.update { dishTypeList }
    }

    fun initAlertDialogue(){
        _isEnableAlertDialogue.update {  !_isEnableAlertDialogue.value }
    }
}