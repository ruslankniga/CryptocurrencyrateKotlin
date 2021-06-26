package com.example.cryptocurrencyratekotlin.model

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyratekotlin.DataCruptFragment
import com.example.cryptocurrencyratekotlin.R

class CruptAdapter(val cruptList: CruptList?, val context: Context, val recyclerView: RecyclerView?, val activity: FragmentActivity?)
    : RecyclerView.Adapter<CruptAdapter.CruptViewHolder>() {



    private val mOnClickListener = View.OnClickListener { v ->

            val fragment = DataCruptFragment()
            val index = recyclerView!!.getChildLayoutPosition(v)

            val bundle: Bundle = Bundle()
            bundle.putParcelable("Crupt", cruptList!!.getByIndex(index))
            fragment.arguments  = bundle

            val fm: FragmentManager = activity!!.supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.mainFragment, fragment)
            ft.commit()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CruptViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.crupt_item, parent, false)
        view.setOnClickListener(mOnClickListener)
        return CruptViewHolder(view)
    }

    override fun onBindViewHolder(holder: CruptViewHolder, position: Int) {
        val crupt: Crupt = cruptList!!.getByIndex(position)

        holder.rank.text = crupt.rank + "|"
        holder.symbol.text = crupt.symbol
        holder.name.text = crupt.name
        holder.priceUSD.text = "USD " + crupt.priceUsd
        holder.changePrice24H.text = "24H   " + crupt.changePercent24Hr
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