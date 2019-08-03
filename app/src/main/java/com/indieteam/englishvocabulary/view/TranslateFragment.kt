package com.indieteam.englishvocabulary.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.business.component.DaggerTranslateComponent
import com.indieteam.englishvocabulary.business.module.*
import com.indieteam.englishvocabulary.business.provider.*
import com.indieteam.englishvocabulary.databinding.FragmentTranslateBindingImpl
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel

class TranslateFragment : Fragment() {

    private val translateViewModel = TranslateViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val translateComponent = DaggerTranslateComponent.builder()
            .contextModule(ContextModule(requireContext()))
            .suggestModule(SuggestModule(SuggestProvider(requireContext())))
            .retrofitModule(RetrofitModule(RetrofitProvider))
            .translateModule(TranslateModule(TranslateProvider()))
            .databaseModule(DatabaseModule(DatabaseManager(requireContext())))
            .build()

        translateComponent.poke(translateViewModel)

        val binding = DataBindingUtil.inflate<FragmentTranslateBindingImpl>(inflater, R.layout.fragment_translate, container, false)
        binding.translateViewModel = translateViewModel
        binding.executePendingBindings()
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
