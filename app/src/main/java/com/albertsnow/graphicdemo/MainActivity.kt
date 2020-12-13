package com.albertsnow.graphicdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.albert.opencvlibrary.CVPictureUtils

import com.albertsnow.graphicdemo.camera.CameraActivity
import com.albertsnow.graphicdemo.opengl.MultiTextureActivity
import com.albertsnow.graphicdemo.opengl.OpenGLActivity
import com.albertsnow.graphicdemo.opengl.QuickSurfaceActivity
import com.albertsnow.graphicdemo.opengl.transform.TransformActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val REQUEST_CODE_CAMERA: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        initView()
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity", "MainActivity onResume");

        requestPermission()
    }

    private fun initView() {
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_list, android.R.layout.simple_list_item_1)
        val listView = findViewById<ListView>(R.id.main_list_view)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent()
            when (position) {

                0 -> intent.setClass(this@MainActivity, OpenGLActivity::class.java)
                1 -> intent.setClass(this@MainActivity, MultiTextureActivity::class.java)
                2 -> intent.setClass(this@MainActivity, CameraActivity::class.java)
                3 -> intent.setClass(this@MainActivity, TransformActivity::class.java)
                4 -> intent.setClass(this@MainActivity, QuickSurfaceActivity::class.java)
            }
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    private fun requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)) {
                Toast.makeText(baseContext, "We need camera to work", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CODE_CAMERA
                )

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CODE_CAMERA
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

}
