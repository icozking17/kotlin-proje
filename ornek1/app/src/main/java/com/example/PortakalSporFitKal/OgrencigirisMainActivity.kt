package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.ogrencigiris.*

class OgrencigirisMainActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.ogrencigiris)
        super.onCreate(savedInstanceState)

        geri.setOnClickListener{
            val intent = Intent(this,BaslaMainActivity::class.java)
            startActivity(intent)
        }

        yenik.setOnClickListener{
            val asd = Intent(this,Ogrenciyenikyt1MainActivity::class.java)
            startActivity(asd)

        }
        uyeg.setOnClickListener{
            val asd = Intent(this,OgrenciuyegirisMainActivity::class.java)
            startActivity(asd)
         }
    }
}
