package com.sal3awy.movieapp.module.movielist.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sal3awy.movieapp.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.moviesFrame, MoviesListFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}