package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentWebViewBinding
import github.sachin2dehury.owlmail.viewmodel.WebPageViewModel

@AndroidEntryPoint
class WebPageFragment : Fragment(R.layout.fragment_web_view) {

    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding get() = _binding!!

    private val viewModel: WebPageViewModel by viewModels()

//    private val args: WebPageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWebViewBinding.bind(view)

        setContent()

        binding.swipeRefresh.setOnRefreshListener {
            setContent()
        }
    }

    private fun setContent() {
//        val url = ApiConstants.BASE_URL + args.url.replace("auth=co&disp=a", "disp=i")
//        binding.swipeRefreshLayout.isRefreshing = true
//        binding.webView.apply {
//            webChromeClient = chromeClient
//            settings.loadsImagesAutomatically = true
//            settings.setSupportZoom(true)
//            setInitialScale(100)
//            loadUrl(url + ApiConstants.AUTH_FROM_TOKEN + viewModel.token.substringAfter('='))
//            zoomOut()
//        }
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}