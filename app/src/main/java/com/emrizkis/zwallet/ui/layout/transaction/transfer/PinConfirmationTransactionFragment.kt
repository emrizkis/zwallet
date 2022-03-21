package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentPinConfirmationTransactionBinding
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

class PinConfirmationTransactionFragment : Fragment() {

    private lateinit var binding: FragmentPinConfirmationTransactionBinding
    var pinInput  = mutableListOf<EditText>()
    private val viewModel: TransferViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPinConfirmationTransactionBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        agar scrollview jalan
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        initPin(view)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun initPin(view: View) {
        pinInput.add(0,binding.inputRegisterPin1)
        pinInput.add(1,binding.inputRegisterPin2)
        pinInput.add(2,binding.inputRegisterPin3)
        pinInput.add(3,binding.inputRegisterPin4)
        pinInput.add(4,binding.inputRegisterPin5)
        pinInput.add(5,binding.inputRegisterPin6)
        pinHandler(pinInput, view)
    }

    private fun pinHandler(pin: List<EditText>, view: View) {
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
                            pin[i + 1].requestFocus()
                        }

                        if (i==(pin.size-1) && (binding.inputRegisterPin1.text.isNotEmpty() &&
                                    binding.inputRegisterPin2.text.isNotEmpty() &&
                                    binding.inputRegisterPin3.text.isNotEmpty() &&
                                    binding.inputRegisterPin4.text.isNotEmpty() &&
                                    binding.inputRegisterPin5.text.isNotEmpty() &&
                                    binding.inputRegisterPin6.text.isNotEmpty())
                        ){
                            val response = viewModel.transferAmount(getpin())
                            response.observe(viewLifecycleOwner){
                                when(it.state){
                                    State.LOADING->{
                                        loadingDialog.start("Processing your request")
                                    }
                                    State.SUCCESS->{
                                        if(it.data?.status == HttpURLConnection.HTTP_OK){
                                            Navigation.findNavController(view).navigate(R.id.action_pinConfirmationTransactionFragment_to_successTransactionFragment)
                                        }
                                    }
                                    State.ERROR->{
                                        Navigation.findNavController(view).navigate(R.id.action_pinConfirmationTransactionFragment_to_failedTransactionFragment)
                                    }
                                }
                            }
//

                        }

                    }

                })

                setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
                    if (event.action !== KeyEvent.ACTION_DOWN) {
                        return@OnKeyListener false //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                    }
                    if (keyCode == KeyEvent.KEYCODE_DEL && pin[i].text.toString().isEmpty() && i != 0) {
                        //this condition is to handel the delete input by users.
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

}