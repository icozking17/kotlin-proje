package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.ogrenciprogram3.*

class OgrenciProgramMainActivity3:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciprogram3)

            anas.setOnClickListener{
                val intent = Intent(this,AnasMainActivity::class.java)
                startActivity(intent)
            }
            geri.setOnClickListener{
                val intent = Intent(this,OgrenciProgramMainActivity2::class.java)
                startActivity(intent)
            }


        }
    }
