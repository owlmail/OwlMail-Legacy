package github.sachin2dehury.owlmail.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.viewmodel.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        preferenceManager.findPreference<Preference>(getString(R.string.logout))?.apply {
            setOnPreferenceClickListener {
//                val navOptions = NavOptions.Builder()
//                    .setPopUpTo(R.id.settingsFragment, true)
//                    .build()
//                findNavController().navigate(NavGraphDirections.actionToUrlFragment(), navOptions)
                true
            }
        }

        preferenceManager.findPreference<SwitchPreferenceCompat>("Theme")?.apply {
            setDefaultValue(viewModel.darkThemeState.value)
            setOnPreferenceChangeListener { _, value ->
                viewModel.saveDarkThemeState(value as Boolean).invokeOnCompletion {
                }
                true
            }
        }


        preferenceManager.findPreference<SwitchPreferenceCompat>("Sync")?.apply {
            setDefaultValue(viewModel.syncState.value)
            setOnPreferenceChangeListener { _, value ->
                viewModel.saveSyncState(value as Boolean).invokeOnCompletion {
                }
                true
            }
        }

        preferenceManager.findPreference<SwitchPreferenceCompat>("Analytics")?.apply {
            setDefaultValue(viewModel.analyticsState.value)
            setOnPreferenceChangeListener { _, value ->
                viewModel.saveAnalyticsState(value as Boolean).invokeOnCompletion {
                }
                true
            }
        }
    }

    private fun subscribeToObservers() = lifecycleScope.launch {
        viewModel.analyticsState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
        viewModel.darkThemeState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
        viewModel.syncState.collectLatest { result ->
            when (result) {
                is ResultState.Success<*> -> {
                }
                is ResultState.Error -> {
                }
                is ResultState.Loading -> {
                }
            }
        }
    }
}