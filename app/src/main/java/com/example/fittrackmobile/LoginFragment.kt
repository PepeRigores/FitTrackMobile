package com.example.fittrackmobile

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fittrackmobile.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIniciarSesion.setOnClickListener {
            if (validarFormulario()) {
                val email = binding.etEmail.text?.toString()?.trim().orEmpty()
                Toast.makeText(
                    requireContext(),
                    "Sesión iniciada con éxito para $email",
                    Toast.LENGTH_SHORT
                ).show()
                // Aquí se procesaría el inicio de sesión real
            }
        }

        binding.tvRecuperarPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recuperarPasswordFragment)
        }

        binding.tvIrARegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }
    }

    private fun validarFormulario(): Boolean {
        var esValido = true
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val password = binding.etPassword.text?.toString()?.trim().orEmpty()

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = getString(R.string.error_email_requerido)
            esValido = false
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty() || password.length < 6) {
            binding.tilPassword.error = getString(R.string.error_password_requerida)
            esValido = false
        } else {
            binding.tilPassword.error = null
        }

        return esValido
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}