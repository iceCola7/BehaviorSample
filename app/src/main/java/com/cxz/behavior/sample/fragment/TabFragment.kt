package com.cxz.behavior.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cxz.behavior.sample.R

/**
 * @author chenxz
 * @date 2020/9/11
 * @desc
 */
class TabFragment : Fragment() {

    private var name: String = ""

    companion object {
        fun newInstance(name: String): Fragment {
            val args = Bundle()
            args.putString("data", name)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("data") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        val nameTV = view.findViewById<TextView>(R.id.tv_fragment_tab_text)
        nameTV.text = name
        return view
    }

}