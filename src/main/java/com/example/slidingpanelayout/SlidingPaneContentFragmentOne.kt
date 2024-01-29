package com.example.slidingpanelayout

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.slidingpanelayout.databinding.FragmentSlidingPaneContentOneBinding

class SlidingPaneContentFragmentOne : Fragment(R.layout.fragment_sliding_pane_content_one) {

    private var _binding: FragmentSlidingPaneContentOneBinding? = null
    private val binding: FragmentSlidingPaneContentOneBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSlidingPaneContentOneBinding.bind(view)
        binding.text.text = getLoremIpsum()
        binding.text.movementMethod = ScrollingMovementMethod()
        binding.button.setOnClickListener {
            view.findNavController()
                .navigate(
                    SlidingPaneContentFragmentOneDirections.actionSlidingPaneContentFragmentOneToSlidingPaneContentFragmentTwo()
                )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
