package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentUrlBinding

@AndroidEntryPoint
class UrlFragment : Fragment(R.layout.fragment_url) {

    private var _binding: FragmentUrlBinding? = null
    private val binding: FragmentUrlBinding get() = _binding!!

    private var url: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUrlBinding.bind(view)

//        binding.buttonGo.setOnClickListener {
//            binding.editTextUrl.text?.let { url ->
//                this.url = url.toString()
//                if (URLUtil.isValidUrl(this.url)) {
//                    binding.root.hideKeyBoard()
//                    findNavController().navigate(NavGraphDirections.actionToAuthFragment(this.url!!))
//                } else {
//                    it?.showSnackbar("Invalid Url!")
//                }
//            }
//        }
//
//        binding.buttonPrivacyPolicy.setOnClickListener {
//            findNavController().navigate(NavGraphDirections.actionToWebViewFragment(getString(R.string.privacy_policy)))
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}