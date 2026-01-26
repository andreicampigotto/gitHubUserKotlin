package com.andreicampigotto.githubusersapi.presentation.user


import android.R.attr.visible
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.andreicampigotto.githubusersapi.R
import com.andreicampigotto.githubusersapi.data.repository.State
import com.andreicampigotto.githubusersapi.databinding.FragmentUserBinding
import com.andreicampigotto.githubusersapi.domain.model.UserModel
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject


class UserFragment(): Fragment(R.layout.fragment_user){

    private val viewModel by inject<UserViewModel>()
    private lateinit var binding: FragmentUserBinding


    private fun observables() {
        viewModel.users.observe(this@UserFragment){
            loadUser(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.getUserInfo("andreicampigotto")
        observables()
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//
//            binding = FragmentUserBinding.bind(view)
//            viewModel.users.observe(viewLifecycleOwner, observables())
//
//            viewModel.getUserInfo("andreicampigotto")
//            //searchRepository()
//        }


    fun loadUser(state: State<UserModel>) {
        when (state) {
            is State.Loading -> {
                binding.progressBar.isVisible
            }

            is State.Success -> {
                binding.progressBar.isGone
                binding.textViewError.isGone
                val user = state.model
                binding.textViewUserName.text = user.name
                binding.textViewLogin.text = user.login
                binding.textViewBio.text = user.bio
                binding.textViewCompany.text = user.company
                binding.textViewLocation.text = user.login
                binding.textViewWebSite.text = user.blog
                binding.textViewTwitter.text = user.twitterUsername
                binding.textViewFollowers.text = user.followers.toString()
                binding.textViewFollowing.text = user.following.toString()
                binding.textViewRepositories.text = user.publicRepos.toString()

                Glide.with(this)
                    .load(user.avatarUrl)
                    .into(binding.imageViewUser)
            }
            is State.Error ->{
                binding.progressBar.isGone
                binding.textViewError.apply {
                    visible
                    text = state.message
                }
            }
        }





    }

    private fun searchUser() {

    }

}