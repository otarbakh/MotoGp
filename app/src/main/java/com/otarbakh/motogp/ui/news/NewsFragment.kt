package com.otarbakh.motogp.ui.news

import android.content.Intent
import android.net.Uri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentNewsBinding
import com.otarbakh.motogp.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class NewsFragment :
    BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    private val newsVM: NewsViewModel by viewModels()
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }


    override fun viewCreated() {
//        setHasOptionsMenu(true)
        setupRecycler()
        observe()
    }

    override fun listeners() {
        share()
        gotoLink()
        search()
    }

    private fun setupRecycler() {
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun observe() {
        lifecycleScope.launch{
            newsVM.news.collectLatest {
                newsAdapter.submitData(it)
            }
        }
    }

    private fun share() {
        newsAdapter.apply {
            setOnShareClickListener { ticket, _ ->
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${ticket.url}"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun gotoLink() {
        newsAdapter.setOnGotoClickListener { article, _ ->
            val uri: Uri = Uri.parse(article.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun search() {
        binding.etSearchImpl.addTextChangedListener { editable ->
            if (editable!!.toString().isNotEmpty()) {
                newsVM.searchNews(editable.toString())
            }
        }
    }
}