package com.albertsnow.graphicdemo.widget

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.albertsnow.graphicdemo.R

class CustomFragmentManager<T> {


    fun initFragment(activity: FragmentActivity, createFun: ()-> Fragment) {
        with(activity) {
            val fragment = supportFragmentManager.findFragmentById(R.id.widget_content_container)
            val selectFragment: T? = if (fragment == null) null else fragment as T

            if (selectFragment == null) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.widget_content_container, createFun())
                transaction.commit()
            }
        }
    }


}