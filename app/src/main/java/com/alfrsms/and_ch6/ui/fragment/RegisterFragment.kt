package com.alfrsms.and_ch6.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alfrsms.and_ch6.R
import com.alfrsms.and_ch6.databinding.FragmentRegisterBinding
import com.alfrsms.and_ch6.ui.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel : RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener{ toCreateAccount() }
    }

    private fun toCreateAccount() {
        val username = binding.etRegisterUsername.text.toString()
        val email = binding.etRegisterEmail.text.toString()
        val password = binding.etRegisterPassword.text.toString()
        val confirmPassword = binding.etRegisterConfirmPassword.text.toString()

        if (password == confirmPassword) {
            registerViewModel.saveAccount(username, password, email)
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        } else {
            Toast.makeText(requireContext(), "Password not correct", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}