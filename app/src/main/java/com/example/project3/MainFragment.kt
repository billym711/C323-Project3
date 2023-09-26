package com.example.project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class MainFragment : Fragment() {

//This is the starting screen.
    // It allows the user to select the number of questions, the difficulty, and the type of operation.
    // pressing start takes the user to the next screen.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var isResult = false
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        // This gets access to all the elements we need on this page
        //(Radio buttons, start button, +/- buttons, and text elements)
        isResult = MainFragmentArgs.fromBundle(requireArguments()).isResult
        if (isResult){
            val numberCorrect = MainFragmentArgs.fromBundle(requireArguments()).questionsCorrect
            val numberTotal = MainFragmentArgs.fromBundle(requireArguments()).questionsTotal
            }

        val startButton = view.findViewById<Button>(R.id.button)
        val diffButtonEasy = view.findViewById<RadioButton>(R.id.testButton)
        val diffButtonMedium = view.findViewById<RadioButton>(R.id.radioButton2)
        val diffButtonHard = view.findViewById<RadioButton>(R.id.radioButton3)
        val operButtonAdd = view.findViewById<RadioButton>(R.id.radioButton7)
        val operButtonMult = view.findViewById<RadioButton>(R.id.radioButton8)
        val operButtonDiv = view.findViewById<RadioButton>(R.id.radioButton9)
        val operButtonSub = view.findViewById<RadioButton>(R.id.radioButton10)
        var difficultyGroup = view.findViewById<RadioGroup>(R.id.difficulty)
        var operationGroup = view.findViewById<RadioGroup>(R.id.operation)
        val plusButton = view.findViewById<Button>(R.id.button5)
        val minusButton = view.findViewById<Button>(R.id.button4)

        //These variables are set here, but are changed when a button is clicked
        var diff = "Easy"
        var oper = "Addition"
        var questionNum = 10
        difficultyGroup.setOnCheckedChangeListener { group, checkedId ->
            //we set the radio button group to listen for a change, and update the difficulty variable
            if (diffButtonEasy.isChecked) {
                diff = "Easy"
            }
            if (diffButtonMedium.isChecked) {
                diff = "Medium"
            }
            if (diffButtonHard.isChecked) {
                diff = "Hard"
            }
        }
            operationGroup.setOnCheckedChangeListener { group, checkedId ->
                //we set the radio button group to listen for a change, and update the operation variable

                if (operButtonAdd.isChecked) {
                oper = "Add"
            }
            if (operButtonMult.isChecked) {
                oper = "Multiply"
            }
            if (operButtonDiv.isChecked) {
                oper = "Divide"
            }
            if (operButtonSub.isChecked) {
                oper = "Subtract"
            }
        }

        //This sets the default number of questions

        var numOfQuestions = view.findViewById<TextView>(R.id.textView4)
        numOfQuestions.text = questionNum.toString()

        plusButton.setOnClickListener {
            //When the plus or minus button is clicked, we increase or decrease the number of questions
            questionNum++
            numOfQuestions.text = questionNum.toString()
        }
        minusButton.setOnClickListener {
            //we check to make sure the question number is not less than 1
            if (questionNum > 1) {
                questionNum--
            }
            numOfQuestions.text = questionNum.toString()
        }
        startButton.setOnClickListener {
            //if the radio buttons don't have options, do not move to the next screen. Else, move on.
            if (!diff.isNullOrEmpty() && !oper.isNullOrEmpty()) {
                //this sends the necessary variables over to the next fragment
                val action = MainFragmentDirections.actionMainFragmentToAct23(
                    diff,
                    oper,
                    questionNum.toString()
                )
                view.findNavController()
                    .navigate(action)
            }
        }

        return view
    }

}