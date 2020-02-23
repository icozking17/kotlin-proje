package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.ogrencibilgiler.*

class OgrencibilgilerMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrencibilgiler)
        val id = DBHelper.User.id
        val result = db.gör("*","sporcu","WHERE ID = $id")
        ad.text =  db.fromdb(result,"Ad")
        soyadi.text =  db.fromdb(result,"Soyad")
        val kilo = db.fromdb(result,"Kilo").toFloat()

        val boy = db.fromdb(result,"Boy").toFloat()
        kilog.text =  kilo.toString()
        boym.text =  boy.toString()

        vki.text =  ((kilo / (boy *boy))).toString()
        telefon.text=db.fromdb(result,"Telefon")
        anas.setOnClickListener {
            val geri = Intent(this,AnasMainActivity::class.java)
            startActivity(geri)
        }
        guncelle.setOnClickListener {
            val intent = Intent(this,OgrenciguncelleMainActivity::class.java)
            startActivity(intent)
        }
        has.setOnClickListener {
            val intent = Intent(this,AdimMainActivity::class.java)
            startActivity(intent)
        }
    }
}
