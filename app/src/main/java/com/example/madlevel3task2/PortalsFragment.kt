package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter =
        PortalAdapter(portals, { portalItem: Portal -> portalItemClicked(portalItem) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddReminderResult()
    }

    private fun initViews() {
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter
        rvPortals.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            var portalTitle = ""
            var portalUrl = ""

            bundle.getString(BUNDLE_PORTAL_TITLE_KEY)?.let {
                portalTitle = it
            } ?: Log.e("PortalFragment", "Request triggered, but empty portal title text!")

            bundle.getString(BUNDLE_PORTAL_URL_KEY)?.let {
                portalUrl = it
            } ?: Log.e("PortalFragment", "Request triggered, but empty portal url text!")

            println("Title: $portalTitle")
            println("Url: $portalUrl")

            val portal = Portal(portalTitle, portalUrl)

            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        }
    }

    // Launches the URL in a new window
    private fun portalItemClicked(portalItem: Portal) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(portalItem.portalUrl)) }
    }
}