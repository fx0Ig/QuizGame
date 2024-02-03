package com.ig.quizgame.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ig.quizgame.R
import com.ig.quizgame.data.QuizViewModel
import com.ig.quizgame.databinding.FragmentQuestionBinding
import com.ig.quizgame.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResultBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = resources.getString(R.string.lose_result, viewModel.counter)

        binding.loseTv.text = text

        if (viewModel.isWin) {
            binding.loseTv.visibility = View.INVISIBLE
            binding.winTv.visibility = View.VISIBLE
        } else {
            binding.loseTv.visibility = View.VISIBLE
            binding.winTv.visibility = View.INVISIBLE
        }

        binding.menuBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.counter = 0
    }

}