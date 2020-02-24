package com.albertsnow.graphicdemo.widget

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.base.BaseActivity
import com.albertsnow.graphicdemo.widget.textview.TextViewFragment

import kotlinx.android.synthetic.main.activity_widget.*

class WidgetActivity : BaseActivity() {

    val customFragmentManager = CustomFragmentManager<TextViewFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        customFragmentManager.initFragment(this) {
            TextViewFragment.newInstance()
        }

    }

}
