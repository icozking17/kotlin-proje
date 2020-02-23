package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.ogrenciyenikyt1.*

class Ogrenciyenikyt1MainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciyenikyt1)

        geri.setOnClickListener{
            val intent = Intent(this,OgrencigirisMainActivity::class.java)
            startActivity(intent)
        }
        devam.setOnClickListener {
            val sfr = sifre.text.toString()
            val tel = tel.text.toString()
            if(sfr.length <6 ||sfr.length > 10){
                Toast.makeText(this,"Şifre 6-10 karakter arası olmalıdır",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(tel.length != 10){
                Toast.makeText(this,"Telefon 10 karakter olmalıdır",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val values  =mapOf(
                "Ad" to ad.text.toString(),
                "Soyad" to soyad.text.toString(),
                "Kullanici_adi" to kuladi.text.toString(),
                "Sifre" to sfr,
                "Telefon" to tel)
            val intent = Intent(this, Ogrenciyenikyt1MainActivity2::class.java)
            intent.putExtra("keys",values.keys.toTypedArray())
            intent.putExtra("values",values.values.toTypedArray())
            startActivity(intent)
        }
    }

}



