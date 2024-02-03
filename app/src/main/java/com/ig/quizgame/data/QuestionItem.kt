package com.ig.quizgame.data


import com.google.gson.annotations.SerializedName

data class QuestionItem(
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("firstAnswer")
    val firstAnswer: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("secondAnswer")
    val secondAnswer: String,
    @SerializedName("thirdAnswer")
    val thirdAnswer: String,
    val correctAnswerPos: Int = 1
)