package com.example.hellogarden.ui.appeinleitung

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hellogarden.MainViewModel
import com.example.hellogarden.databinding.FragmentAppEinleitungsBinding

class AppEinleitungsFragment : Fragment() {

    /**
     * Hier wird das ViewModel, in dem die Logik stattfindet, geholt
     */

    private val viewModel: MainViewModel by activityViewModels()

    /**
     * Das binding für das QuizFragment wird deklariert
     */

    private lateinit var binding: FragmentAppEinleitungsBinding

    /**
     * Lifecycle Funktion onCreateView
     * Hier wird das binding initialisiert und das Layout gebaut
     */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppEinleitungsBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Lifecycle Funktion onViewCreated
     * Hier werden die Elemente eingerichtet und z.B. onClickListener gesetzt
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loslegenButton.setOnClickListener {
            findNavController()
                .navigate(AppEinleitungsFragmentDirections.actionAppEinleitungsFragmentToSignUpFragment())
        }

        binding.goVideoBtn.setOnClickListener {
            findNavController()
                .navigate(AppEinleitungsFragmentDirections.actionAppEinleitungsFragmentToVideoFragment())
        }
    }
}
