package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentMailBoxBinding
import github.sachin2dehury.owlmail.viewmodel.MailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MailFragment : Fragment(R.layout.fragment_mail_box) {

    private var _binding: FragmentMailBoxBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MailViewModel by viewModels()

//    private val controller = MailBoxController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMailBoxBinding.bind(view)

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
            it.map { conv ->
                Log.w("sachin", "$conv")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.searchBar).actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.isSubmitButtonEnabled = true
        searchView.setOnCloseListener {
            false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String) = false
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.swipeRefresh.setOnRefreshListener(null)
        binding.epoxyRecyclerView.clear()
        _binding = null
    }
}