package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.data.constants.ZimbraFolder
import github.sachin2dehury.owlmail.data.search.Conversation
import github.sachin2dehury.owlmail.databinding.FragmentSearchBinding
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.controller.ZimbraPagingEpoxyController
import github.sachin2dehury.owlmail.viewmodel.SearchTabViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchTabFragment(private val tab: ZimbraFolder) : Fragment(R.layout.fragment_search),
    EpoxyModelOnClickListener<Conversation> {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchTabViewModel by viewModels()

    private val controller = ZimbraPagingEpoxyController<Conversation>(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view)

        setupRecyclerView()
        setUpOnClickListener()
        subscribeToObserver()
    }

    private fun setUpOnClickListener() {
        binding.swipeRefresh.setOnRefreshListener {
        }
    }

    private fun setupRecyclerView() = binding.epoxyRecyclerView.setController(controller)

    private fun subscribeToObserver() = lifecycleScope.launch {
        viewModel.getSearchRequestPagingSource("in:${tab.value}").collectLatest {
            controller.submitData(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.swipeRefresh.setOnRefreshListener(null)
        binding.epoxyRecyclerView.clear()
        _binding = null
    }

    override fun onItemClick(item: Conversation?) {

    }
}