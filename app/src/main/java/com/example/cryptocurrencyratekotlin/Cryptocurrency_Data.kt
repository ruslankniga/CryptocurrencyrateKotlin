package com.example.cryptocurrencyratekotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyratekotlin.model.CruptRateList
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Cryptocurrency_Data : AppCompatActivity() {

    private var rank: TextView? = null
    private var name: TextView? = null
    private var priceUsd: TextView? = null
    private var supply: TextView? = null
    private var maxSupply: TextView? = null
    private var volumeUsd24Hr: TextView? = null
    private var changePercent24Hr: TextView? = null
    private var vwap24Hr: TextView? = null

    private var idData: String? = null
    private var symbolData: String? = null
    private var rankData: String? = null
    private var nameData: String? = null
    private var priceUsdData: String? = null
    private var supplyData: String? = null
    private var maxSupplyData: String? = null
    private var volumeUsd24HrData: String? = null
    private var changePercent24HrData: String? = null
    private var vwap24HrData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptocurrency__data)

        val intent = intent

        InitializationData(intent)
        InitializationTextView()
        ApiRequest()
    }

    private fun ApiRequest(){

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.coincap.io/v2/assets/" + idData + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: Api = retrofit.create(Api::class.java)
        val call: Call<CruptRateList> = api.getCruptRate()

        call.enqueue(object : Callback<CruptRateList> {
            override fun onResponse(call: Call<CruptRateList>, response: Response<CruptRateList>) {
                if (!response.isSuccessful()) {
                    Toast.makeText(this@Cryptocurrency_Data, response.code(), Toast.LENGTH_SHORT).show()
                    return
                }
                Graph(response.body())
            }

            override fun onFailure(call: Call<CruptRateList>, t: Throwable) {
                Toast.makeText(this@Cryptocurrency_Data, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun Graph(cruptRateList: CruptRateList?){
        //val graphView = findViewById<View>(R.id.graph) as GraphView
        val graphView: GraphView = findViewById(R.id.graph)
        val series = LineGraphSeries<DataPoint>()

        for (i in 0 until cruptRateList!!.size)
        {
            series.appendData(DataPoint(i.toDouble(), cruptRateList.getByIndex(i)?.priceUsd!!.toDouble()), true, cruptRateList.size)
        }

        graphView.addSeries(series)
        series.color = Color.BLUE
        series.title = symbolData

        graphView.titleColor = Color.BLUE
        graphView.titleTextSize = 46f
        graphView.legendRenderer.isVisible = true
        graphView.legendRenderer.align = LegendRenderer.LegendAlign.TOP
    }

    private fun InitializationData(intent: Intent) {
        idData = intent.getStringExtra("id")
        symbolData = intent.getStringExtra("symbol")
        rankData = intent.getStringExtra("rank")
        nameData = intent.getStringExtra("name")
        priceUsdData = intent.getStringExtra("priceUsd")
        supplyData = intent.getStringExtra("supply")
        maxSupplyData = intent.getStringExtra("maxSupply")
        volumeUsd24HrData = intent.getStringExtra("volumeUsd24Hr")
        changePercent24HrData = intent.getStringExtra("changePercent24Hr")
        vwap24HrData = intent.getStringExtra("vwap24Hr")
    }

    private fun InitializationTextView(){
        rank = findViewById(R.id.rank_data)
        rank?.text = rankData + "|"
        name = findViewById(R.id.name_data)
        name?.text = nameData
        priceUsd = findViewById(R.id.priceUsd_data)
        priceUsd?.text = "USD " + priceUsdData
        supply = findViewById(R.id.suply_data)
        supply?.text = "Доступный запас " + supplyData
        maxSupply = findViewById(R.id.maxSuply_data)
        maxSupply?.text = "Общее кол-во активов " + maxSupplyData
        volumeUsd24Hr = findViewById(R.id.volumeUsd24Hr_data)
        volumeUsd24Hr?.text = "Кол-во торгового объема в USD за 24H " + volumeUsd24HrData
        changePercent24Hr = findViewById(R.id.changePercent24Hr_data)
        changePercent24Hr?.text = "24H   " + changePercent24HrData
        if (changePercent24HrData!!.toDouble() > 0) {
            changePercent24Hr?.setTextColor(Color.GREEN)
        } else {
            changePercent24Hr?.setTextColor(Color.RED)
        }
        vwap24Hr = findViewById(R.id.vwap24Hr_data)
        vwap24Hr?.text = "Средняя цена за последние 24H " + vwap24HrData
    }
}