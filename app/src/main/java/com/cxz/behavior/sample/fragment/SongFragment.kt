package com.cxz.behavior.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cxz.behavior.sample.R
import com.cxz.behavior.sample.adapter.SongAdapter
import kotlinx.android.synthetic.main.fragment_song.*

/**
 * @author chenxz
 * @date 2020/9/11
 * @desc
 */
class SongFragment : Fragment() {

    private var songAdapter: SongAdapter? = null

    private val mDataList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_song, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = songAdapter
    }

    private fun initData() {
        for (i in 0..32) {
            mDataList.add(i)
        }
        songAdapter = SongAdapter()
        songAdapter?.addData(mDataList)
    }
}