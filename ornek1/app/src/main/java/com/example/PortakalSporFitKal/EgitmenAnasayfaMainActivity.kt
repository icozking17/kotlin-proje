package com.example.PortakalSporFitKal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.egitmenanasayfa.*


class EgitmenAnasayfaMainActivity : AppCompatActivity() {
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenanasayfa)

        val int_id = DBHelper.User.id

        bilgiler.setOnClickListener{
            val intent = Intent(this,EgitmenBilgilerMainActivity::class.java)
            startActivity(intent)
        }
        ogrencilis.setOnClickListener{
            val intent = Intent(this,EgitmenogrencilisteMainActivity::class.java)
            startActivity(intent)

        }
        program.setOnClickListener{
            val intent = Intent(this,EgitmenProgramMainActivity1::class.java)
            startActivity(intent)
        }
        cikis.setOnClickListener{
            val intent = Intent(this,EgitmenuyegirisMainActivity::class.java)
            Toast.makeText(this@EgitmenAnasayfaMainActivity,"Çıkış Yapıldı!!",Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        val result = db.gör("*","hoca","WHERE ID = $int_id")
        val ad = db.fromdb(result,"Ad")
        hosisim.text = "Hoşgeldin $ad"
    }
}