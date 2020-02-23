package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.egitmenprogram2.*

class EgitmenProgramMainActivity2:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenprogram2)

            s3.setOnClickListener{
                val intent = Intent(this,EgitmenProgramMainActivity3::class.java)
                startActivity(intent)
            }
            geri.setOnClickListener{
                val intent = Intent(this,EgitmenProgramMainActivity1::class.java)
                startActivity(intent)
            }

        }
    }
