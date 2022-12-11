package com.example.hellogarden.ui.video

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.activityViewModels
import com.example.hellogarden.MainViewModel
import com.example.hellogarden.R
import com.example.hellogarden.databinding.FragmentVideoBinding


class VideoFragment : Fragment() {


    var simpleVideoView: VideoView? = null

    // declaring a null variable for MediaController
    var mediaControls: MediaController? = null


    /**
     * ViewModel wird verknüpft
     */

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentVideoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }


    /**
     * Layout wird vorbereitet zum Erstellen aufgeblasen
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        simpleVideoView = binding.simpleVideoView
        if (mediaControls == null) {


            /**
             * bei Fragments benutzt man requireContext
             * creating an object of media controller class
             */

            mediaControls = MediaController(requireContext())

            /**
             *  set the anchor view for the video view
             */

            mediaControls!!.setAnchorView(this.simpleVideoView)
        }

        /**
         *  set the media controller for video view
         */


        simpleVideoView!!.setMediaController(mediaControls)

        /**
         * set the absolute path of the video file which is going to be played
         */

        simpleVideoView!!.setVideoURI(
            Uri.parse(
                "android.resource://"
                        + requireContext().packageName + "/" + R.raw.garten
            )
        )


        simpleVideoView!!.requestFocus()

        /**
         *  starting the video
         */

        simpleVideoView!!.start()


        /**
         * display a toast message
         * after the video is completed
         */

        simpleVideoView!!.setOnCompletionListener {
            Toast.makeText(
                requireContext(), "Video completed",
                Toast.LENGTH_LONG
            ).show()

        }

        /**
         * display a toast message if any
         * error occurs while playing the video
         */

        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(
                requireContext(), "An Error Occurred " +
                        "While Playing Video !!!", Toast.LENGTH_LONG
            ).show()
            false
        }


    }

}
