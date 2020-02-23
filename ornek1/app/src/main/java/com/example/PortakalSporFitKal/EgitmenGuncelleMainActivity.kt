package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.egitmenguncelle.*
import kotlinx.android.synthetic.main.egitmenguncelle.adi
import kotlinx.android.synthetic.main.egitmenguncelle.soyadi
import kotlinx.android.synthetic.main.egitmenguncelle.telefon

class EgitmenGuncelleMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenguncelle)


        val göre = db.gör("*","hoca","WHERE ID = ${DBHelper.User.id}")
        adi.setText(db.fromdb(göre,"Ad"))
        soyadi.setText(db.fromdb(göre,"Soyad"))
        sifre.setText(db.fromdb(göre,"Sifre"))
        telefon.setText(db.fromdb(göre,"Telefon"))

        geri.setOnClickListener{
            val intent = Intent(this,EgitmenBilgilerMainActivity::class.java)
            startActivity(intent)
        }

        guncelle.setOnClickListener {
            if(sifre.text.length <6 ||sifre.text.length > 10){
                Toast.makeText(this,"Şifre 6-10 karakter arası olmalıdır",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(telefon.text.length != 10) {
                Toast.makeText(this, "Telefon 10 karakter olmalıdır", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val values = mapOf(
                "Ad" to adi.text.toString(),
                "Soyad" to soyadi.text.toString(),
                "Sifre" to sifre.text.toString(),
                "Telefon" to telefon.text.toString()
            )
            db.degistir("hoca", DBHelper.User.id,values)

            val intent = Intent(this, EgitmenAnasayfaMainActivity::class.java)
            Toast.makeText(this@EgitmenGuncelleMainActivity,"Guncelleme Başarılı!!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}
