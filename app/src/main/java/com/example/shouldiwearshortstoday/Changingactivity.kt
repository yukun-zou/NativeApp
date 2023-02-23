package com.example.shouldiwearshortstoday

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.shouldiwearshortstoday.Storage

class Changingactivity : AppCompatActivity() {

    private lateinit var storage : Storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changingclothes)
        storage = Storage(this)
    }


    fun closeChangingactivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    @SuppressLint("MissingInflatedId")
    fun changehat(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changehat, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val hatImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val hatImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        hatImage1.setOnClickListener {
            val hat1=findViewById<ImageView>(R.id.hat3)
            val hat2=findViewById<ImageView>(R.id.hat)
            val hat3=findViewById<ImageView>(R.id.hat1)
            val hat4=findViewById<ImageView>(R.id.hat2)
            hat1.visibility = View.INVISIBLE
            hat2.visibility = View.VISIBLE
            hat4.visibility = View.GONE
            hat3.visibility = View.VISIBLE
            popupWindow.dismiss()
            storage.changeHat(R.drawable.hat)


        }
        hatImage2.setOnClickListener {
            val hat1=findViewById<ImageView>(R.id.hat)
            val hat2=findViewById<ImageView>(R.id.hat3)
            val hat3=findViewById<ImageView>(R.id.hat1)
            val hat4=findViewById<ImageView>(R.id.hat2)
            hat1.visibility = View.INVISIBLE
            hat2.visibility = View.VISIBLE
            hat3.visibility = View.INVISIBLE
            hat4.visibility = View.VISIBLE
            popupWindow.dismiss()
            storage.changeHat(R.drawable.hat2)
        }
    }

    @SuppressLint("MissingInflatedId")
    fun changescarf(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changescarf, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val scarfImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val scarfImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        scarfImage1.setOnClickListener {
            val scarf1=findViewById<ImageView>(R.id.scarf3)
            val scarf2=findViewById<ImageView>(R.id.scarf)
            val scarf3=findViewById<ImageView>(R.id.scarf1)
            val scarf4=findViewById<ImageView>(R.id.scarf2)
            scarf1.visibility = View.GONE
            scarf2.visibility = View.VISIBLE
            scarf4.visibility = View.GONE
            scarf3.visibility = View.VISIBLE
            popupWindow.dismiss()
            storage.changeScarf(R.drawable.scarf)
        }
        scarfImage2.setOnClickListener {
            val scarf1=findViewById<ImageView>(R.id.scarf)
            val scarf2=findViewById<ImageView>(R.id.scarf3)
            val scarf3=findViewById<ImageView>(R.id.scarf1)
            val scarf4=findViewById<ImageView>(R.id.scarf2)
            scarf1.visibility = View.GONE
            scarf2.visibility = View.VISIBLE
            scarf3.visibility = View.INVISIBLE
            scarf4.visibility = View.VISIBLE
            popupWindow.dismiss()
            storage.changeScarf(R.drawable.scarf2)
        }
    }

    @SuppressLint("MissingInflatedId")
    fun changeumbrella(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changeumbrella, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val umbrellaImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val umbrellaImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        umbrellaImage1.setOnClickListener {
            val umbrella1=findViewById<ImageView>(R.id.umbrella3)
            val umbrella2=findViewById<ImageView>(R.id.umbrella)
            val umbrella3=findViewById<ImageView>(R.id.umbrella1)
            val umbrella4=findViewById<ImageView>(R.id.umbrella2)
            umbrella1.visibility = View.GONE
            umbrella2.visibility = View.VISIBLE
            umbrella4.visibility = View.GONE
            umbrella3.visibility = View.VISIBLE
            popupWindow.dismiss()
            storage.changeUmbrella(R.drawable.umbrella)
        }
        umbrellaImage2.setOnClickListener {
            val umbrella1=findViewById<ImageView>(R.id.umbrella)
            val umbrella2=findViewById<ImageView>(R.id.umbrella3)
            val umbrella3=findViewById<ImageView>(R.id.umbrella1)
            val umbrella4=findViewById<ImageView>(R.id.umbrella2)
            umbrella1.visibility = View.GONE
            umbrella2.visibility = View.VISIBLE
            umbrella3.visibility = View.INVISIBLE
            umbrella4.visibility = View.VISIBLE
            popupWindow.dismiss()
            storage.changeUmbrella(R.drawable.umbrella2)
        }
    }

    @SuppressLint("MissingInflatedId")
    fun changeshorts(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changeshorts, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val shortsImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val shortsImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        shortsImage1.setOnClickListener {
            val shorts1=findViewById<ImageView>(R.id.shorts3)
            val shorts2=findViewById<ImageView>(R.id.shorts)
            val shorts3=findViewById<ImageView>(R.id.shorts1)
            val shorts4=findViewById<ImageView>(R.id.shorts2)
            val trousers1=findViewById<ImageView>(R.id.trousers)
            val trousers2=findViewById<ImageView>(R.id.trousers3)
            shorts1.visibility = View.GONE
            shorts2.visibility = View.VISIBLE
            shorts4.visibility = View.GONE
            shorts3.visibility = View.VISIBLE
            trousers1.visibility = View.GONE
            trousers2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeShorts(R.drawable.shorts)
        }
        shortsImage2.setOnClickListener {
            val shorts1=findViewById<ImageView>(R.id.shorts)
            val shorts2=findViewById<ImageView>(R.id.shorts3)
            val shorts3=findViewById<ImageView>(R.id.shorts1)
            val shorts4=findViewById<ImageView>(R.id.shorts2)
            val trousers1=findViewById<ImageView>(R.id.trousers)
            val trousers2=findViewById<ImageView>(R.id.trousers3)
            shorts1.visibility = View.GONE
            shorts2.visibility = View.VISIBLE
            shorts3.visibility = View.INVISIBLE
            shorts4.visibility = View.VISIBLE
            trousers1.visibility = View.GONE
            trousers2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeShorts(R.drawable.shorts2)
        }
    }
    @SuppressLint("MissingInflatedId")
    fun changetrousers(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changetrousers, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val trousersImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val trousersImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        trousersImage1.setOnClickListener {
            val trousers1=findViewById<ImageView>(R.id.trousers3)
            val trousers2=findViewById<ImageView>(R.id.trousers)
            val trousers3=findViewById<ImageView>(R.id.trousers1)
            val trousers4=findViewById<ImageView>(R.id.trousers2)
            val shorts1=findViewById<ImageView>(R.id.shorts)
            val shorts2=findViewById<ImageView>(R.id.shorts3)
            trousers1.visibility = View.GONE
            trousers2.visibility = View.VISIBLE
            trousers4.visibility = View.GONE
            trousers3.visibility = View.VISIBLE
            shorts1.visibility = View.GONE
            shorts2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeTrousers(R.drawable.trousers)
        }
        trousersImage2.setOnClickListener {
            val trousers1=findViewById<ImageView>(R.id.trousers)
            val trousers2=findViewById<ImageView>(R.id.trousers3)
            val trousers3=findViewById<ImageView>(R.id.trousers1)
            val trousers4=findViewById<ImageView>(R.id.trousers2)
            val shorts1=findViewById<ImageView>(R.id.shorts)
            val shorts2=findViewById<ImageView>(R.id.shorts3)
            trousers1.visibility = View.GONE
            trousers2.visibility = View.VISIBLE
            trousers3.visibility = View.INVISIBLE
            trousers4.visibility = View.VISIBLE
            shorts1.visibility = View.GONE
            shorts2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeTrousers(R.drawable.trousers2)
        }
    }

    @SuppressLint("MissingInflatedId")
    fun changehoodie(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changehoodie, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val hoodieImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val hoodieImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        hoodieImage1.setOnClickListener {
            val hoodie1=findViewById<ImageView>(R.id.hoodie3)
            val hoodie2=findViewById<ImageView>(R.id.hoodie)
            val hoodie3=findViewById<ImageView>(R.id.hoodie1)
            val hoodie4=findViewById<ImageView>(R.id.hoodie2)
            val tshirt1=findViewById<ImageView>(R.id.tshirt)
            val tshirt2=findViewById<ImageView>(R.id.tshirt3)
            val winterjacket1=findViewById<ImageView>(R.id.winterjacket)
            val winterjacket2=findViewById<ImageView>(R.id.winterjacket3)
            hoodie1.visibility = View.GONE
            hoodie2.visibility = View.VISIBLE
            hoodie4.visibility = View.GONE
            hoodie3.visibility = View.VISIBLE
            tshirt1.visibility = View.GONE
            tshirt2.visibility = View.GONE
            winterjacket1.visibility = View.GONE
            winterjacket2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeHoodie(R.drawable.hoodie)
        }
        hoodieImage2.setOnClickListener {
            val hoodie1=findViewById<ImageView>(R.id.hoodie)
            val hoodie2=findViewById<ImageView>(R.id.hoodie3)
            val hoodie3=findViewById<ImageView>(R.id.hoodie1)
            val hoodie4=findViewById<ImageView>(R.id.hoodie2)
            val tshirt1=findViewById<ImageView>(R.id.tshirt)
            val tshirt2=findViewById<ImageView>(R.id.tshirt3)
            val winterjacket1=findViewById<ImageView>(R.id.winterjacket)
            val winterjacket2=findViewById<ImageView>(R.id.winterjacket3)
            hoodie1.visibility = View.GONE
            hoodie2.visibility = View.VISIBLE
            hoodie3.visibility = View.INVISIBLE
            hoodie4.visibility = View.VISIBLE
            tshirt1.visibility = View.GONE
            tshirt2.visibility = View.GONE
            winterjacket1.visibility = View.GONE
            winterjacket2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeHoodie(R.drawable.hoodie2)
        }
    }

    @SuppressLint("MissingInflatedId")
    fun changetshirt(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changetshirt, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val tshirtImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val tshirtImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        tshirtImage1.setOnClickListener {
            val tshirt1=findViewById<ImageView>(R.id.tshirt3)
            val tshirt2=findViewById<ImageView>(R.id.tshirt)
            val tshirt3=findViewById<ImageView>(R.id.tshirt1)
            val tshirt4=findViewById<ImageView>(R.id.tshirt2)
            val hoodie1=findViewById<ImageView>(R.id.hoodie)
            val hoodie2=findViewById<ImageView>(R.id.hoodie3)
            val winterjacket1=findViewById<ImageView>(R.id.winterjacket)
            val winterjacket2=findViewById<ImageView>(R.id.winterjacket3)
            tshirt1.visibility = View.GONE
            tshirt2.visibility = View.VISIBLE
            tshirt4.visibility = View.GONE
            tshirt3.visibility = View.VISIBLE
            hoodie1.visibility = View.GONE
            hoodie2.visibility = View.GONE
            winterjacket1.visibility = View.GONE
            winterjacket2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeTshirt(R.drawable.tshirt)
        }
        tshirtImage2.setOnClickListener {
            val tshirt1=findViewById<ImageView>(R.id.tshirt)
            val tshirt2=findViewById<ImageView>(R.id.tshirt3)
            val tshirt3=findViewById<ImageView>(R.id.tshirt1)
            val tshirt4=findViewById<ImageView>(R.id.tshirt2)
            val hoodie1=findViewById<ImageView>(R.id.hoodie)
            val hoodie2=findViewById<ImageView>(R.id.hoodie3)
            val winterjacket1=findViewById<ImageView>(R.id.winterjacket)
            val winterjacket2=findViewById<ImageView>(R.id.winterjacket3)
            tshirt1.visibility = View.GONE
            tshirt2.visibility = View.VISIBLE
            tshirt3.visibility = View.INVISIBLE
            tshirt4.visibility = View.VISIBLE
            hoodie1.visibility = View.GONE
            hoodie2.visibility = View.GONE
            winterjacket1.visibility = View.GONE
            winterjacket2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeTshirt(R.drawable.tshirt2)
        }
    }

    @SuppressLint("MissingInflatedId")
    fun changewinterjacket(view: View){
        val popupView = LayoutInflater.from(this).inflate(R.layout.changewinterjacket, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(
            findViewById(R.id.change_layout),
            Gravity.CENTER,
            0,
            0
        )
        val winterjacketImage1 = popupView.findViewById<ImageView>(R.id.imageView17)
        val winterjacketImage2 = popupView.findViewById<ImageView>(R.id.imageView18)

// set a click listener on the hat images to close the popup window
        winterjacketImage1.setOnClickListener {
            val winterjacket1=findViewById<ImageView>(R.id.winterjacket3)
            val winterjacket2=findViewById<ImageView>(R.id.winterjacket)
            val winterjacket3=findViewById<ImageView>(R.id.winterjacket1)
            val winterjacket4=findViewById<ImageView>(R.id.winterjacket2)
            val hoodie1=findViewById<ImageView>(R.id.hoodie)
            val hoodie2=findViewById<ImageView>(R.id.hoodie3)
            val tshirt1=findViewById<ImageView>(R.id.tshirt)
            val tshirt2=findViewById<ImageView>(R.id.tshirt3)
            winterjacket1.visibility = View.GONE
            winterjacket2.visibility = View.VISIBLE
            winterjacket4.visibility = View.GONE
            winterjacket3.visibility = View.VISIBLE
            hoodie1.visibility = View.GONE
            hoodie2.visibility = View.GONE
            tshirt1.visibility = View.GONE
            tshirt2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeWinterJacket(R.drawable.winter_jacket)
        }
        winterjacketImage2.setOnClickListener {
            val winterjacket1=findViewById<ImageView>(R.id.winterjacket)
            val winterjacket2=findViewById<ImageView>(R.id.winterjacket3)
            val winterjacket3=findViewById<ImageView>(R.id.winterjacket1)
            val winterjacket4=findViewById<ImageView>(R.id.winterjacket2)
            val hoodie1=findViewById<ImageView>(R.id.hoodie)
            val hoodie2=findViewById<ImageView>(R.id.hoodie3)
            val tshirt1=findViewById<ImageView>(R.id.tshirt)
            val tshirt2=findViewById<ImageView>(R.id.tshirt3)
            winterjacket1.visibility = View.GONE
            winterjacket2.visibility = View.VISIBLE
            winterjacket3.visibility = View.INVISIBLE
            winterjacket4.visibility = View.VISIBLE
            hoodie1.visibility = View.GONE
            hoodie2.visibility = View.GONE
            tshirt1.visibility = View.GONE
            tshirt2.visibility = View.GONE
            popupWindow.dismiss()
            storage.changeWinterJacket(R.drawable.winter_jacket2)
        }
    }



}