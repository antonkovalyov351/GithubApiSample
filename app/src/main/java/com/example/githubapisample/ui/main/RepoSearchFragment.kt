package com.example.githubapisample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.githubapisample.databinding.FragmentRepoSearchBinding
import com.example.githubapisample.di.Injectable
import com.example.githubapisample.ui.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_repo_search.*
import timber.log.Timber
import javax.inject.Inject

class RepoSearchFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModel: RepoSearchViewModel

    private lateinit var binding: FragmentRepoSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRepoSearchBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchView()
    }

    private fun initRecyclerView() {
        val repoAdapter = RepoAdapter().also { binding.repoList.adapter = it }
        viewModel.searchResult.observe(this, repoAdapter::swapData)
        viewModel.loading.observe(this) {
            Timber.d("loading = $it")
            binding.loadingProgress.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun initSearchView() {
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchEditText.text.toString()
                viewModel.search(query)
                search_edit_text.hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}
