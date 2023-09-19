package com.example.project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class endScreen : Fragment() {

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
        //The last page of the app. Shows the user score
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_end_screen, container, false)
        //Getting access to variables
        val startButton = view.findViewById<Button>(R.id.button3)
        val message = endScreenArgs.fromBundle(requireArguments()).numCorrect
        val message2 = endScreenArgs.fromBundle(requireArguments()).numAnswered
        val finalText = view.findViewById<TextView>(R.id.textView10)
        //sets the final score text with the variables we sent in the previous screen
        finalText.text = message + " out of " + message2

        startButton.setOnClickListener {
            //when button is clicked, move back to start
            view.findNavController()
                .navigate(R.id.action_endScreen_to_mainFragment)
        }
        return view
    }
}