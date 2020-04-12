package com.agapovp.android.headfirst1301

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var shareActionProvider: ShareActionProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar as Toolbar)
        activity_main_pager.adapter = SectionsPagerAdapter(supportFragmentManager)
        activity_main_tabs.setupWithViewPager(activity_main_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.action_share)
        shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider
        setShareActionIntent(getString(R.string.share_intent_text))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_order -> {
                startActivity(
                    Intent(this, OrderActivity::class.java)
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setShareActionIntent(text: String) {
        shareActionProvider.setShareIntent(
            Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, text)
        )
    }

    private inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int) = when (position) {
            0 -> TopFragment()
            1 -> PizzaFragment()
            2 -> PastaFragment()
            3 -> StoresFragment()
            else -> null
        }

        override fun getCount(): Int {
            return PAGE_COUNT
        }

        override fun getPageTitle(position: Int) = when (position) {
            0 -> resources.getText(R.string.tab_home_title)
            1 -> resources.getText(R.string.tab_pizza_title)
            2 -> resources.getText(R.string.tab_pasta_title)
            3 -> resources.getText(R.string.tab_store_title)
            else -> null
        }
    }

    companion object {
        const val PAGE_COUNT = 4
    }
}
