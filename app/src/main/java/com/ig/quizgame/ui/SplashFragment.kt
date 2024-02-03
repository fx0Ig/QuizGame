package com.ig.quizgame.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ig.quizgame.R
import com.ig.quizgame.data.ApiService
import com.ig.quizgame.data.Question
import com.ig.quizgame.data.QuestionCache
import com.ig.quizgame.databinding.FragmentSplashBinding
import com.ig.quizgame.databinding.FragmentStartBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var animator: ObjectAnimator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            loadQuestions()
        }
        bar()
    }

    private fun bar() {
        binding.loadingBar.max = 100

        animator = ObjectAnimator.ofInt(binding.loadingBar, "progress", binding.loadingBar.max)

        animator.duration = 3000

        animator.repeatCount = ObjectAnimator.INFINITE

        animator.doOnEnd {

            findNavController().navigate(R.id.action_splashFragment_to_startFragment)

        }
        animator.start()
    }

    private suspend fun loadQuestions() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://mocki.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(ApiService::class.java)

        val call: Call<Question?> = retrofitAPI.getCourse()

        try {
            suspendCoroutine { cont ->
                call.enqueue(object : Callback<Question?> {
                    override fun onResponse(call: Call<Question?>, response: Response<Question?>) {
                        if (response.isSuccessful) {
                            Log.d("IG", "Successful")
                            QuestionCache.questions = response.body()
                            cont.resume(response.body())
                        }
                    }

                    override fun onFailure(call: Call<Question?>, t: Throwable) {

                        Log.e("IG", "${t.message}")
                        Toast.makeText(
                            requireContext(),
                            "Failed to download data",
                            Toast.LENGTH_SHORT
                        ).show()
                        cont.resumeWithException(t)

                    }
                })
            }
            withContext(Dispatchers.Main.immediate) {
                animator.repeatCount = 0
            }
        } catch (e: Exception) {

        }

    }

}