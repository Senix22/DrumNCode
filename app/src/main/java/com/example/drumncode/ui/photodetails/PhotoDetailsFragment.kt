package com.example.drumncode.ui.photodetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.drumncode.R
import com.example.drumncode.common.State
import com.example.drumncode.common.hide
import com.example.drumncode.common.show
import com.example.drumncode.data.models.PhotoDetailsEntity
import com.example.drumncode.databinding.PhotoDetailsLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment() {
    private lateinit var binding: PhotoDetailsLayoutBinding
    private val detailsViewModel: DetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            detailsViewModel.deleteList()
            findNavController().popBackStack()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = PhotoDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentID = arguments?.getString("currentID") ?: 0L
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.state.collect {
                    when (it) {
                        is State.Content -> {
                            binding.pBar.hide()
                            detailsViewModel.photoList.collect { entity ->
                                entity?.let { it1 -> setupViewPager2(it1, currentID.toString()) }
                            }
                        }

                        State.Empty -> {

                        }

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

    private fun setupViewPager2(list: List<PhotoDetailsEntity>, currentId: String) {
        binding.viewPager.adapter = ViewPagerAdapter(list)
        val item = list.indices.find { list[it].id == currentId }
        binding.viewPager.setCurrentItem(item ?: 1, false)
        val offsetPx = resources.getDimension(R.dimen.dp_5).toInt()
        binding.viewPager.setPadding(offsetPx, 0, offsetPx, 0)

        val pageMarginPx = resources.getDimension(R.dimen.dp_5).toInt()
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        binding.viewPager.setPageTransformer(marginTransformer)

    }

    override fun onStop() {
        super.onStop()
        detailsViewModel.deleteList()
    }

}