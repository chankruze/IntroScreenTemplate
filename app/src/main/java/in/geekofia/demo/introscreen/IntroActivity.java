package in.geekofia.demo.introscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntroActivity extends AppCompatActivity {

    private static final String DESC = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        // hide actionbar if presents
        Objects.requireNonNull(getSupportActionBar()).hide();

        // init views
        tabLayout = findViewById(R.id.tab_indicator);
        viewPager = findViewById(R.id.viewpager);

        // List of screens & adaptor
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Title 1", DESC, R.drawable.img_one));
        mList.add(new ScreenItem("Title 2", DESC, R.drawable.img_two));
        mList.add(new ScreenItem("Title 3", DESC, R.drawable.img_three));
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);

        // set adaptor to viewpager
        viewPager.setAdapter(introViewPagerAdapter);
        // link tab layout to viewpager
        tabLayout.setupWithViewPager(viewPager);
    }
}
