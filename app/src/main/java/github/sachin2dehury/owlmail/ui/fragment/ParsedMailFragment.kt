package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.databinding.FragmentParsedMailBinding
import github.sachin2dehury.owlmail.viewmodel.ParsedMailViewModel
import kotlinx.coroutines.Job


@AndroidEntryPoint
class ParsedMailFragment : Fragment(R.layout.fragment_parsed_mail) {

    private var _binding: FragmentParsedMailBinding? = null
    private val binding: FragmentParsedMailBinding get() = _binding!!

    private val viewModel: ParsedMailViewModel by viewModels()

//    private val args: MailItemsFragmentArgs by navArgs()

    private var job: Job? = null

//    @Inject
//    lateinit var mailItemsAdapter: MailItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentParsedMailBinding.bind(view)

//        setupRecyclerView()
//        setContent()
//
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            getJob()
//        }
    }

//    private fun setupRecyclerView() = binding.recyclerViewMailBox.apply {
//        mailItemsAdapter.view = binding.textViewMailSubject
//        mailItemsAdapter.id = args.id
//        mailItemsAdapter.token = viewModel.getToken().substringAfter('=')
//        mailItemsAdapter.css =
//            requireContext().assets.open("Font").bufferedReader().use { it.readText() }
//        mailItemsAdapter.setupOnItemClickListener { link ->
//            findNavController().navigate(NavGraphDirections.actionToComposeFragment(link))
//        }
//        adapter = mailItemsAdapter
//        layoutManager = LinearLayoutManager(context)
//    }

//    private fun getJob() {
//        job?.cancel()
//        job = lifecycleScope.launch {
//            viewModel.getParsedMails(args.conversationId).collectLatest {
//                mailItemsAdapter.submitData(it)
//            }
//        }
//    }
//
//
//    private fun setContent() {
//        lifecycleScope.launchWhenCreated {
//            mailItemsAdapter.loadStateFlow.collectLatest { loadStates ->
//                binding.swipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
//            }
//        }
//        getJob()
//        mailItemsAdapter.addLoadStateListener { loadState ->
//            val errorState = loadState.source.append as? LoadState.Error
//                ?: loadState.source.prepend as? LoadState.Error
//                ?: loadState.append as? LoadState.Error
//                ?: loadState.prepend as? LoadState.Error
//            errorState?.let {
//                it.error.message?.let { message -> binding.root.showSnackbar(message) }
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}