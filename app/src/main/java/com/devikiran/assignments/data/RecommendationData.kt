package com.devikiran.assignments.data

data class RecommendationData (
    val dishData: DishData,
    val isSelected: Boolean,
    val prepTime: String,
    val preparationLevel: String,
    val ratings: String
)