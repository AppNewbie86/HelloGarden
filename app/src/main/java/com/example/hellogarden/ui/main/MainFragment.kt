package com.example.hellogarden.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hellogarden.MainViewModel
import com.example.hellogarden.databinding.FragmentMainBinding


/**
 * Das MainFragment ist der Begrüßungsscreen unserer App
 * sollte kein User eingeloggt sein wird man automatisch zum Login weitergeleitet
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var rentGardenBtn: Button
    private lateinit var galleryBtn: Button
    private lateinit var myOffersBtn: Button
    private lateinit var profilBtn: Button



    private val viewModel: MainViewModel by activityViewModels()

    /**
     * Lifecycle Funktion onCreateView
     * Hier wird das binding initialisiert und das Layout gebaut
     */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rentgardenBtn.setOnClickListener {
            findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToDetailGardenFragment())
        }

         binding.profilBtn.setOnClickListener {
             findNavController()
                 .navigate(MainFragmentDirections.actionMainFragmentToProfilFragment())
         }

        binding.galleryBtn.setOnClickListener {
            findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToGalleryFragment())
        }




    }
}

