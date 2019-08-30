package com.indieteam.englishvocabulary.view

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.databinding.FragmentTranslateBindingImpl
import com.indieteam.englishvocabulary.viewmodel.TranslateViewModel
import javax.inject.Inject

class TranslateFragment : Fragment {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var translateViewModel: TranslateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentTranslateBindingImpl>(
            inflater,
            R.layout.fragment_translate,
            container,
            false
        )
        binding.translateViewModel = translateViewModel
        binding.executePendingBindings()
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
