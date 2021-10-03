package com.example.catsimage.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import com.example.catsimage.databinding.FragmentMainBinding
import com.example.catsimage.ui.viewModel.CatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), CatsPhotoAdapter.onItemClickListener {
    private val viewModel by viewModels<CatsViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.setHasFixedSize(true)
        }

        binding.recyclerView.apply {
            this.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            val adapter = CatsPhotoAdapter(this@MainFragment)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.flow.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(photo: CatsPhoto) {
        val action = MainFragmentDirections.actionMainFragment2ToDetailsFragment(photo)
        findNavController().navigate(action)
    }
}
