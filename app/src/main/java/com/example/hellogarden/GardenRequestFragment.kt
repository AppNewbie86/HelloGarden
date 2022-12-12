package com.example.hellogarden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.hellogarden.adapter.ProductAdapter
import com.example.hellogarden.databinding.FragmentGardenRequestBinding

class GardenRequestFragment : Fragment() {

    private lateinit var binding: FragmentGardenRequestBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenRequestBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productAdapter = ProductAdapter()
        binding.productRecycler.adapter = productAdapter

        viewModel.product.observe(
            viewLifecycleOwner,
            Observer {
                // immer wenn neue products kommen recyclerview updaten
                productAdapter.submitList(it)
            }
        )
    }
}
