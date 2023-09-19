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
        val view = inflater.inflate(R.layout.fragment_main, container, false)
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
        val plusButton = view.findViewById<ImageButton>(R.id.imageButton)
        val minusButton = view.findViewById<ImageButton>(R.id.imageButton2)

        var diff = "Easy"
        var oper = "Addition"
        var questionNum = 10
        difficultyGroup.setOnCheckedChangeListener { group, checkedId ->
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


        var numOfQuestions = view.findViewById<TextView>(R.id.textView4)
        numOfQuestions.text = questionNum.toString()

        plusButton.setOnClickListener {
            questionNum++
            numOfQuestions.text = questionNum.toString()
        }
        minusButton.setOnClickListener {
            questionNum--
            numOfQuestions.text = questionNum.toString()
        }
        startButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAct23(diff, oper)
            view.findNavController()
                .navigate(action)
        }

        return view
    }

}