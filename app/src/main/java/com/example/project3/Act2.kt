package com.example.project3

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.w3c.dom.Text
import kotlin.math.truncate

class Act2 : Fragment() {
    //The second screen of the app, where users can enter their answers

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.act2, container, false)
        // Getting access to buttons and text
        val startButton = view.findViewById<Button>(R.id.button2)
        val message = Act2Args.fromBundle(requireArguments()).difficulty
        val message2 = Act2Args.fromBundle(requireArguments()).operation
        val message3 = Act2Args.fromBundle(requireArguments()).questions
        val operationText = view.findViewById<TextView>(R.id.textView7)
        val num1Text = view.findViewById<TextView>(R.id.textView6)
        val num2Text = view.findViewById<TextView>(R.id.textView8)
        val answerText = view.findViewById<EditText>(R.id.editTextText)
        //setting some basic variables for calculations
        var num1 = 0
        var num2 = 0
        var answer = 0.0
        var numCorrect = 0
        var numAnswered = 0
        //using the sent variables from the previous fragment
        //to determine what the random numbers can be
        if (message == "Easy"){
            num1 = (0..9).random()
            num2 = (0..9).random()
            num1Text.text = num1.toString()
            num2Text.text = num2.toString()
        }
        if (message == "Medium"){
            num1 = (0..24).random()
            num2 = (0..24).random()
            num1Text.text = num1.toString()
            num2Text.text = num2.toString()
        }
        if (message == "Hard"){
            num1 = (0..49).random()
            num2 = (0..49).random()
            num1Text.text = num1.toString()
            num2Text.text = num2.toString()
        }
        //and setting the proper operation text
        if (message2 == "Add"){
            operationText.text = "+"

        }
        if (message2 == "Multiply"){
            operationText.text = "X"

        }
        if (message2 == "Divide"){
            operationText.text = "/"

        }
        if (message2 == "Subtract"){
            operationText.text = "-"

        }

        startButton.setOnClickListener {
            //when they click DONE, only progress if they entered something
            if (!answerText.text.isNullOrEmpty()) {
                //Creating toasts for correct and incorrect answers
                val correctToast = Toast.makeText(context, "Correct. Good work!", Toast.LENGTH_SHORT)
                val incorrectToast = Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT)
                //creating media players for correct and incorrect answers
                val mediaPlayerCorrect = MediaPlayer.create(context, R.raw.correct_sound)
                val mediaPlayerIncorrect = MediaPlayer.create(context, R.raw.incorrect_sound)
                answer = answerText.text.toString().toDouble()
                //Calculate the correct answer and compare
                //Also show the proper toast and play the corresponding media
                if (message2 == "Add" && (answer == (num1 + num2).toDouble())) {
                    numCorrect++
                    correctToast.show()
                    mediaPlayerCorrect.start()
                }
                if (message2 == "Add" && (answer != (num1 + num2).toDouble())) {
                    incorrectToast.show()
                    mediaPlayerIncorrect.start()
                }
                if (message2 == "Multiply" && (answer == (num1 * num2).toDouble())) {
                    numCorrect++
                    correctToast.show()
                    mediaPlayerCorrect.start()

                }
                if (message2 == "Multiply" && (answer != (num1 * num2).toDouble())) {
                    incorrectToast.show()
                    mediaPlayerIncorrect.start()

                }
                //currently, division ignores remainders and just truncates.
                if (message2 == "Divide") {

                    if (answer == (num1 / num2).toDouble()) {
                        numCorrect++
                        correctToast.show()
                        mediaPlayerCorrect.start()

                    }else{
                        incorrectToast.show()
                        mediaPlayerIncorrect.start()
                    }
                }
                //This is another section of code that could be enabled to use decimals in the division portion of the app
                /*if (message2 == "Divide" && (num1 / num2).toString().length < 20) {
                    if ((answer == (num1.toDouble() / num2.toDouble()))) {
                        numCorrect++
                    }
                }
                //This adds a check for repeating numbers so that the user only needs to put 3 decimal places
                if (message2 == "Divide" && (num1 / num2).toString().length >= 20) {
                    if ((answer == truncate((num1 / num2).toDouble() * 1000) / 1000)) {
                        numCorrect++
                    }
                }
                 */
                if (message2 == "Subtract" && (answer == (num1 - num2).toDouble())) {
                    numCorrect++
                    correctToast.show()
                    mediaPlayerCorrect.start()

                }
                if (message2 == "Subtract" && (answer != (num1 - num2).toDouble())) {
                    incorrectToast.show()
                    mediaPlayerIncorrect.start()

                }
                numAnswered++
                if (numAnswered == message3.toInt()) {
                    //if we reach our question maximum, go to the next screen and send the variables
                    val action = Act2Directions.actionAct23ToMainFragment()
                    //because we have default values for our variables that we send, we have to define them here, instead of in parentheses.
                    action.questionsCorrect = numCorrect.toString()
                    action.questionsTotal = numAnswered.toString()
                    if (message2 == "Add"){
                        action.prevOperation = "addition"
                    }
                    if (message2 == "Subtract"){
                        action.prevOperation = "subtraction"
                    }
                    if (message2 == "Multiply"){
                        action.prevOperation = "multiplication"
                    }
                    if (message2 == "Divide"){
                        action.prevOperation = "division"
                    }
                    view.findNavController()
                        .navigate(action)
                } else {
                    //otherwise, generate next question
                    answerText.setText("")
                    if (message == "Easy") {
                        num1 = (0..9).random()
                        num2 = (0..9).random()
                        num1Text.text = num1.toString()
                        num2Text.text = num2.toString()
                    }
                    if (message == "Medium") {
                        num1 = (0..24).random()
                        num2 = (0..24).random()
                        num1Text.text = num1.toString()
                        num2Text.text = num2.toString()
                    }
                    if (message == "Hard") {
                        num1 = (0..49).random()
                        num2 = (0..49).random()
                        num1Text.text = num1.toString()
                        num2Text.text = num2.toString()
                    }
                    if (message2 == "Divide" && num2 == 0){
                            num2 = 1
                            num2Text.text = num2.toString()
                    }
                }
            }
        }

        return view
    }
}