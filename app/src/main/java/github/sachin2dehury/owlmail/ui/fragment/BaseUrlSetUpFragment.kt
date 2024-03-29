package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.NavGraphDirections
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentBaseUrlSetUpBinding
import github.sachin2dehury.owlmail.ui.utils.getThemeColor
import github.sachin2dehury.owlmail.ui.utils.hideKeyBoard
import github.sachin2dehury.owlmail.viewmodel.BaseUrlSetUpViewModel

@AndroidEntryPoint
class BaseUrlSetUpFragment : Fragment(R.layout.fragment_base_url_set_up) {

    private var _binding: FragmentBaseUrlSetUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BaseUrlSetUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBaseUrlSetUpBinding.bind(view)
        viewModel.resetBaseURL()
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
        urlEditText.text?.toString()?.trim()?.let { baseURL ->
            if (URLUtil.isValidUrl(baseURL)) {
                viewModel.saveBaseURL(baseURL)
                root.hideKeyBoard()
                findNavController().navigate(NavGraphDirections.actionToAuthFragment(baseURL))
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