package com.battagliandrea.pokedex.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.battagliandrea.pokedex.BuildConfig
import com.battagliandrea.pokedex.R
import com.battagliandrea.pokedex.di.viewmodel.InjectingSavedStateViewModelFactory
import com.battagliandrea.pokedex.ext.getViewModel
import com.battagliandrea.pokedex.ext.observe
import com.battagliandrea.pokedex.ui.base.ViewState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.transition.MaterialContainerTransform
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details_header.*
import javax.inject.Inject


class DetailsFragment : Fragment() {

    private lateinit var mViewModel: DetailsViewModel

    val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel<DetailsViewModel>(abstractFactory, savedInstanceState)
        with(mViewModel) {
            observe(header){ renderHeader(it)}
        }

        args.pokemonId.also { id ->
            restoreTransition(id)
            mViewModel.pokeId = id
        }
    }

    private fun renderHeader(state: DetailsViewState.Header){
        with(state){
            when(pokemonDataViewState){
                is ViewState.Success -> {
                    tvName.text = pokemonDataViewState.data?.name.orEmpty().capitalize()
                    tvId.text = String.format("#%03d", pokemonDataViewState.data?.id)
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Loading -> {
                    Toast.makeText(context, "LOADING", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initTransition(){
        postponeEnterTransition()

        val transformationEnter: MaterialContainerTransform = MaterialContainerTransform(requireContext()).apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 250
            scrimColor = android.R.color.transparent
        }
        sharedElementEnterTransition = transformationEnter

        val transformationReturn: MaterialContainerTransform = MaterialContainerTransform(requireContext()).apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
            duration = 250
            scrimColor = android.R.color.transparent
        }
        sharedElementReturnTransition = transformationReturn
    }

    private fun restoreTransition(id: Int){
        ivAvatar.transitionName = id.toString()

        Glide.with(this@DetailsFragment)
            .load("${BuildConfig.apiResUrl}${id}.png")
            .apply(RequestOptions()
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(ivAvatar)
    }
}
