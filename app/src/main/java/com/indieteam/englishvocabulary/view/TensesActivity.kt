package com.indieteam.englishvocabulary.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.databinding.ActivityTensesBinding
import com.indieteam.englishvocabulary.viewmodel.Label
import com.indieteam.englishvocabulary.viewmodel.TensesViewModel

class TensesActivity : AppCompatActivity() {

    private var activityTitle: String? = ""
    private var tensesViewModel = TensesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityTensesBinding>(this, R.layout.activity_tenses)
        binding.tensesViewModel = tensesViewModel
        binding.executePendingBindings()

        activityTitle = intent.getStringExtra("title")
        activityTitle?.let{
            if (it.isNotBlank()) {
                tensesViewModel.setTitle(it)
                Log.d("title", it)

                when (it) {
                    Label.simplePresent -> {
                    }
                    Label.presentContinuous -> {
                    }

                    Label.presentPerfect -> {
                    }

                    Label.pastSimple -> {
                    }

                    Label.pastContinuous -> {
                    }

                    Label.pastPerfect -> {
                    }

                    Label.simpleFuture -> {
                    }

                    Label.futureContinuous -> {
                    }
                    Label.futurePerfect -> {
                    }
                    else -> {
                    }
                }
            }
        }


    }
}
