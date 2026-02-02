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
        binding.TextInputEditTextSearch.onFocusChangeListener

        searchUser()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel.getUserInfo("andreicampigotto")

            observables()
            searchUser()
        }


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
                binding.textViewBio.apply {
                    text = user.bio
                    isVisible = !user.bio.isNullOrBlank()
                }
                binding.textViewCompany.text = user.company
                binding.textViewLocation.text = user.location
                binding.textViewWebSite.apply {
                    text = user.blog
                    isVisible = !user.blog.isNullOrBlank()
                }
                binding.textViewTwitter.apply {
                    text = user.twitterUsername
                    isVisible = !user.twitterUsername.isNullOrBlank()
                }
                binding.textViewFollowers.text = user.followers.toString() + "    Followers"
                binding.textViewFollowing.text = user.following.toString() + "    Following"
                binding.textViewRepositories.text = "Repositories     " + user.publicRepos.toString()

                Glide.with(this)
                    .load(user.avatarUrl)
                    .into(binding.imageViewUser)
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
        val search = binding.TextInputEditTextSearch

        search.setOnEditorActionListener {  v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString()

                viewModel.getUserInfo(binding.TextInputEditTextSearch.toString())
                viewModel.getUserInfo(query)
                true
            } else {
                false
            } }

        search.setHint("...")
        search.requestFocus()


    }

}