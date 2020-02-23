package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.egitmengiris.*

class EgitmengirisMainActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.egitmengiris)
        super.onCreate(savedInstanceState)

        geri.setOnClickListener{
            val intent = Intent(this,BaslaMainActivity::class.java)
            startActivity(intent)
        }

        yenikyt.setOnClickListener{
            val asd = Intent(this,EgitmenyenikayitMainActivity::class.java)
            startActivity(asd)

        }
        uyeg.setOnClickListener{
            val asd = Intent(this,EgitmenuyegirisMainActivity::class.java)
            startActivity(asd)
         }
    }
}
