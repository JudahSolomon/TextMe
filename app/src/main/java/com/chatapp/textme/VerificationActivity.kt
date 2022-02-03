package com.chatapp.textme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.chatapp.textme.databinding.ActivityVerificationBinding
import com.google.android.material.internal.ContextUtils.getActivity
import com.hbb20.CountryCodePicker
import com.hbb20.CountryCodePicker.PhoneNumberValidityChangeListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import okio.Timeout
import java.util.concurrent.TimeUnit


class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding
    private val optIsValid = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>()

    @SuppressLint("ResourceAsColor")

    private lateinit var mAuth: FirebaseAuth
    private lateinit var phoneAuth: PhoneAuthProvider

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification)

        phoneAuth = PhoneAuthProvider.getInstance()
        mAuth = FirebaseAuth.getInstance()

        isValidLiveData.apply {
            this.value = false
            addSource(optIsValid) { opt ->
                val optCode = optIsValid.value
                this.value = isValidOpt(opt)
            }
        }
        binding.enterNumberITL.doOnTextChanged { text, _, _, _ ->
            optIsValid.value = text.toString()
        }
        isValidLiveData.observe(this) { isValid ->
            binding.verifyButton.isEnabled = isValid


        }
    }

    // liveData button validation fun
    private fun isValidOpt(opt: String): Boolean? {
        val isValidCode = opt != null
                && opt.length >= 9
        return isValidCode

    }


}