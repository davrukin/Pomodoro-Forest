package today.kalendu.pomodoroforest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Chronometer
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var chronometer: Chronometer ?= null
    private var toggleButton: ToggleButton ?= null
    private var textPoints: TextView ?= null
    private var textBreakLength: TextView ?= null
    private var progressWheel: ProgressBar ?= null

    private var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initializeUI()
        setListeners()
    }

    private fun initializeUI() {
        this.chronometer = findViewById(R.id.chronometer)
        this.toggleButton = findViewById(R.id.chronometer_toggle)

        this.textPoints = findViewById(R.id.text_points)
        this.textPoints!!.text = points.toString()

        this.textBreakLength = findViewById(R.id.text_break_length)
        this.textBreakLength!!.visibility = View.INVISIBLE

        this.progressWheel = findViewById(R.id.progress_wheel)
        this.progressWheel!!.visibility = View.INVISIBLE
    }

    private fun setListeners() {
        this.toggleButton!!.setOnClickListener { toggleChronometer() }
    }

    private fun toggleChronometer() {
        if (!this.chronometer!!.isActivated) {
            this.chronometer!!.start()
            this.toggleButton!!.toggle()
            checkPoints(true)
            this.progressWheel!!.visibility = View.VISIBLE
            this.progressWheel!!.isActivated = true
        } else {
            this.chronometer!!.stop()
            this.toggleButton!!.toggle()
            checkPoints(false)
            this.progressWheel!!.visibility = View.INVISIBLE
            this.progressWheel!!.isActivated = false
        }
    }

    private fun checkPoints(enabled: Boolean) {
        this.textBreakLength!!.visibility = View.VISIBLE
        if (this.points < 4) {
            this.textBreakLength!!.text = "Take a short break"
            updatePoints()
        } else {
            this.textBreakLength!!.text = "Take a long break, like rn"
            resetCount()
        }
    }

    private fun updatePoints() {
        this.points++
        this.textPoints!!.text = points.toString()
    }

    private fun resetCount() {
        this.points = 0
        this.textPoints!!.text = points.toString()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate t he menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
