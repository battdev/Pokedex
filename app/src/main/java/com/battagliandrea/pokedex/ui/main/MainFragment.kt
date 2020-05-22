package com.battagliandrea.pokedex.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.battagliandrea.pokedex.R
import com.battagliandrea.pokedex.di.viewmodel.InjectingSavedStateViewModelFactory
import com.battagliandrea.pokedex.ext.getViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MainFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel<MainViewModel>(abstractFactory, savedInstanceState)
    }
}
