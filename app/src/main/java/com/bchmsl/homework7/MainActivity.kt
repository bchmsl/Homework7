package com.bchmsl.homework7

import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.bchmsl.homework7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        listeners()
    }



    private fun listeners() {
        binding.btnAddField.setOnClickListener {
            makeDialog()
        }
    }

    private fun makeDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Title")

        val dialogLinearLayout = LinearLayout(this)
        val dialogLinearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        dialogLinearLayout.orientation = LinearLayout.VERTICAL
        dialogLinearLayout.layoutParams = dialogLinearLayoutParams
        builder.setView(dialogLinearLayout)

        val input = EditText(this)
        input.hint = "Enter Text"
        input.inputType = InputType.TYPE_CLASS_TEXT
        dialogLinearLayout.addView(input)

        val checkBox = CheckBox(this)
        checkBox.text = "Field is numeric"
        dialogLinearLayout.addView(checkBox)

        builder.setPositiveButton("OK") { _, _ ->
            makeEditText(input.text.toString(), checkBox.isChecked)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }


    private fun makeEditText(hint: String, isNumeric: Boolean) {
        val editText = EditText(this@MainActivity)
        editText.hint = hint
        when (isNumeric) {
            true -> editText.inputType = InputType.TYPE_CLASS_NUMBER
            false -> editText.inputType = InputType.TYPE_CLASS_TEXT
        }
        val etLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        
        editText.layoutParams = etLayoutParams

        editText.setPadding(30)
        editText.setBackgroundResource(R.drawable.rectangle)
        binding.llEdits.addView(editText)
    }

}