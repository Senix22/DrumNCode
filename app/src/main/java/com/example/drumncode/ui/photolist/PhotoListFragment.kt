package com.example.drumncode.ui.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drumncode.R
import com.example.drumncode.common.State
import com.example.drumncode.common.show
import com.example.drumncode.databinding.PhotoListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PhotoListFragment : Fragment() {

    private val viewModel: PhotoListViewModel by viewModels()
    private lateinit var binding: PhotoListFragmentBinding

    private var adapter: PhotoListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = PhotoListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.savedMovie.collect { state ->
                    when (state) {
                        is State.Content -> {
                            binding.rvSubject.show()
                            adapter?.submitList(state.data)
                        }

                        State.Empty -> {}
                        is State.Error -> {

                        }

                        State.Loading -> {
                            binding.pBar.show()
                        }
                    }

                }
            }
        }
    }

    private fun initView() {
        adapter = PhotoListAdapter { current, previous, next ->
            openTrailer(current, next.orEmpty(), previous.orEmpty())
        }
        binding.rvSubject.adapter = adapter
        binding.rvSubject.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun openTrailer(currentID: String, nextId: String, prevId: String) {
        viewModel.getDetails(currentID, prevId, nextId)
        val bundle = bundleOf("currentID" to currentID, )
        findNavController().navigate(R.id.action_show_movie, bundle)
    }

}