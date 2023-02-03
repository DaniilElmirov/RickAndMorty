package ru.cft.shift2023winter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.cft.shift2023winter.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RickAndMortyApplication).component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}