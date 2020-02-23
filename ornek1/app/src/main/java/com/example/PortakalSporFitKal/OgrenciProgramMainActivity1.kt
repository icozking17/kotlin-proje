package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.ogrenciprogram1.*

class OgrenciProgramMainActivity1:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciprogram1)

        s2.setOnClickListener{
            val intent = Intent(this,OgrenciProgramMainActivity2::class.java)
            startActivity(intent)
        }
        geri.setOnClickListener{
            val intent = Intent(this,AnasMainActivity::class.java)
            startActivity(intent)
        }

    }
}
