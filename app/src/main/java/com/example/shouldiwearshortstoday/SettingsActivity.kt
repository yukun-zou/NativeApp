package com.example.shouldiwearshortstoday

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        // Get a reference to the "Show Popup" button
        val showPopupButton = findViewById<Button>(R.id.setting_popup)

        // Set a click listener for the "Show Popup" button
        showPopupButton.setOnClickListener {
            // Inflate the popup layout
            val popupView = LayoutInflater.from(this).inflate(R.layout.setting_window, null)

            // Create a new popup window with the specified layout
            val popupWindow = PopupWindow(popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

            // Set a background drawable for the popup window
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // Set the focusable property of the popup window
            popupWindow.isFocusable = true

            // Show the popup window at the center of the screen
            popupWindow.showAtLocation(findViewById(R.id.setting_constraint_layout), Gravity.CENTER, 0, 0)

            // Get a reference to the input box in the popup layout
            val inputBox = popupView.findViewById<EditText>(R.id.input_box)

            // Set a click listener for the "OK" button in the popup layout
            popupView.findViewById<Button>(R.id.submit_button).setOnClickListener {
                // Get the text from the input box
                val inputText = inputBox.text.toString()

                // Show a toast message with the input text
                Toast.makeText(this, "Input Text: $inputText", Toast.LENGTH_SHORT).show()

                // Dismiss the popup window
                popupWindow.dismiss()
            }
        }
    }
    fun closeSettingsActivity(view: View) {
        finish()
    }

}

