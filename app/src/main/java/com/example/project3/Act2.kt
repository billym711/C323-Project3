package com.example.project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class Act2 : Fragment() {

    // This establishes some basic variables to store numbers and convert them to a printable string
    // We also have booleans to determine whether or not we are currently doing an operation, and which specific operation we're doing.
    private var currentString: String = ""
    private var currentNumber: Double = 0.0
    private var storedNumber: Double = 0.0
    private var doingOp: Boolean = false
    private var additionWait: Boolean = false
    private var subtractionWait: Boolean = false
    private var divisionWait: Boolean = false
    private var multiplicationWait: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.act2, container, false)
        //val startButton = view.findViewById<Button>(R.id.button)

      //  startButton.setOnClickListener {
            //view.findNavController()
              //  .navigate(R.id.action_mainFragment_to_act23)
       // }
        return view
    }
}