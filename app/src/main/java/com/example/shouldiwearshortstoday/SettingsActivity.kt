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
    private lateinit var storage : Storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)
        storage = Storage(this)
        val showPopupButton = findViewById<Button>(R.id.setting_popup)

        showPopupButton.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.setting_window, null)

            val popupWindow = PopupWindow(
                popupView,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popupWindow.isFocusable = true

            popupWindow.showAtLocation(
                findViewById(R.id.setting_constraint_layout),
                Gravity.CENTER,
                0,
                0
            )

            val startTimeEditText = popupView.findViewById<EditText>(R.id.edit_text_start_time)
            val endTimeEditText = popupView.findViewById<EditText>(R.id.edit_text_end_time)


            val cancelButton = popupView.findViewById<Button>(R.id.button_cancel)

            cancelButton.setOnClickListener {

                popupWindow.dismiss()
            }

            popupView.findViewById<Button>(R.id.button_save).setOnClickListener {
                val inputText1 = startTimeEditText.text.toString().toInt()
                val inputText2 = endTimeEditText.text.toString().toInt()
                storage.setDefaultStartEnd(inputText1, inputText2)

                popupWindow.dismiss()
            }
        }

    }

    fun closeSettingsActivity(view: View) {
        finish()
    }
}


