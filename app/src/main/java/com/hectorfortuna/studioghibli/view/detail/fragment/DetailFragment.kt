package com.hectorfortuna.studioghibli.view.detail.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hectorfortuna.studioghibli.R
import com.hectorfortuna.studioghibli.databinding.FragmentDetailBinding
import com.hectorfortuna.studioghibli.model.FilmsResponse

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var films: FilmsResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        films = arguments?.getParcelable<FilmsResponse>("FILMS") as FilmsResponse
        println(films.id)
    }

}