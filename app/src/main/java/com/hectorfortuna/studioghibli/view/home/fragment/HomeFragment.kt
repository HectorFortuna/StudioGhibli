package com.hectorfortuna.studioghibli.view.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.hectorfortuna.studioghibli.R
import com.hectorfortuna.studioghibli.core.Status
import com.hectorfortuna.studioghibli.databinding.FragmentHomeBinding
import com.hectorfortuna.studioghibli.model.FilmsResponse
import com.hectorfortuna.studioghibli.view.home.adapter.FilmsAdapter
import com.hectorfortuna.studioghibli.view.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmsAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFilms()
        observeVMEvents()
    }

    private fun getFilms(field: String = "", limit: Int = 250){
        viewModel.getFilms(field, limit)
    }

    private fun observeVMEvents(){
        viewModel.response.observe(viewLifecycleOwner){
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {
                        setRecyclerView(it)
                    }
                }
                Status.ERROR ->{}
                Status.LOADING ->{}
            }
        }
    }

    private fun setRecyclerView(filmsList: List<FilmsResponse>){
        setAdapter(filmsList)
        binding.rvHomeFragment.apply {
            setHasFixedSize(true)
            adapter = filmsAdapter
        }
    }

    private fun setAdapter(filmsList: List<FilmsResponse>){
        filmsAdapter = FilmsAdapter(filmsList){
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply {
                    putParcelable("FILMS", it)
                }
            )
        }
    }
}