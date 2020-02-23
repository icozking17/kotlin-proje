package com.example.PortakalSporFitKal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.adim.*

class AdimMainActivity : AppCompatActivity() {
    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        val id = DBHelper.User.id
        val res=db.gör("*","adim","WHERE ID=$id ORDER BY Tarih DESC")
        if(res.moveToFirst()) {
            var i = 1
            do {
                if(i++>=7)
                {
                    val tw1=TextView(this)
                    tw1.text=db.fromdb(res,"Tarih")
                    tw1.textSize=14.0F
                    tw1.gravity=Gravity.CENTER


                    val tw2=TextView(this )
                    tw2.text=db.fromdb(res,"Adim")
                    tw2.textSize=14.0F
                    tw2.gravity=Gravity.CENTER
                }

            } while (res.moveToNext())
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.adim)
        anasayfa.setOnClickListener {
        val intent = Intent(this,AnasMainActivity::class.java)
        startActivity(intent)
        }
        geri.setOnClickListener {
            val intent = Intent(this,OgrencibilgilerMainActivity::class.java)
            startActivity(intent)
        }

    }
}
