package com.example.slidingpanelayout

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.slidingpanelayout.databinding.FragmentSlidingPaneLayoutAdaptiveDesignBinding

class SlidingPaneLayoutAdaptiveDesignFragment : Fragment(
    R.layout.fragment_sliding_pane_layout_adaptive_design
) {

    private var _binding: FragmentSlidingPaneLayoutAdaptiveDesignBinding? = null
    private val binding: FragmentSlidingPaneLayoutAdaptiveDesignBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSlidingPaneLayoutAdaptiveDesignBinding.bind(view)
        binding.slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
        binding.recyclerView.apply {
            adapter = SlidingPaneAdapter {
                binding.slidingPaneLayout.openPane()
            }
            layoutManager = LinearLayoutManager(requireContext())
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SlidingPanelOnBackPressCallback(binding.slidingPaneLayout)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SlidingPanelOnBackPressCallback(
    private val slidingPanelLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    slidingPanelLayout.isSlideable && slidingPanelLayout.isOpen
), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPanelLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        slidingPanelLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }

}
