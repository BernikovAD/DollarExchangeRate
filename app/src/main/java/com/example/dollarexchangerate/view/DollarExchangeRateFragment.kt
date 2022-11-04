package com.example.dollarexchangerate.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.dollarexchangerate.*
import com.example.dollarexchangerate.databinding.FragmentDollarExchangeRateBinding
import com.example.dollarexchangerate.utils.BaseViewBindingFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DollarExchangeRateFragment :
    BaseViewBindingFragment<FragmentDollarExchangeRateBinding>(FragmentDollarExchangeRateBinding::inflate) {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: DollarExchangeRateViewModel
    private lateinit var adapter: DollarExchangeRateAdapter

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[DollarExchangeRateViewModel::class.java]
        adapter = DollarExchangeRateAdapter()
        binding.rvRate.adapter = adapter
        val calendarFrom = Calendar.getInstance()
        calendarFrom.time = Date()
        val calendarBefore = Calendar.getInstance()
        calendarBefore.time = Date()
        val simpleFormat = SimpleDateFormat("dd/MM/yyyy")
        calendarFrom.add(Calendar.DATE, -30)
        viewModel.getData(
            simpleFormat.format(calendarFrom.time),
            simpleFormat.format(calendarBefore.time)
        )
        viewModel.observeState(viewLifecycleOwner) {
            when (it) {
                DollarExchangeRateState.Loading -> {
                    binding.rvRate.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DollarExchangeRateState.SuccessLoadData -> {
                    binding.rvRate.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(it.list.rateList)
                }
            }
        }
    }

    companion object {
        fun newInstance() = DollarExchangeRateFragment()
    }
}