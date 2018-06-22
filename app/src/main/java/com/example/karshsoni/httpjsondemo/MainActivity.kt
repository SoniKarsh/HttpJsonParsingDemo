package com.example.karshsoni.httpjsondemo

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var items = ArrayList<MyDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jsonParseClass = JsonParseClass()
        jsonParseClass.execute()

    }
    inner class JsonParseClass: AsyncTask<Unit, Unit, Unit>(){
        val TAG = "Json Parse Class"
        override fun onPostExecute(result: Unit?) {
            val layout = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = DataAdapter(items, this@MainActivity)
            recyclerView.layoutManager=layout
        }

        val reqUrl = "https://api.androidhive.info/contacts/"
        override fun doInBackground(vararg params: Unit?) {
            val httpUrlConnectionClass = HttpUrlConnectionClass()
            val jsonString = httpUrlConnectionClass.startConnection(reqUrl)
            if(jsonString != ""){
                try{
                    val jsonObject = JSONObject(jsonString)
                    val contacts: JSONArray = jsonObject.getJSONArray("contacts")
                    val range = contacts.length() - 1
                    for(i in 0..range){
                        val contactJsonObject = contacts.getJSONObject(i)
                        val id = contactJsonObject.get("id").toString()
                        val name = contactJsonObject.get("name").toString()
                        val email = contactJsonObject.getString("email")
                        val address = contactJsonObject.get("address").toString()
                        val gender = contactJsonObject.get("gender").toString()
                        val phoneObject = contactJsonObject.getJSONObject("phone")
                        val mobile = phoneObject.get("mobile").toString()
                        val home = phoneObject.get("home").toString()
                        val office = phoneObject.get("office").toString()
                        val myTable  = MyDataClass(id, name, email, gender)
                        items.add(myTable)
                    }
                    Log.e("MainActivity Background", items.toString()+"in Log")
                }catch (e: Exception){
                    Log.e("Catch", e.toString())
                    e.printStackTrace()
                }
            }
        }
    }
}
