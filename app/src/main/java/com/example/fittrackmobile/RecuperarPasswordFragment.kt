package com.example.fittrackmobile

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fittrackmobile.databinding.FragmentRecuperarPasswordBinding

class RecuperarPasswordFragment : Fragment() {

    private var _binding: FragmentRecuperarPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecuperarPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviar.setOnClickListener {
            if (validarFormulario()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.instrucciones_enviadas),
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_recuperarPasswordFragment_to_loginFragment)
            }
        }

        binding.btnVolverLogin.setOnClickListener {
            findNavController().navigate(R.id.action_recuperarPasswordFragment_to_loginFragment)
        }
    }

    private fun validarFormulario(): Boolean {
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()

        return if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = getString(R.string.error_email_requerido)
            false
        } else {
            binding.tilEmail.error = null
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}