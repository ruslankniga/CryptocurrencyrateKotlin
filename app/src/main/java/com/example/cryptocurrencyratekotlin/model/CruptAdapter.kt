package com.example.cryptocurrencyratekotlin.model

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyratekotlin.Cryptocurrency_Data
import com.example.cryptocurrencyratekotlin.R

class CruptAdapter(crupts: CruptList?, contexts: Context, recyclerViews: RecyclerView?)
    : RecyclerView.Adapter<CruptAdapter.CruptViewHolder>() {

    private val cruptList: CruptList? = crupts
    private val context: Context = contexts
    private val recyclerView: RecyclerView? = recyclerViews


    private val mOnClickListener = View.OnClickListener { v ->
            val index = recyclerView!!.getChildLayoutPosition(v)

            val intent = Intent(context, Cryptocurrency_Data::class.java)

            intent.putExtra("rank", cruptList!!.getByIndex(index).rank)
            intent.putExtra("symbol", cruptList.getByIndex(index).symbol)
            intent.putExtra("name", cruptList.getByIndex(index).name)
            intent.putExtra("id", cruptList.getByIndex(index).id)
            intent.putExtra("priceUsd", cruptList.getByIndex(index).priceUsd)
            intent.putExtra("supply", cruptList.getByIndex(index).supply)
            intent.putExtra("maxSupply", cruptList.getByIndex(index).maxSupply)
            intent.putExtra("volumeUsd24Hr", cruptList.getByIndex(index).volumeUsd24Hr)
            intent.putExtra("changePercent24Hr", cruptList.getByIndex(index).changePercent24Hr)
            intent.putExtra("vwap24Hr", cruptList.getByIndex(index).vwap24Hr)

            context.startActivity(intent)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CruptViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.crupt_item, parent, false)
        view.setOnClickListener(mOnClickListener)
        return CruptViewHolder(view)
    }

    override fun onBindViewHolder(holder: CruptViewHolder, position: Int) {
        val crupt: Crupt = cruptList!!.getByIndex(position)

        holder.rank.setText(crupt.rank + "|")
        holder.symbol.setText(crupt.symbol)
        holder.name.setText(crupt.name)
        holder.priceUSD.setText("USD " + crupt.priceUsd)
        holder.changePrice24H.setText("24H   " + crupt.changePercent24Hr)
        if (crupt.changePercent24Hr!!.toDouble() > 0) {
            holder.changePrice24H.setTextColor(Color.GREEN)
        } else {
            holder.changePrice24H.setTextColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return cruptList!!.size
    }

    class CruptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rank: TextView
        var symbol: TextView
        var name: TextView
        var priceUSD: TextView
        var changePrice24H: TextView

        init {
            rank = itemView.findViewById(R.id.rank)
            symbol = itemView.findViewById(R.id.symbol)
            name = itemView.findViewById(R.id.name)
            priceUSD = itemView.findViewById(R.id.priceUSD)
            changePrice24H = itemView.findViewById(R.id.changePrice24H)
        }
    }
}