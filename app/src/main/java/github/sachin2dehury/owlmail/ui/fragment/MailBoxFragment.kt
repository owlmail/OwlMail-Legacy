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
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.NavGraphDirections
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.AUTH_FROM_COOKIE
import github.sachin2dehury.owlmail.api.BASE_URL
import github.sachin2dehury.owlmail.api.COMPOSE_MAIL
import github.sachin2dehury.owlmail.api.MOBILE_URL
import github.sachin2dehury.owlmail.databinding.FragmentMailBoxBinding
import github.sachin2dehury.owlmail.epoxy.controller.MailBoxController
import github.sachin2dehury.owlmail.viewmodel.MailBoxViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MailBoxFragment : Fragment(R.layout.fragment_mail_box) {

    private var _binding: FragmentMailBoxBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MailBoxViewModel by activityViewModels()

    private val args: MailBoxFragmentArgs by navArgs()

    @Inject
    lateinit var controller: MailBoxController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMailBoxBinding.bind(view)

        setupRecyclerView()
        setUpOnClickListener()
        subscribeToObserver()
    }

    private fun setUpOnClickListener() {
        binding.swipeRefresh.setOnRefreshListener {

        }
        binding.composeButton.setOnClickListener {
            findNavController().navigate(
                NavGraphDirections.actionToComposeFragment(
                    BASE_URL + MOBILE_URL + AUTH_FROM_COOKIE + COMPOSE_MAIL
                )
            )
        }
    }

    private fun setupRecyclerView() = binding.epoxyRecyclerView.setController(controller)

    private fun subscribeToObserver() = lifecycleScope.launch {
        viewModel.getMails(args.request).collectLatest {
            controller.submitData(it)
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
        binding.composeButton.setOnClickListener(null)
        _binding = null
    }
}