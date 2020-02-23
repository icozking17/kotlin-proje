package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.ogrenciprogram2.*

class OgrenciProgramMainActivity2:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciprogram2)

        s3.setOnClickListener{
            val intent = Intent(this,OgrenciProgramMainActivity3::class.java)
            startActivity(intent)
        }
        geri.setOnClickListener{
            val intent = Intent(this,OgrenciProgramMainActivity1::class.java)
            startActivity(intent)
        }

    }
}
