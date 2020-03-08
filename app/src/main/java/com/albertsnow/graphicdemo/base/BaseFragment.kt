package com.albertsnow.graphicdemo.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


open class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = ResultProfileBinding.inflate(inflater, container, false)
//        return binding.getRoot()

        return super.onCreateView(inflater, container, savedInstanceState)

    }


}