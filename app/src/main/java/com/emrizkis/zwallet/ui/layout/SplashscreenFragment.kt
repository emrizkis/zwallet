package com.emrizkis.zwallet.ui.layout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentPinAuthBinding
import com.emrizkis.zwallet.databinding.FragmentSplashscreenBinding
import com.emrizkis.zwallet.model.request.RefreshTokenRequest
import com.emrizkis.zwallet.ui.layout.auth.AuthActivity
import com.emrizkis.zwallet.ui.layout.auth.pinauth.PinAuthActivity
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
import com.emrizkis.zwallet.utils.*
import java.net.HttpURLConnection

class SplashscreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashscreenBinding
    private lateinit var prefs: SharedPreferences
    private val viewModel: SplashScreenViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentSplashscreenBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!

        if (prefs.getBoolean(KEY_LOGGED_IN, false)){


            val request = RefreshTokenRequest(
                prefs.getString(KEY_USER_EMAIL, "")!!,
                prefs.getString(KEY_USER_REFRESH_TOKEN, "")!!
            )

            val response = viewModel.refreshTokenLogin(request)

            response.observe(viewLifecycleOwner){
                when (it.state){
                    State.LOADING->{
//                        loadingDialog.start("Processing your request")
                    }

                    State.SUCCESS->{
                        if(it.data?.status == HttpURLConnection.HTTP_OK){

//                            Handler().postDelayed({
//                                val intent = Intent(activity, PinAuthActivity::class.java)
//                                startActivity(intent)
//                                activity?.finish()
//                            }, 500)

//                        loadingDialog.stop()
                        }
                        else {

                            Handler().postDelayed({
                                val intent = Intent(activity, PinAuthActivity::class.java)
                                startActivity(intent)
                                activity?.finish()
                            }, 500)
                            Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    State.ERROR->{
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT)
                            .show()

                    }

                }
            }



        } else  {

            Handler().postDelayed({
                val intent = Intent(activity, AuthActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }, 500)

        }

    }

}