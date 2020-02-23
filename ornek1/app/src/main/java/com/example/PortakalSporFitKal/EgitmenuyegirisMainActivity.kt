package com.example.PortakalSporFitKal
import android.content.Intent
import android.database.CursorIndexOutOfBoundsException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.PortakalSporFitKal.vttercuman.DBHelper
import kotlinx.android.synthetic.main.egitmenuyegiris.*

class EgitmenuyegirisMainActivity:AppCompatActivity(){
    val db by lazy { DBHelper(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.egitmenuyegiris)
        geri.setOnClickListener {
            val intent = Intent(this, EgitmengirisMainActivity::class.java)
            startActivity(intent)

        }
        giris.setOnClickListener {
            val girilenkuladi = kuladi.text.toString()
            val girilensifre = sifre.text.toString()

            val result =db.gör("ID","hoca","WHERE Kullanici_adi='$girilenkuladi' AND Sifre='$girilensifre'")
            try {
                val id = db.fromdb(result,"ID")
                DBHelper.User.id = id.toInt()
                val enter = Intent(this, EgitmenAnasayfaMainActivity::class.java)
                startActivity(enter)

            }
            catch (e:CursorIndexOutOfBoundsException){
                Toast.makeText(this@EgitmenuyegirisMainActivity,"KULLANICI ADI VEYA SİFRE YANLIS!!",Toast.LENGTH_LONG).show()
            }

        }


        }

    }

