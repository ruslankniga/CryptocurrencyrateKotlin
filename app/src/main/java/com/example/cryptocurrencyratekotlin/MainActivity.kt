package com.example.cryptocurrencyratekotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyratekotlin.model.CruptAdapter
import com.example.cryptocurrencyratekotlin.model.CruptList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.coincap.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api: Api = retrofit.create(Api::class.java)
        val call: Call<CruptList> = api.getCrupt()

        call.enqueue(object : Callback<CruptList> {
            override fun onResponse(call: Call<CruptList>, response: Response<CruptList>) {
                if (!response.isSuccessful()) {
                    Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()
                    return
                }
                val cruptAdapter = CruptAdapter( response.body(),this@MainActivity, recyclerView)
                recyclerView?.adapter = cruptAdapter
            }

            override fun onFailure(call: Call<CruptList>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}