package com.example.hellogarden.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hellogarden.MainViewModel
import com.example.hellogarden.databinding.FragmentDetailGardenBinding

class DetailGardenFragment : Fragment() {

    private lateinit var binding: FragmentDetailGardenBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailGardenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forwardBtn.setOnClickListener {
            findNavController()
                .navigate(DetailGardenFragmentDirections.actionDetailGardenFragmentToRentFragment())
        }


    }
}
