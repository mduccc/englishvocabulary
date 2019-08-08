package com.indieteam.englishvocabulary.view

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.databinding.ActivityTensesBinding
import com.indieteam.englishvocabulary.model.TensesModel
import com.indieteam.englishvocabulary.viewmodel.Label
import com.indieteam.englishvocabulary.viewmodel.TensesViewModel
import javax.inject.Inject

class TensesActivity : AppCompatActivity {

    constructor() {
        if (!App.isAppComponentInitialized())
            App.appComponent = App.module.build()
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var tensesViewModel: TensesViewModel
    private var activityTitle: String? = ""

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
                        tensesViewModel.setDescription(TensesModel.Companion.SimplePresent.title)
                        tensesViewModel.setLook(TensesModel.Companion.SimplePresent.look)
                        tensesViewModel.setManual(TensesModel.Companion.SimplePresent.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.SimplePresent.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.SimplePresent.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.SimplePresent.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.SimplePresent.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.SimplePresent.question)
                        tensesViewModel.setExample3(TensesModel.Companion.SimplePresent.example3)
                    }
                    Label.presentContinuous -> {
                        tensesViewModel.setDescription(TensesModel.Companion.PresentContinuous.title)
                        tensesViewModel.setLook(TensesModel.Companion.PresentContinuous.look)
                        tensesViewModel.setManual(TensesModel.Companion.PresentContinuous.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.PresentContinuous.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.PresentContinuous.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.PresentContinuous.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.PresentContinuous.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.PresentContinuous.question)
                        tensesViewModel.setExample3(TensesModel.Companion.PresentContinuous.example3)
                    }

                    Label.presentPerfect -> {
                        tensesViewModel.setDescription(TensesModel.Companion.PresentPerfect.title)
                        tensesViewModel.setLook(TensesModel.Companion.PresentPerfect.look)
                        tensesViewModel.setManual(TensesModel.Companion.PresentPerfect.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.PresentPerfect.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.PresentPerfect.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.PresentPerfect.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.PresentPerfect.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.PresentPerfect.question)
                        tensesViewModel.setExample3(TensesModel.Companion.PresentPerfect.example3)
                    }

                    Label.pastSimple -> {
                        tensesViewModel.setDescription(TensesModel.Companion.PastSimple.title)
                        tensesViewModel.setLook(TensesModel.Companion.PastSimple.look)
                        tensesViewModel.setManual(TensesModel.Companion.PastSimple.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.PastSimple.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.PastSimple.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.PastSimple.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.PastSimple.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.PastSimple.question)
                        tensesViewModel.setExample3(TensesModel.Companion.PastSimple.example3)
                    }

                    Label.pastContinuous -> {
                        tensesViewModel.setDescription(TensesModel.Companion.PastContinuous.title)
                        tensesViewModel.setLook(TensesModel.Companion.PastContinuous.look)
                        tensesViewModel.setManual(TensesModel.Companion.PastContinuous.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.PastContinuous.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.PastContinuous.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.PastContinuous.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.PastContinuous.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.PastContinuous.question)
                        tensesViewModel.setExample3(TensesModel.Companion.PastContinuous.example3)
                    }

                    Label.pastPerfect -> {
                        tensesViewModel.setDescription(TensesModel.Companion.PastPerfect.title)
                        tensesViewModel.setLook(TensesModel.Companion.PastPerfect.look)
                        tensesViewModel.setManual(TensesModel.Companion.PastPerfect.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.PastPerfect.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.PastPerfect.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.PastPerfect.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.PastPerfect.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.PastPerfect.question)
                        tensesViewModel.setExample3(TensesModel.Companion.PastPerfect.example3)
                    }

                    Label.simpleFuture -> {
                        tensesViewModel.setDescription(TensesModel.Companion.SimpleFuture.title)
                        tensesViewModel.setLook(TensesModel.Companion.SimpleFuture.look)
                        tensesViewModel.setManual(TensesModel.Companion.SimpleFuture.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.SimpleFuture.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.SimpleFuture.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.SimpleFuture.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.SimpleFuture.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.SimpleFuture.question)
                        tensesViewModel.setExample3(TensesModel.Companion.SimpleFuture.example3)
                    }

                    Label.futureContinuous -> {
                        tensesViewModel.setDescription(TensesModel.Companion.FutureContinuous.title)
                        tensesViewModel.setLook(TensesModel.Companion.FutureContinuous.look)
                        tensesViewModel.setManual(TensesModel.Companion.FutureContinuous.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.FutureContinuous.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.FutureContinuous.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.FutureContinuous.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.FutureContinuous.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.FutureContinuous.question)
                        tensesViewModel.setExample3(TensesModel.Companion.FutureContinuous.example3)
                    }
                    Label.futurePerfect -> {
                        tensesViewModel.setDescription(TensesModel.Companion.FuturePerfect.title)
                        tensesViewModel.setLook(TensesModel.Companion.FuturePerfect.look)
                        tensesViewModel.setManual(TensesModel.Companion.FuturePerfect.manual)
                        tensesViewModel.setAffirmation(TensesModel.Companion.FuturePerfect.affirmation)
                        tensesViewModel.setExample1(TensesModel.Companion.FuturePerfect.example1)
                        tensesViewModel.setNegative(TensesModel.Companion.FuturePerfect.negative)
                        tensesViewModel.setExample2(TensesModel.Companion.FuturePerfect.example2)
                        tensesViewModel.setQuestion(TensesModel.Companion.FuturePerfect.question)
                        tensesViewModel.setExample3(TensesModel.Companion.FuturePerfect.example3)
                    }
                    else -> {
                    }
                }
            }
        }


    }
}
