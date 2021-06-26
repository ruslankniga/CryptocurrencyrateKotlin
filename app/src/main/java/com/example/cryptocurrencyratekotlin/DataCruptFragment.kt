package com.example.cryptocurrencyratekotlin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cryptocurrencyratekotlin.model.Crupt
import com.example.cryptocurrencyratekotlin.model.CruptRateList
import com.example.cryptocurrencyratekotlin.repository.RateListRepository
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*


class DataCruptFragment : Fragment() {

    private var repository: RateListRepository? = null
    private var mTimer: Timer = Timer()
    private var flag: Boolean = true

    private var rank: TextView? = null
    private var name: TextView? = null
    private var priceUsd: TextView? = null
    private var supply: TextView? = null
    private var maxSupply: TextView? = null
    private var volumeUsd24Hr: TextView? = null
    private var vwap24Hr: TextView? = null
    private var graphView: GraphView? = null

    private var crupt: Crupt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = arguments
        crupt = bundle!!.getParcelable("Crupt")


        val ratesRequest: RatesRequest = RatesRequest(
            requireContext(),
            "https://api.coincap.io/v2/assets/" + crupt?.id + "/"
        )

        repository = RateListRepository(ratesRequest)
        ratesRequest.makeRequest()

        startAlarm()
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_data_crupt, container, false)

        rank = view.findViewById(R.id.rankf) as TextView
        rank?.text = crupt?.rank + "|"
        name = view.findViewById(R.id.namef)
        name?.text = crupt?.name
        priceUsd = view.findViewById(R.id.priceUSDf)
        priceUsd?.text = "USD " + crupt?.priceUsd
        supply = view.findViewById(R.id.supplyf)
        supply?.text = "Доступный запас " + crupt?.supply
        maxSupply = view.findViewById(R.id.maxSupplyf)
        maxSupply?.text = "Общее кол-во активов " + crupt?.maxSupply
        volumeUsd24Hr = view.findViewById(R.id.volumeUsd24Hrf)
        volumeUsd24Hr?.text = "Кол-во торгового объема в USD за 24H " + crupt?.volumeUsd24Hr
        vwap24Hr = view.findViewById(R.id.vwap24Hrf)
        vwap24Hr?.text = "Средняя цена за последние 24H " + crupt?.vwap24Hr
        graphView = view.findViewById(R.id.graph)

        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val fragment = RecycleViewFragment()
            val fm: FragmentManager = requireActivity().supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()

            val bundle: Bundle = Bundle()
            bundle.putInt("position", crupt!!.rank.toInt())
            fragment.arguments  = bundle

            ft.replace(R.id.mainFragment, fragment)
            ft.commit()
        }

        return view
    }

    private fun Graph(cruptRateList: CruptRateList?){
        val series = LineGraphSeries<DataPoint>()

        for (i in 0 until cruptRateList!!.size)
        {
            series.appendData(DataPoint(i.toDouble(), cruptRateList.getByIndex(i)?.priceUsd!!.toDouble()), true, cruptRateList.size)
        }

        graphView!!.addSeries(series)
        series.color = Color.BLUE
        series.title = crupt?.symbol

        graphView!!.titleColor = Color.BLUE
        graphView!!.titleTextSize = 46f
        graphView!!.legendRenderer.isVisible = true
        graphView!!.legendRenderer.align = LegendRenderer.LegendAlign.TOP
    }

    private fun startAlarm() {
        mTimer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    if(isAdded()) {
                        requireActivity().runOnUiThread {
                            UpdateGraph()
                        }
                    }
                }
            }, 0
            , 1000
        )
    }

    private fun UpdateGraph() {
        if (flag) {
            if (repository!!.rates != null) {

                Graph(repository!!.rates)
                flag = false
                mTimer.cancel()
            }
        }
    }
}

