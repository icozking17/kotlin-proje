package com.example.PortakalSporFitKal

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.egitmenogrenciliste.*


class EgitmenogrencilisteMainActivity : AppCompatActivity() {
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenogrenciliste)

        val id = DBHelper.User.id
        val res =db.gör("ID,Ad,Soyad","sporcu")
        if(res.moveToFirst()){
            var i=1
            do {
                if(i++>=5)break
                val parent = LinearLayout(this)
                parent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                parent.orientation = LinearLayout.HORIZONTAL

                 val tw1 = TextView(this)
                tw1.text=db.fromdb(res,"Ad")
                tw1.textSize= 25.0F
                tw1.width=200
                tw1.height=80
                tw1.gravity=Gravity.CENTER
                tw1.paintFlags=Paint.FAKE_BOLD_TEXT_FLAG

                val tw2 = TextView(this)
                tw2.text=db.fromdb(res,"Soyad")
                tw2.gravity=Gravity.CENTER
                tw2.textSize=25.0f
                tw2.width=200
                tw2.height=80
                tw2.paintFlags=Paint.FAKE_BOLD_TEXT_FLAG

                val bt = Button(this)
                bt.text = "Detay..."
                bt.gravity = Gravity.CENTER
                bt.setBackgroundColor(Color.TRANSPARENT)
                bt.textSize=20.0f
                bt.tag=db.fromdb(res,"ID")
                bt.setOnClickListener{
                    val intent = Intent(this,EgitmenogrencibilgiMainActivity::class.java)
                    intent.putExtra("ID",bt.tag.toString())
                    startActivity(intent)
                }
                parent.addView(tw1)
                parent.addView(tw2)
                parent.addView(bt)
                val mami = findViewById<LinearLayout>(R.id.Ana)
                mami.addView(parent)
            }while (res.moveToNext())

        }

        anasa.setOnClickListener{
            val intent = Intent(this,EgitmenAnasayfaMainActivity::class.java)
            startActivity(intent)
        }

    }
}