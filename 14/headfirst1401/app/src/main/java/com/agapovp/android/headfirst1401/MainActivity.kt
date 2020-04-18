package com.agapovp.android.headfirst1401

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar as Toolbar)

        ActionBarDrawerToggle(
            this,
            activity_main_drawer_layout,
            activity_main_toolbar as Toolbar,
            R.string.activity_main_nav_open_drawer,
            R.string.activity_main_nav_close_drawer
        ).run {
            activity_main_drawer_layout.addDrawerListener(this)
            syncState()
        }

        activity_main_nav_view.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().run {
            add(R.id.activity_main_content_frame, InboxFragment())
            commit()
        }
    }

    override fun onBackPressed() {
        if (activity_main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            activity_main_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var fragment: Fragment? = null
        var intent: Intent? = null

        when (item.itemId) {
            R.id.nav_drafts -> fragment = DraftsFragment()
            R.id.nav_sent -> fragment = SentItemsFragment()
            R.id.nav_trash -> fragment = TrashFragment()
            R.id.nav_help -> intent = Intent(this, HelpActivity::class.java)
            R.id.nav_feedback -> intent = Intent(this, FeedbackActivity::class.java)
            else -> fragment = InboxFragment()
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.activity_main_content_frame, fragment)
                commit()
            }
        } else {
            startActivity(intent)
        }

        activity_main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
