package com.ig.quizgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ig.quizgame.R
import com.ig.quizgame.data.QuizViewModel
import com.ig.quizgame.databinding.FragmentQuestionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isFirstTime) {
            viewModel.getQuestion()
            viewModel.isFirstTime = false
        }

        viewModel.question.observe(viewLifecycleOwner) {
            binding.question.text = it.question
            generateAnswers()
        }

        viewModel.questionCounter.observe(viewLifecycleOwner) {
            when (it) {
                0 -> {
                    binding.question1.setImageResource(R.drawable.red_question)
                    binding.question2.setImageResource(R.drawable.red_question)
                    binding.question3.setImageResource(R.drawable.red_question)
                    binding.question4.setImageResource(R.drawable.red_question)
                    binding.question5.setImageResource(R.drawable.red_question)
                }

                1 -> {
                    binding.question1.setImageResource(R.drawable.green_question)
                }

                2 -> {
                    binding.question2.setImageResource(R.drawable.green_question)
                }

                3 -> {
                    binding.question3.setImageResource(R.drawable.green_question)
                }

                4 -> {
                    binding.question4.setImageResource(R.drawable.green_question)
                }

                5 -> {
                    binding.question5.setImageResource(R.drawable.green_question)
                }
            }
        }

        binding.answer1.setOnClickListener {

            it.setBackgroundResource(R.drawable.selected_btn)
            blockInput(false)

            lifecycleScope.launch(Dispatchers.Main) {
                makePauses()
                blockInput(true)
                if (viewModel.checkAnswer(1)) {
                    viewModel.increaseCounter()
                    if (viewModel.questionCounter.value == 5) {
                        viewModel.isWin = true
                        findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    } else {
                        viewModel.getQuestion()
                    }
                } else {
                    findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    viewModel.isWin = false
                    viewModel.resetCounter()
                }
            }
        }

        binding.answer2.setOnClickListener {
            it.setBackgroundResource(R.drawable.selected_btn)
            blockInput(false)

            lifecycleScope.launch(Dispatchers.Main) {
                makePauses()
                blockInput(true)
                if (viewModel.checkAnswer(2)) {
                    viewModel.increaseCounter()
                    if (viewModel.questionCounter.value == 5) {
                        viewModel.isWin = true
                        findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    } else {
                        viewModel.getQuestion()
                    }
                } else {
                    findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    viewModel.isWin = false
                    viewModel.resetCounter()
                }
            }
        }

        binding.answer3.setOnClickListener {
            it.setBackgroundResource(R.drawable.selected_btn)
            blockInput(false)

            lifecycleScope.launch(Dispatchers.Main) {
                makePauses()
                blockInput(true)
                if (viewModel.checkAnswer(3)) {
                    viewModel.increaseCounter()
                    if (viewModel.questionCounter.value == 5) {
                        viewModel.isWin = true
                        findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    } else {
                        viewModel.getQuestion()
                    }
                } else {
                    findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    viewModel.isWin = false
                    viewModel.resetCounter()
                }
            }
        }

        binding.answer4.setOnClickListener {

            it.setBackgroundResource(R.drawable.selected_btn)
            blockInput(false)
            lifecycleScope.launch(Dispatchers.Main) {
                makePauses()
                blockInput(true)
                if (viewModel.checkAnswer(4)) {
                    viewModel.increaseCounter()
                    if (viewModel.questionCounter.value == 5) {
                        viewModel.isWin = true
                        findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    } else {
                        viewModel.getQuestion()
                    }
                } else {
                    findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                    viewModel.isWin = false
                    viewModel.resetCounter()
                }
            }
        }

    }

    private fun generateAnswers() {
        when (viewModel.question.value!!.correctAnswerPos) {
            1 -> {
                binding.answer1.text = viewModel.question.value!!.correctAnswer
                binding.answer2.text = viewModel.question.value!!.firstAnswer
                binding.answer3.text = viewModel.question.value!!.secondAnswer
                binding.answer4.text = viewModel.question.value!!.thirdAnswer
            }

            2 -> {
                binding.answer1.text = viewModel.question.value!!.firstAnswer
                binding.answer2.text = viewModel.question.value!!.correctAnswer
                binding.answer3.text = viewModel.question.value!!.secondAnswer
                binding.answer4.text = viewModel.question.value!!.thirdAnswer
            }

            3 -> {
                binding.answer1.text = viewModel.question.value!!.firstAnswer
                binding.answer2.text = viewModel.question.value!!.secondAnswer
                binding.answer3.text = viewModel.question.value!!.correctAnswer
                binding.answer4.text = viewModel.question.value!!.thirdAnswer
            }

            4 -> {
                binding.answer1.text = viewModel.question.value!!.firstAnswer
                binding.answer2.text = viewModel.question.value!!.secondAnswer
                binding.answer3.text = viewModel.question.value!!.thirdAnswer
                binding.answer4.text = viewModel.question.value!!.correctAnswer
            }
        }

    }

    private fun showCorrect() {
        when (viewModel.question.value!!.correctAnswerPos) {
            1 -> {
                binding.answer1.setBackgroundResource(R.drawable.button_correct)
                binding.answer2.setBackgroundResource(R.drawable.button_wrong)
                binding.answer3.setBackgroundResource(R.drawable.button_wrong)
                binding.answer4.setBackgroundResource(R.drawable.button_wrong)
            }

            2 -> {
                binding.answer1.setBackgroundResource(R.drawable.button_wrong)
                binding.answer2.setBackgroundResource(R.drawable.button_correct)
                binding.answer3.setBackgroundResource(R.drawable.button_wrong)
                binding.answer4.setBackgroundResource(R.drawable.button_wrong)
            }

            3 -> {
                binding.answer1.setBackgroundResource(R.drawable.button_wrong)
                binding.answer2.setBackgroundResource(R.drawable.button_wrong)
                binding.answer3.setBackgroundResource(R.drawable.button_correct)
                binding.answer4.setBackgroundResource(R.drawable.button_wrong)
            }

            4 -> {
                binding.answer1.setBackgroundResource(R.drawable.button_wrong)
                binding.answer2.setBackgroundResource(R.drawable.button_wrong)
                binding.answer3.setBackgroundResource(R.drawable.button_wrong)
                binding.answer4.setBackgroundResource(R.drawable.button_correct)
            }
        }
    }

    private fun resetColor() {
        binding.answer1.setBackgroundResource(R.drawable.button_style)
        binding.answer2.setBackgroundResource(R.drawable.button_style)
        binding.answer3.setBackgroundResource(R.drawable.button_style)
        binding.answer4.setBackgroundResource(R.drawable.button_style)
    }

    private suspend fun makePauses() {
        delay(3000L)
        showCorrect()
        delay(1000L)
        resetColor()
    }

    private fun blockInput(isEnabled: Boolean) {
        binding.answer1.isEnabled = isEnabled
        binding.answer2.isEnabled = isEnabled
        binding.answer3.isEnabled = isEnabled
        binding.answer4.isEnabled = isEnabled
    }

}
