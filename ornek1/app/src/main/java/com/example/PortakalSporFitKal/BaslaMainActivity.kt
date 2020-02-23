package com.example.PortakalSporFitKal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.basla.*

class BaslaMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basla)

        sporcu.setOnClickListener{
            val intent = Intent(this,OgrencigirisMainActivity::class.java)
            startActivity(intent)
        }
        egitmen.setOnClickListener{
            val intent = Intent(this,EgitmengirisMainActivity::class.java)
            startActivity(intent)
        }

    }
}