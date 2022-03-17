package com.example.matsedillvikunnar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.matsedillvikunnar.databinding.ActivityMainBinding;
import com.example.matsedillvikunnar.fragments.MyPageFragment;
import com.example.matsedillvikunnar.fragments.NewPlanFragment;
import com.example.matsedillvikunnar.fragments.RecipesFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MyPageFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.myPages:
                    replaceFragment(new MyPageFragment());
                    break;
                case R.id.newPlan:
                    replaceFragment(new NewPlanFragment());
                    break;
                case R.id.recipes:
                    replaceFragment(new RecipesFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}

