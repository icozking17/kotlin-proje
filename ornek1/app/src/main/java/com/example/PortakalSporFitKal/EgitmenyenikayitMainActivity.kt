package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.egitmenyenikayit.*
class EgitmenyenikayitMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenyenikayit)

        geri.setOnClickListener{
            val intent = Intent(this,EgitmengirisMainActivity::class.java)
            startActivity(intent)
        }
        devam.setOnClickListener {
            val sfr = sifre.text.toString()
            val tel = tel.text.toString()
            if(sfr.length <6 ||sfr.length > 10){
                Toast.makeText(this,"Şifre 6-10 karakter arası olmalıdır", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(tel.length != 10){
                Toast.makeText(this,"Telefon 10 karakter olmalıdır", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val kadi =  kadi.text.toString()
            val values  =mapOf(
                "Ad" to adi.text.toString(),
                "Soyad" to soyadi.text.toString(),
                "Kullanici_adi" to kadi,
                "Sifre" to sfr,
                "Telefon" to tel)

            db.ekle("hoca",values)
            val result =db.gör("ID","hoca","WHERE Kullanici_adi='$kadi' AND Sifre='$sfr'")
            val id = db.fromdb(result,"ID")
            DBHelper.User.id = id.toInt()

            val intent = Intent(this, EgitmenAnasayfaMainActivity::class.java)

            startActivity(intent)
        }
    }
}
