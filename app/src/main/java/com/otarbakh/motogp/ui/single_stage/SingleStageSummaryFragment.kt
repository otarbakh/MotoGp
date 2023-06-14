package com.otarbakh.motogp.ui.single_stage


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.databinding.FragmentSingleStageSummaryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingleStageSummaryFragment :
    BaseFragment<FragmentSingleStageSummaryBinding>(FragmentSingleStageSummaryBinding::inflate) {

    private val singleStageVM: SingleStageSummaryViewModel by viewModels()
    private val args: SingleStageSummaryFragmentArgs by navArgs()

    override fun viewCreated() {
        observe()
    }

    override fun listeners() {

    }

    private fun observe(){
        singleStageVM.getSingleStageSummary(args.stageId)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                singleStageVM.state.collectLatest {
                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                           binding.apply {
                               tvWinner.text = it.data?.get(0)!!.name.toString()
                               tv2ndPlace.text = it.data?.get(1)!!.name.toString()
                               tv3rdPlace.text = it.data?.get(2)!!.name.toString()
                               tvStageName.text = it.data?.get(0)!!.country_code.toString()
                           }
                        }
                    }
                }
            }
        }
    }


}