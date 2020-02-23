package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import com.example.PortakalSporFitKal.vttercuman.DBHelper.User.id
import kotlinx.android.synthetic.main.ogrenciguncelle.*
import kotlinx.android.synthetic.main.ogrenciguncelle.geri

class OgrenciguncelleMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciguncelle)


        val görek = db.gör("*","sporcu","WHERE ID = $id")

        ad.setText(db.fromdb(görek,"Ad"))
        soyad.setText(db.fromdb(görek,"Soyad"))
        sifre.setText(db.fromdb(görek,"Sifre"))
         boy.setText(db.fromdb(görek,"Boy"))
        kilo.setText(db.fromdb(görek,"Kilo"))
        telefon.setText(db.fromdb(görek,"Telefon"))

        geri.setOnClickListener{
            val intent = Intent(this,OgrencibilgilerMainActivity::class.java)
            startActivity(intent)
        }
        guncelle.setOnClickListener {
            if(sifre.text.length <6 ||sifre.text.length > 10){
                Toast.makeText(this,"Şifre 6-10 karakter arası olmalıdır",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(telefon.text.length != 10){
                Toast.makeText(this,"Telefon 10 karakter olmalıdır",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val values = mapOf(
                "Ad" to ad.text.toString(),
                "Soyad" to soyad.text.toString(),
                "Sifre" to sifre.text.toString(),
                "Boy" to boy.text.toString(),
                "Kilo" to kilo.text.toString(),
                "Telefon" to telefon.text.toString()
            )
            db.degistir("sporcu",id,values)

            val intent = Intent(this, AnasMainActivity::class.java)

            Toast.makeText(this@OgrenciguncelleMainActivity,"Guncelleme Başarılı!!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}
