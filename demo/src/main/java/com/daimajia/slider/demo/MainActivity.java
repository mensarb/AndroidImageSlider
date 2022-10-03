package com.daimajia.slider.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.daimajia.slider.demo.databinding.ActivityMainBinding;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());

        HashMap<String,String> url_maps = getUrlMap();
        HashMap<String,Integer> file_maps = getFileMap();

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                        .putString("extra", name);
    
            binding.slider.addSlider(textSliderView);
        }
        binding.slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        binding.slider.setCustomAnimation(new DescriptionAnimation());
        binding.slider.setDuration(4000);
        binding.slider.addOnPageChangeListener(this);
    
        binding.transformers.setAdapter(new TransformerAdapter(this));
        binding.transformers.setOnItemClickListener((parent, view, position, id) -> {
            binding.slider.setPresetTransformer(((TextView) view).getText().toString());
            Toast.makeText(MainActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private HashMap<String,String> getUrlMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        map.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        map.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        map.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        return map;
    }
    
    private HashMap<String, Integer> getFileMap(){
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        map.put("Hannibal",R.drawable.hannibal);
        map.put("Big Bang Theory",R.drawable.bigbang);
        map.put("House of Cards",R.drawable.house);
        map.put("Game of Thrones", R.drawable.game_of_thrones);
        return map;
    }
    
    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        binding.slider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_custom_indicator:
                binding.slider.setCustomIndicator(findViewById(R.id.custom_indicator));
                break;
            case R.id.action_custom_child_animation:
                binding.slider.setCustomAnimation(new ChildAnimationExample());
                break;
            case R.id.action_restore_default:
                binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                binding.slider.setCustomAnimation(new DescriptionAnimation());
                break;
            case R.id.action_github:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    
    }

    @Override
    public void onPageSelected(int position) {
    
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    
    }
}
