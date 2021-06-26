package com.example.cryptocurrencyratekotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyratekotlin.model.CruptAdapter
import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.repository.CruptListRepository
import com.example.cryptocurrencyratekotlin.repository.CruptsRepository
import java.util.*


class RecycleViewFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var cruptAdapter: CruptAdapter? = null
    private var repository: CruptListRepository? = null
    private var mTimer: Timer = Timer()
    private var flag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cruptsRequest: CruptsRequest = CruptsRequest(
            requireContext(),
            "https://api.coincap.io/v2/"
        )

        repository = CruptListRepository(cruptsRequest)
        cruptsRequest.makeRequest()

        startAlarm()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_recycle_view, container, false)

        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        val bundle: Bundle? = arguments
        val index = bundle?.getInt("position")
        if (index != null) {
            recyclerView?.scrollToPosition(index - 3)
        }
        return view
    }

    private fun startAlarm() {
        mTimer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    if (isAdded()) {
                        requireActivity().runOnUiThread {
                            UpdateRecyclerView()
                        }
                    }
                }
            }, 0
            , 1000
        )
    }

    private fun UpdateRecyclerView() {
        if (flag) {
            if (repository!!.crupts != null) {

                cruptAdapter = CruptAdapter(repository!!.crupts, requireContext(), recyclerView, activity)
                recyclerView!!.adapter = cruptAdapter
                flag = false
                mTimer.cancel()
            }
        }
    }
}