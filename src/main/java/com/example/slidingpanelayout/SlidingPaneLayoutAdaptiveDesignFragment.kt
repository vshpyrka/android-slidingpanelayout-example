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
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(
    slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
        slidingPaneLayout.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            updateEnabledState()
        }
    }

    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    override fun onPanelOpened(panel: View) {
        updateEnabledState()
    }

    override fun onPanelClosed(panel: View) {
        updateEnabledState()
    }

    private fun updateEnabledState() {
        // Only intercept the back button when the sliding pane layout is slideable
        // (in other words, only one of the two panes is visible) and when the sliding pane layout
        // is open (in other words, when the detail pane is open)
        isEnabled = slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen
    }

}
