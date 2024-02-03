package com.ig.quizgame.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlin.random.nextInt

class QuizViewModel : ViewModel() {

    val question = MutableLiveData<QuestionItem>()
    var isFirstTime = true
    var questionCounter = MutableLiveData<Int>(0)
    var isWin = false
    var counter = 0

    fun getQuestion() {
        question.value =
            QuestionCache.questions?.random()?.copy(correctAnswerPos = Random.nextInt(1..4))
    }

    fun checkAnswer(answerPos: Int): Boolean {
        return answerPos == question.value!!.correctAnswerPos
    }

    fun increaseCounter() {
        questionCounter.value = questionCounter.value?.plus(1)
        counter = questionCounter.value!!
    }

    fun resetCounter(){
        questionCounter.value = 0
    }

}