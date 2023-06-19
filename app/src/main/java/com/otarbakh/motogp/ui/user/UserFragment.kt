package com.otarbakh.motogp.ui.user

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private lateinit var mauth: FirebaseAuth

    override fun viewCreated() {
        mauth = Firebase.auth
        val user = mauth.currentUser
        if (user != null) {
            binding.tvUsersName.text = "hello dear \n${mauth.currentUser?.email}"
        }
        changeButton()
    }

    override fun listeners() {
        logOut()
    }


    private fun changeButton() {
        val user = mauth.currentUser
        if (user == null) {
            binding.logoutbutton.visibility = View.GONE
            binding.tvLogin.visibility = View.VISIBLE
        } else {
            binding.logoutbutton.visibility = View.VISIBLE
            binding.tvLogin.visibility = View.GONE
        }
    }

    private fun logOut() {
        binding.logoutbutton.setOnClickListener {
            mauth.signOut()
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToLoginFragment())
            checkLoggedInState()
        }
    }

    private fun checkLoggedInState() {
        val user = mauth.currentUser
        if (user == null) {
            binding.tvUsersName.text = ""
        } else {
            binding.tvUsersName.text = "hello  dear" + "  " + mauth.currentUser?.email.toString()
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT)
                .show()
        }
    }
}