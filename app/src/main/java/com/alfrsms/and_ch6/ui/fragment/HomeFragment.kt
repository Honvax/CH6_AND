package com.alfrsms.and_ch6.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alfrsms.and_ch6.R
import com.alfrsms.and_ch6.databinding.FragmentHomeBinding
import com.alfrsms.and_ch6.model.Data
import com.alfrsms.and_ch6.model.MovieResponse
import com.alfrsms.and_ch6.service.TmdbApiInterface
import com.alfrsms.and_ch6.ui.adapter.HomeAdapter
import com.alfrsms.and_ch6.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var api : TmdbApiInterface

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.rvMovieList

        homeViewModel.getUsername().observe(viewLifecycleOwner) {
            binding.tvWelcome.text = "Welcome, $it"
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        getMovieData { movies : List<Data> ->
            recyclerView.adapter = HomeAdapter(movies) }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                val option = NavOptions.Builder()
                    .setPopUpTo(R.id.loginFragment, true)
                    .build()

                findNavController().navigate(R.id.action_homeFragment_to_profileFragment, null , option)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getMovieData(callback: (List<Data>) -> Unit){
        api.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}