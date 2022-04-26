package com.sal3awy.movieapp.module.movielist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sal3awy.movieapp.R
import com.sal3awy.movieapp.module.movielist.presentation.viewmodel.MoviesViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {


    private val viewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.moviesListLiveData().observe(this) {
            // TODO Submit to the adapter
        }
        viewModel.errorLiveData().observe(this) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadingLiveData().observe(this) {
            //TODO show progress loading
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMoviesList(1, 10)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MoviesListFragment()
    }
}