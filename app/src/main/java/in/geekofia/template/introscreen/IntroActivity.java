package in.geekofia.template.introscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DESC = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabLayout;
    private Button buttonNext, buttonStart;
    int currPosition = 0, maxPosition = 0;
    private Animation buttonStartAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        // check if it rolled before
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("intro_pref", MODE_PRIVATE);

        if (preferences.getBoolean("isIntroRolled", false)) {
            startMainActivity();
        }

        // hide actionbar if presents
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // init views
        tabLayout = findViewById(R.id.tab_indicator);
        viewPager = findViewById(R.id.viewpager);
        buttonNext = findViewById(R.id.button_next);
        buttonStart = findViewById(R.id.button_start);
        buttonStartAnim = AnimationUtils.loadAnimation(this, R.anim.button_start_anim);
        TextView buttonSkip = findViewById(R.id.tv_skip);

        // List of screens & adaptor
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Tasty Food", DESC, R.drawable.img_one));
        mList.add(new ScreenItem("Fast Delivery", DESC, R.drawable.img_two));
        mList.add(new ScreenItem("Easy Payment", DESC, R.drawable.img_three));
        maxPosition = mList.size();
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);

        // set adaptor to viewpager
        viewPager.setAdapter(introViewPagerAdapter);
        // link tab layout to viewpager
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == maxPosition - 1) {
                    loadLastScreen();
                } else {
                    resetScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // link buttonNext click to viewpager
        buttonNext.setOnClickListener(this);
        buttonStart.setOnClickListener(this);
        buttonSkip.setOnClickListener(this);
    }

    private void startMainActivity() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void loadLastScreen() {
        buttonNext.setVisibility(View.INVISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        buttonStart.setVisibility(View.VISIBLE);

        buttonStart.setAnimation(buttonStartAnim);
    }

    private void resetScreen() {
        buttonNext.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        buttonStart.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                currPosition = viewPager.getCurrentItem();

                if (currPosition < maxPosition) {
                    currPosition++;
                    viewPager.setCurrentItem(currPosition);
                }
                break;
            case R.id.tv_skip:
                viewPager.setCurrentItem(maxPosition - 1);
                break;
            case R.id.button_start:
                startMainActivity();

                SharedPreferences preferences = getApplicationContext().getSharedPreferences("intro_pref", MODE_PRIVATE);
                preferences.edit().putBoolean("isIntroRolled", true).apply();
        }
    }
}
