package com.emrizkis.zwallet.ui.layout.main.profile.changepin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentNewPin1Binding
import com.emrizkis.zwallet.databinding.FragmentNewPin2Binding
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import java.net.HttpURLConnection


class NewPin2Fragment : Fragment() {
    var pinInput  = mutableListOf<EditText>()
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var binding: FragmentNewPin2Binding
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = FragmentNewPin2Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.inputRegisterPin6.addTextChangedListener{
            if (binding.inputRegisterPin6.text.length ==  0 ){
                binding.inputRegisterPin6.setBackgroundResource(R.drawable.background_pin_input)

            } else {
                binding.inputRegisterPin6.setBackgroundResource(R.drawable.background_pin_input_filled)
            }
        }

        binding.btnSubmit.setOnClickListener {

            if(binding.inputRegisterPin1.text.isNotEmpty() &&
                binding.inputRegisterPin2.text.isNotEmpty() &&
                binding.inputRegisterPin3.text.isNotEmpty() &&
                binding.inputRegisterPin4.text.isNotEmpty() &&
                binding.inputRegisterPin5.text.isNotEmpty() &&
                binding.inputRegisterPin6.text.isNotEmpty()){

                if (getpin()== viewModel.getPin().value.toString()) {

                    proses()
                }

                else {
                    Toast.makeText(context,"pin not match", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }

        initPin()

    }

    private fun initPin() {
        pinInput.add(0,binding.inputRegisterPin1)
        pinInput.add(1,binding.inputRegisterPin2)
        pinInput.add(2,binding.inputRegisterPin3)
        pinInput.add(3,binding.inputRegisterPin4)
        pinInput.add(4,binding.inputRegisterPin5)
        pinInput.add(5,binding.inputRegisterPin6)
        pinHandler(pinInput)
    }

    private fun pinHandler(pin: List<EditText>) {

        for(i in 0..(pin.size-1)){
            pin[i].apply {
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        TODO("Not yet implemented")
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        TODO("Not yet implemented")
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        if (i < (pin.size-1) && pin[i].text.toString().isNotEmpty()) {
                            pin[i].setBackgroundResource(R.drawable.background_pin_input_filled)
                            pin[i + 1].requestFocus()
                        }

                        if (i==(pin.size-1) && (binding.inputRegisterPin1.text.isNotEmpty() &&
                                    binding.inputRegisterPin2.text.isNotEmpty() &&
                                    binding.inputRegisterPin3.text.isNotEmpty() &&
                                    binding.inputRegisterPin4.text.isNotEmpty() &&
                                    binding.inputRegisterPin5.text.isNotEmpty() &&
                                    binding.inputRegisterPin6.text.isNotEmpty())
                        ){
                            binding.btnSubmit.setBackgroundResource(R.drawable.background_button_rounded_active)
                            binding.btnSubmit.setTextColor(Color.parseColor("#FFFFFF"))


                            if(getpin() == viewModel.getPin().value.toString()){

                                proses()
//                                activity?.finish()

                            } else {
                                Toast.makeText(context,"pin not match", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }

                    }

                })

                setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
                    if (event.action !== KeyEvent.ACTION_DOWN) {
                        return@OnKeyListener false //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                    }
                    if (keyCode == KeyEvent.KEYCODE_DEL && pin[i].text.toString().isEmpty() && i != 0) {
                        //this condition is to handel the delete input by users.
                        if(i ==(pin.size)){
                            pin[i+1].setBackgroundResource(R.drawable.background_pin_input)
                            pin[i - 1].setBackgroundResource(R.drawable.background_pin_input)
                        } else {
                            pin[i - 1].setBackgroundResource(R.drawable.background_pin_input)
                        }
                        pin[i - 1].setText("") //Deletes the digit of pin
                        pin[i - 1].requestFocus()
                        //and sets the focus on previous digit
                    }
                    false
                })
            }
        }
    }

    fun getpin():String{
        return pinInput[0].text.toString()+
                pinInput[1].text.toString()+
                pinInput[2].text.toString()+
                pinInput[3].text.toString()+
                pinInput[4].text.toString()+
                pinInput[5].text.toString()
    }

    fun proses(){
        val response = viewModel.createPin(getpin())
        response.observe(viewLifecycleOwner){
            when(it.state){
                State.LOADING->{
                    loadingDialog.start("Processing your request")
                }
                State.SUCCESS->{
                    if(it.data?.status== HttpURLConnection.HTTP_OK){
                        activity?.finish()
                    }
                }
                State.ERROR->{
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

    }

}