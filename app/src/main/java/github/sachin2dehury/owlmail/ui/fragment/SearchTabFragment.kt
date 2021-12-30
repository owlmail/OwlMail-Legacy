package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentSearchBinding
import github.sachin2dehury.owlmail.viewmodel.MailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchTabFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MailViewModel by viewModels()

//    private val controller = ZimbraPagingEpoxyController(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view)

//        setupRecyclerView()
        setUpOnClickListener()
        subscribeToObserver()
    }

    private fun setUpOnClickListener() {
        binding.swipeRefresh.setOnRefreshListener {
        }
    }

//    private fun setupRecyclerView() = binding.epoxyRecyclerView.setController(controller)

    private fun subscribeToObserver() = lifecycleScope.launch {
        viewModel.getSearchRequestPagingSource("in:inbox").collectLatest {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.swipeRefresh.setOnRefreshListener(null)
        binding.epoxyRecyclerView.clear()
        _binding = null
    }
}