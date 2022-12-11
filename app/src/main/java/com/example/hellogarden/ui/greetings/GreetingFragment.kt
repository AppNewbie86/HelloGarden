package com.example.hellogarden.ui.greetings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hellogarden.MainViewModel
import com.example.hellogarden.databinding.FragmentGreetingBinding

/**
 * Dieses Fragment verwaltet die Anzeige der Lesezeichen
 */

class GreetingFragment : Fragment() {


    /**
     * hier wird die binding Variable deklariert
     */

    private lateinit var binding: FragmentGreetingBinding
    private lateinit var goforwardBtn: Button
    private lateinit var startbackground: ImageView
    private lateinit var welcomegreetingtext: ImageView
    private lateinit var infotextgreeting: ImageView

    /**
     * Hier holt man sich das ViewModel
     */
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
        binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root




    }



    /**
     * Lifecycle Funktion onViewCreated
     * Hier werden die Elemente eingerichtet und z.B. onClickListener gesetzt
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Navigation zu LoginFragment
         */

        binding.goforwardBtn.setOnClickListener {

            findNavController()
                .navigate(GreetingFragmentDirections.actionGreetingFragmentToAppEinleitungsFragment())
        }


    }


}

