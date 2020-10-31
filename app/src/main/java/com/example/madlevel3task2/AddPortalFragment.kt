package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_TITLE_KEY = "bundle_portal_title"
const val BUNDLE_PORTAL_URL_KEY = "bundle_portal_url"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPortal.setOnClickListener {
            onAddReminder()
        }
    }

    private fun onAddReminder() {
        val portalTitle = etTitleName.text.toString()
        val portalUrl = etUrlName.text.toString()

        if (portalTitle.isNotBlank() && portalUrl.isNotBlank()) {
            setFragmentResult(
                REQ_PORTAL_KEY, bundleOf(
                    Pair(BUNDLE_PORTAL_TITLE_KEY, portalTitle),
                    Pair(BUNDLE_PORTAL_URL_KEY, portalUrl)
                )
            )

            findNavController().popBackStack()
        } else {
            Toast.makeText(activity, R.string.not_valid_portal, Toast.LENGTH_LONG).show()
        }
    }
}