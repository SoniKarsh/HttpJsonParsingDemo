package com.example.karshsoni.httpjsondemo

import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class HttpUrlConnectionClass {

    fun startConnection(reqUrl: String): String{
        var response: String = ""
        lateinit var conn: HttpURLConnection
        try{
            val url = URL(reqUrl)
            conn = url.openConnection() as HttpURLConnection
            val stream = conn.inputStream
            val reader = BufferedReader(InputStreamReader(stream))
            val buffer = StringBuilder()
            reader.forEachLine {
                buffer.append(it)
            }
            response = buffer.toString()
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            conn.disconnect()
        }
        return response
    }

}