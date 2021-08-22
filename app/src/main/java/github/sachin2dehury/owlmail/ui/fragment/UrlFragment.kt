package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.NavGraphDirections
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentUrlBinding
import github.sachin2dehury.owlmail.ui.utils.getThemeColor
import github.sachin2dehury.owlmail.ui.utils.hideKeyBoard

@AndroidEntryPoint
class UrlFragment : Fragment(R.layout.fragment_url) {

    private var _binding: FragmentUrlBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUrlBinding.bind(view)
        setupOnClickListener()
    }

    private fun setupOnClickListener() = binding.apply {
        nextButton.setOnClickListener {
            updateUrl()
        }
        privacyPolicyButton.setOnClickListener {
            findNavController().navigate(NavGraphDirections.actionToWebViewFragment(getString(R.string.privacy_policy)))
        }
        urlEditText.doOnTextChanged { _, _, _, _ ->
            urlTextBox.error = null
            urlEditText.setTextColor(urlEditText.getThemeColor(R.attr.colorTextPrimary))
        }
    }

    private fun updateUrl() = binding.apply {
        urlEditText.text?.toString()?.trim()?.let { url ->
            if (URLUtil.isValidUrl(url)) {
                root.hideKeyBoard()
                findNavController().navigate(NavGraphDirections.actionToAuthFragment(url))
            } else {
                urlEditText.setTextColor(urlEditText.getThemeColor(R.attr.colorRed))
                urlTextBox.error = "Enter a valid url!"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.nextButton.setOnClickListener(null)
        binding.privacyPolicyButton.setOnClickListener(null)
        _binding = null
    }
}