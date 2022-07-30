package github.sachin2dehury.owlmail.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import github.sachin2dehury.owlmail.data.remote.search.Conversation
import github.sachin2dehury.owlmail.databinding.FragmentSearchBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.controller.ZimbraPagingEpoxyController
import github.sachin2dehury.owlmail.viewmodel.SearchTabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchTabFragment :
    Fragment(R.layout.fragment_search),
    EpoxyModelOnClickListener<Conversation> {

    private var _binding: FragmentSearchBinding? = null

    private val viewModel: SearchTabViewModel by viewModels()

    @Inject
    lateinit var controller: ZimbraPagingEpoxyController<Conversation>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view)

        setUpTabData()
        setupRecyclerView()
        setUpOnClickListener()
        subscribeToObserver()
    }

    private fun setUpTabData() = arguments?.run {
        viewModel.zimbraFolder = getParcelable(TAB_DATA) ?: ZimbraFolder.INBOX
    }

    private fun setupRecyclerView() = _binding?.run {
        epoxyRecyclerView.setController(controller)
    }

    private fun setUpOnClickListener() = _binding?.run {
        swipeRefresh.setOnRefreshListener {}
    }

    private fun subscribeToObserver() = lifecycleScope.launch {
        viewModel.getSearchRequestPagingSource(null).collectLatest {
            controller.submitData(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.swipeRefresh?.setOnRefreshListener(null)
        _binding?.epoxyRecyclerView?.clear()
        _binding = null
    }

    companion object {
        private const val TAB_DATA = "tab_data"
        fun newInstance(tab: ZimbraFolder) = SearchTabFragment().apply {
            arguments = bundleOf(TAB_DATA to tab)
        }
    }

    override fun onModelClick(item: Conversation?) {
        Toast.makeText(requireContext(), "${item?.subject}", Toast.LENGTH_LONG).show()
    }
}
