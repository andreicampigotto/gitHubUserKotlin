package com.andreicampigotto.githubusersapi.presentation.user


import android.R.attr.visible
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.andreicampigotto.githubusersapi.data.repository.State
import com.andreicampigotto.githubusersapi.databinding.FragmentUserBinding
import com.andreicampigotto.githubusersapi.domain.model.UserModel
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject


class UserFragment(): Fragment(){

    private val viewModel by inject<UserViewModel>()
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun observables() {

        viewModel.users.observe(viewLifecycleOwner){
            loadUser(it)
        }

        searchUser()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel.getUserInfo("andreicampigotto")

            observables()
            searchUser()
        }


    private fun loadUser(state: State<UserModel>) {
        when (state) {
            is State.Loading -> {
                binding.progressBar.isVisible
            }
            is State.Success -> {
                binding.progressBar.isGone
                binding.textViewError.isGone
                val user = state.model
                binding.includeCard
                    .textViewUserName.text = user.name
                binding.includeCard.textViewLogin.text = user.login
                binding.includeCard.textViewBio.apply {
                    text = user.bio
                    isVisible = !user.bio.isNullOrBlank()
                }
                binding.includeCard.textViewCompany.text = user.company
                binding.includeCard.textViewLocation.text = user.location
                binding.includeCard.textViewWebSite.apply {
                    text = user.blog
                    isVisible = !user.blog.isNullOrBlank()
                }
                binding.includeCard.textViewTwitter.apply {
                    text = user.twitterUsername
                    isVisible = !user.twitterUsername.isNullOrBlank()
                }
                binding.includeCard.textViewFollowers.text = "${user.followers}      Followers"
                binding.includeCard.textViewFollowing.text = "${ user.following }     Following"
                binding.includeCard.textViewRepositories.text = "Repositories     ${user.publicRepos}"

                Glide.with(this)
                    .load(user.avatarUrl)
                    .into(binding.includeCard.imageViewUser)
            }
            is State.Error ->{
                binding.progressBar.isVisible
                binding.textViewError.apply {
                    visible
                    text = state.message
                }
            }
        }

    }

    private fun searchUser() {
        val search = binding.textInputEditTextSearch

        search.setOnEditorActionListener {  v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString()

                viewModel.getUserInfo(binding.textInputEditTextSearch.toString())
                viewModel.getUserInfo(query)
                true
            } else {
                false
            } }

        search.setHint("...")
        search.requestFocus()


    }

}