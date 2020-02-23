package com.example.PortakalSporFitKal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.ogrenciyenikyt2.*


class Ogrenciyenikyt1MainActivity2:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ogrenciyenikyt2)

        geri.setOnClickListener{
            val intent = Intent(this,Ogrenciyenikyt1MainActivity::class.java)
            startActivity(intent)
        }
        giris.setOnClickListener {
            val yas = yas.text.toString().toInt()
            if(yas < 18){
                Toast.makeText(this@Ogrenciyenikyt1MainActivity2,"Yaşınız fazla küçük",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val keys = intent.getStringArrayExtra("keys").toMutableList()
            val values = intent.getStringArrayExtra("values").toMutableList()
            keys.add("Boy")
            keys.add("Kilo")
            values.add(boy.text.toString())
            values.add(kilo.text.toString())
            val map = keys.zip(values).toMap()
            db.ekle("sporcu",map)
            val tel =map["Telefon"]
            val res = db.gör("ID","sporcu","WHERE Telefon = '$tel'")
            val id = db.fromdb(res,"ID")
            DBHelper.User.id = id.toInt()
            val intent = Intent(this, AnasMainActivity::class.java)
            startActivity(intent)
        }
    }
}
