package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.insertSeparators
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.NavGraphDirections
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.AUTH_FROM_COOKIE
import github.sachin2dehury.owlmail.api.BASE_URL
import github.sachin2dehury.owlmail.api.COMPOSE_MAIL
import github.sachin2dehury.owlmail.api.MOBILE_URL
import github.sachin2dehury.owlmail.databinding.FragmentMailBoxBinding
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.viewmodel.MailBoxViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MailBoxFragment : Fragment(R.layout.fragment_mail_box) {

    private var _binding: FragmentMailBoxBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MailBoxViewModel by activityViewModels()

    private val args: MailBoxFragmentArgs by navArgs()

//    @Inject
//    lateinit var mailBoxAdapter: MailBoxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMailBoxBinding.bind(view)

//        setupAdapter()
        setupRecyclerView()
        setContent()


    }

    private fun setUpOnClickListener() {
        binding.swipeRefresh.setOnRefreshListener {
            getJob()
        }
        binding.composeButton.setOnClickListener {
            findNavController().navigate(
                NavGraphDirections.actionToComposeFragment(
                    BASE_URL + MOBILE_URL + AUTH_FROM_COOKIE + COMPOSE_MAIL
                )
            )
        }
    }

//    private fun setupAdapter() = mailBoxAdapter.setupOnItemClickListener {
//        findNavController().navigate(
//            NavGraphDirections.actionToMailItemsFragment(it.conversationId, it.id)
//        )
//    }

    private fun setupRecyclerView() = binding.epoxyRecyclerView.apply {
//        adapter = mailBoxAdapter
//        layoutManager = LinearLayoutManager(context)
    }

    private fun getJob(query: String = args.request) {
//        job?.cancel()
//        job = lifecycleScope.launch {
//            viewModel.getMails(query).collectLatest {
//                mailBoxAdapter.submitData(it)
//            }
//        }
    }

    private fun subscribeToObserver() = lifecycleScope.launch {
        viewModel.getMails("").collectLatest {
            it.insertSeparators { mail: Mail?, mail2: Mail? ->

            }
        }
    }

    private fun setContent() {
        lifecycleScope.launchWhenCreated {
//            mailBoxAdapter.loadStateFlow.collectLatest { loadStates ->
//                binding.swipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
//            }
        }
        getJob()
//        mailBoxAdapter.addLoadStateListener { loadState ->
//            val errorState = loadState.source.append as? LoadState.Error
//                ?: loadState.source.prepend as? LoadState.Error
//                ?: loadState.append as? LoadState.Error
//                ?: loadState.prepend as? LoadState.Error
//            errorState?.let {
//                it.error.message?.let { message -> binding.root.showSnackbar(message) }
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.searchBar).actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.isSubmitButtonEnabled = true
        searchView.setOnCloseListener {
            getJob()
            false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getJob(query)
                return true
            }

            override fun onQueryTextChange(query: String) = false
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.swipeRefresh.setOnRefreshListener(null)
        binding.composeButton.setOnClickListener(null)
        _binding = null
    }
}