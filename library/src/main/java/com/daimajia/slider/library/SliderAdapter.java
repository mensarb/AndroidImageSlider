package com.daimajia.slider.library;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import java.util.ArrayList;
import java.util.List;

/**
 * A slider adapter
 */
public class SliderAdapter extends PagerAdapter implements BaseSliderView.ImageLoadListener{
    
    private final ArrayList<BaseSliderView> list = new ArrayList<>();

    public SliderAdapter(){

    }

    public <T extends BaseSliderView> void addSlider(T slider){
        addSlider(slider, list.size());
    }
    
    public <T extends BaseSliderView> void addSlider(T slider, int index){
        slider.setOnImageLoadListener(this);
        list.add(index, slider);
        notifyDataSetChanged();
    }
    
    public <T extends BaseSliderView> void addSlider(List<T> sliders){
        addSlider(sliders, list.size());
    }
    
    public <T extends BaseSliderView> void addSlider(List<T> sliders, int index){
        setOnImageLoadListener(sliders);
        list.addAll(index, sliders);
        notifyDataSetChanged();
    }
    
    private <T extends BaseSliderView> void setOnImageLoadListener(List<T> sliders){
        for(int i=0; i<sliders.size(); i++){
            sliders.get(i).setOnImageLoadListener(this);
        }
    }

    @Nullable
    public BaseSliderView getSliderView(int position){
        if (0 <= position && position < list.size()){
            return list.get(position);
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public <T extends BaseSliderView> void removeSlider(@NonNull T slider){
        if (list.contains(slider)){
            list.remove(slider);
            notifyDataSetChanged();
        }
    }
    
    public void removeSliderAt(int position){
        if (list.size() > position){
            list.remove(position);
            notifyDataSetChanged();
        }
    }
    
    public void removeAllSliders(){
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseSliderView sliderView = list.get(position);
        View view = sliderView.getView();
        container.addView(view);
        return view;
    }
    
    /**
     * =============================================================================================
     * {@link BaseSliderView.ImageLoadListener}
     * =============================================================================================
     */
    
    @Override
    public void onStart(BaseSliderView target) {

    }

    /**
     * When image download error, then remove.
     */
    @Override
    public void onEnd(boolean result, BaseSliderView target) {
        if (!target.isErrorDisappear() || result){
            return;
        }
        for (BaseSliderView slider: list){
            if(slider.equals(target)){
                removeSlider(target);
                break;
            }
        }
    }

}
