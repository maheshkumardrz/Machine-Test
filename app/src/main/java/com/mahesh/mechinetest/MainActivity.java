package com.mahesh.mechinetest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;

import com.mahesh.mechinetest.databinding.ActivityMainBinding;
import com.mahesh.mechinetest.ui.home.ActivityFragment;
import com.mahesh.mechinetest.ui.home.ContributionFragment;
import com.mahesh.mechinetest.ui.home.FamilyFragment;
import com.mahesh.mechinetest.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appBarMain.contentMain.bottombar.bottomHome.setOnClickListener(this);
        binding.appBarMain.contentMain.bottombar.bottomFamily.setOnClickListener(this);
        binding.appBarMain.contentMain.bottombar.bottomActivity.setOnClickListener(this);
        binding.appBarMain.contentMain.bottombar.bottomContribution.setOnClickListener(this);

        binding.appBarMain.contentMain.bottombar.bottomHome.performClick();

    }



    public void doChangeColourOfBottomNavItems(ImageView imageView, TextView textView, String type)
    {
        binding.appBarMain.contentMain.bottombar.imHome.setImageResource(R.drawable.home_unselected);
        binding.appBarMain.contentMain.bottombar.txtHome.setTextColor(getResources().getColor(R.color.notActiveText));
        binding.appBarMain.contentMain.bottombar.imFamily.setImageResource(R.drawable.family_unselected);
        binding.appBarMain.contentMain.bottombar.txtFamily.setTextColor(getResources().getColor(R.color.notActiveText));
        binding.appBarMain.contentMain.bottombar.imActivity.setImageResource(R.drawable.activity_unselected);
        binding.appBarMain.contentMain.bottombar.txtActivity.setTextColor(getResources().getColor(R.color.notActiveText));
        binding.appBarMain.contentMain.bottombar.imContribution.setImageResource(R.drawable.contribution_unselected);
        binding.appBarMain.contentMain.bottombar.txtContribution.setTextColor(getResources().getColor(R.color.notActiveText));

        if (type.equals("home")) {
            imageView.setImageResource(R.drawable.home_selected);
            textView.setTextColor(getResources().getColor(R.color.red));
        }else if (type.equals("family")) {
            imageView.setImageResource(R.drawable.family_selected);
            textView.setTextColor(getResources().getColor(R.color.red));

        }  else if (type.equals("activity")) {
            imageView.setImageResource(R.drawable.activity_selected);
            textView.setTextColor(getResources().getColor(R.color.red));

        } else if (type.equals("contribution")) {
            imageView.setImageResource(R.drawable.contribution_selected);
            textView.setTextColor(getResources().getColor(R.color.red));
        } else {
            imageView.setImageResource(R.drawable.home_selected);
            textView.setTextColor(getResources().getColor(R.color.red));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    public void naveOpen(View view)
    {
        binding.drawerLayout.openDrawer(Gravity.START);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bottomHome:
                doChangeColourOfBottomNavItems(binding.appBarMain.contentMain.bottombar.imHome, binding.appBarMain.contentMain.bottombar.txtHome, "home");
                fragTransaction(new HomeFragment());
                break;
            case R.id.bottomFamily:
                doChangeColourOfBottomNavItems(binding.appBarMain.contentMain.bottombar.imFamily, binding.appBarMain.contentMain.bottombar.txtFamily, "family");
                fragTransaction(new FamilyFragment());
                break;
            case R.id.bottomActivity:
                doChangeColourOfBottomNavItems(binding.appBarMain.contentMain.bottombar.imActivity, binding.appBarMain.contentMain.bottombar.txtActivity, "activity");
                fragTransaction(new ActivityFragment());
                break;
            case R.id.bottomContribution:
                doChangeColourOfBottomNavItems(binding.appBarMain.contentMain.bottombar.imContribution, binding.appBarMain.contentMain.bottombar.txtContribution, "contribution");
                fragTransaction(new ContributionFragment());
                break;
        }

    }

    public boolean isLastStackedFragment(Fragment fragment) {
        boolean status = false;
        try {
            int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
            String tag = getSupportFragmentManager().getBackStackEntryAt(index).getName().toString();
            Fragment frg = getSupportFragmentManager().findFragmentByTag(tag);
            if (frg.getTag().toString().equals(fragment.getClass().getName().toString())) {
                status = true;
            }
        } catch (Exception e) {
            Log.e("isLastStackedFragment: ", e.toString());
        }
        return status;
    }


    public void fragTransaction(Fragment fragment) {
        if (!isLastStackedFragment(fragment)) {
            getSupportFragmentManager().beginTransaction().addToBackStack(fragment.getClass().getName().toString()).replace(R.id.fragment_view, fragment, fragment.getClass().getName().toString()).commit();
        }

    }
}