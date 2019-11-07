package com.example.githubapisample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.githubapisample.R
import com.example.githubapisample.di.Injectable
import com.example.githubapisample.ui.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_repo_search.*
import javax.inject.Inject

class RepoSearchFragment : Fragment(), Injectable {

    // TODO: ROOM
    // TODO: DATA BINDING
    // TODO: PAGING
    // TODO: TESTING

    @Inject
    lateinit var viewModel: RepoSearchViewModel

    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_repo_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_edit_text.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(search_edit_text.text.toString())
                search_edit_text.hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        repoAdapter = RepoAdapter().also { repo_list.adapter = it }
        viewModel.repoList.observe(this, repoAdapter::updateData)
    }
}
