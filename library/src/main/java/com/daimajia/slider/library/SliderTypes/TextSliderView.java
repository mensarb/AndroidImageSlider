package com.daimajia.slider.library.SliderTypes;

import android.content.Context;
import android.view.View;
import com.daimajia.slider.library.databinding.RenderTypeTextBinding;

/**
 * This is a slider with a description TextView.
 */
public class TextSliderView extends BaseSliderView {

    public TextSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        RenderTypeTextBinding binding = RenderTypeTextBinding.inflate(getLayoutInflater());
        binding.description.setText(getDescription());
        bindEventAndShow(binding.getRoot(), binding.image);
        return binding.getRoot();
    }
}
