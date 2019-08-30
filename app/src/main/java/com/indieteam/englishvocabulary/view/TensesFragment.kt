package com.indieteam.englishvocabulary.view

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.englishvocabulary.R
import com.indieteam.englishvocabulary.databinding.FragmentTensesBindingImpl
import com.indieteam.englishvocabulary.viewmodel.TensesRouterViewModel
import javax.inject.Inject

class TensesFragment : Fragment {

    @Inject
    constructor() {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var tensesRouterViewModel: TensesRouterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTensesBindingImpl>(inflater, R.layout.fragment_tenses, container, false)
        binding.tensesRouterViewModel = tensesRouterViewModel
        binding.executePendingBindings()
        val view = binding.root

        return view
    }
}
