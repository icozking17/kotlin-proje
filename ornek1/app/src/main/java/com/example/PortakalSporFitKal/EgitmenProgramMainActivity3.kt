package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.egitmenprogram3.*

class EgitmenProgramMainActivity3:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenprogram3)

            anas.setOnClickListener{
                val intent = Intent(this,EgitmenAnasayfaMainActivity::class.java)
                startActivity(intent)
            }
            geri.setOnClickListener{
                val intent = Intent(this,EgitmenProgramMainActivity2::class.java)
                startActivity(intent)
            }

        }
    }
