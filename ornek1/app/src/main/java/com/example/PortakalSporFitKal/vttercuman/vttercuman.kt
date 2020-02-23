package com.example.PortakalSporFitKal.vttercuman

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(val context:Context) : SQLiteOpenHelper(context,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "C:\\Users\\lenovo\\Desktop\\android\\ornek1\\Portakal.db"//database adı
        private val DATABASE_VERSION = 1
    }
    object User{
        var id = 0
    }

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun init(){
        val sqlitedb = this.writableDatabase
        var q =
            "CREATE TABLE IF NOT EXISTS sporcu (ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Ad	TEXT NOT NULL,Soyad	TEXT NOT NULL,"+
                    "Kullanici_adi	TEXT NOT NULL,Sifre	INTEGER NOT NULL,Telefon	INTEGER NOT NULL,"+
                    "Boy	INTEGER NOT NULL,Kilo	INTEGER NOT NULL,Adim   INTEGER NOT NULL DEFAULT 0);"
        sqlitedb.execSQL(q)

        q = "CREATE TABLE IF NOT EXISTS hoca (ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Ad	TEXT NOT NULL,Soyad	TEXT NOT NULL,"+
                "Kullanici_adi	TEXT NOT NULL,Sifre	INTEGER NOT NULL,Telefon	INTEGER NOT NULL);"

        sqlitedb.execSQL(q)
        q="CREATE TABLE IF NOT EXISTS adim(ID INTEGER NOT NULL,Gun TEXT NOT NULL, Tarih DATE NOT NULL ," +
                "Adim INTEGER NOT NULL );"
        sqlitedb.execSQL(q)
    }
    fun ekle(tablename:String,values:Map<String,String>) {
        val sqlitedb = this.writableDatabase
        init()
        val contentValues = ContentValues()
        for((key,value) in values) {
            contentValues.put(key,value)
        }
        val result = sqlitedb.insert(tablename,null,contentValues)

        Toast.makeText(context,if(result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.", Toast.LENGTH_SHORT).show()
    }
    fun fromdb(result: Cursor, sütun:String):String{
        return result.getString(result.getColumnIndex(sütun))
    }

    fun gör(sütunlar:String,tablename: String, sorgu:String = ""):Cursor{

        val sqlitedb =this.readableDatabase
        init()
        val query = "SELECT $sütunlar FROM $tablename $sorgu;"
        val res =  sqlitedb.rawQuery(query,null)
        res.moveToFirst()
        return res
    }

    fun degistir(tablename: String,id:Int=1,values:Map<String,String> ){
        val db = this.writableDatabase
        val cv = ContentValues()
        for((sutun,deger) in values) {
            cv.put(sutun,deger)
        }
        db.update(tablename,cv,"ID = ?",arrayOf(id.toString()))
    }
    fun sil(tablename: String,id: String){
        this.writableDatabase.delete(tablename,"ID = ?", arrayOf(id.toString()))
    }

}
