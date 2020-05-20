package com.zev.wanandroidex.mvp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils

import com.zev.wanandroidex.R
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    private var resId: Int = 0
    private var callback:ImageCallback?=null

    companion object {
        fun getInstance(resId: Int): ImageFragment {
            val f = ImageFragment()
            val b = Bundle()
            b.putInt("resId", resId)
            f.arguments = b
            return f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    fun setCallback(callback: ImageCallback):ImageFragment {
        this.callback = callback
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resId = arguments?.getInt("resId")!!
        ivImage.setImageResource(resId)
        ivImage.setOnClickListener {
            callback?.imageClick()
            LogUtils.d("ivImage====onClick") }
    }


     interface ImageCallback{
         fun imageClick()
    }
}
