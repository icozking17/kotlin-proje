package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import com.example.PortakalSporFitKal.vttercuman.DBHelper.User.id
import kotlinx.android.synthetic.main.egitmenbilgiler.*

class EgitmenBilgilerMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenbilgiler)
        val result=db.gör("*","hoca","WHERE ID = $id")
        adi.text=db.fromdb(result,"Ad")
        soyadi.text=db.fromdb(result,"Soyad")
        telefon.text=db.fromdb(result,"Telefon")

        geri.setOnClickListener{
            val intent = Intent(this,EgitmenAnasayfaMainActivity::class.java)
            startActivity(intent)
        }
        anasayfa.setOnClickListener {
            val intent = Intent(this,EgitmenAnasayfaMainActivity::class.java)
            startActivity(intent)
        }
        guncelle.setOnClickListener {
            val intent = Intent(this,EgitmenGuncelleMainActivity::class.java)
            startActivity(intent)
        }
    }
}
