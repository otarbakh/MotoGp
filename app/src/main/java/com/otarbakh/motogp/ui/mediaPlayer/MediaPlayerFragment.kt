//package com.otarbakh.motogp.ui.mediaPlayer
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.otarbakh.motogp.R
//
//class MediaPlayerFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = MediaPlayerFragment()
//    }
//
//    private lateinit var viewModel: MediaPlayerViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_media_player, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MediaPlayerViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}