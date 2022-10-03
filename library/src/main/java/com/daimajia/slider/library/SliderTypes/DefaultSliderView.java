package com.daimajia.slider.library.SliderTypes;

import android.content.Context;
import android.view.View;
import com.daimajia.slider.library.databinding.RenderTypeDefaultBinding;

/**
 * a simple slider view, which just show an image. If you want to make your own slider view,
 *
 * just extend BaseSliderView, and implement getView() method.
 */
public class DefaultSliderView extends BaseSliderView{

    public DefaultSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        RenderTypeDefaultBinding binding = RenderTypeDefaultBinding.inflate(getLayoutInflater());
        bindEventAndShow(binding.getRoot(), binding.image);
        return binding.getRoot();
    }
}
