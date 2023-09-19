package com.example.project3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
/**
 * This program implements an Android application for an arithmetic test game.
 *
 * @author Billy Moore
 * @version 1.0
 * @since 2023-19-09
 */
class MainActivity : AppCompatActivity() {
    //Since this is an app with fragments, we don't need to do much in the MainActivity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
