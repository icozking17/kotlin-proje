package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.egitmenprogram1.*

class EgitmenProgramMainActivity1:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenprogram1)

        s2.setOnClickListener{
            val intent = Intent(this,EgitmenProgramMainActivity2::class.java)
            startActivity(intent)
        }
        geri.setOnClickListener{
            val intent = Intent(this,EgitmenAnasayfaMainActivity::class.java)
            startActivity(intent)
        }

    }
}
