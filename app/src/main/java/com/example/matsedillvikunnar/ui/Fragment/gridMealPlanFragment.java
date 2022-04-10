package com.example.matsedillvikunnar.ui.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.EntityClass.User;
import com.example.matsedillvikunnar.R;
import com.example.matsedillvikunnar.Service.UserService;
import com.example.matsedillvikunnar.lib.MealPlanAdapter;
import com.example.matsedillvikunnar.networking.NetworkCallback;

import org.json.JSONException;

import java.util.List;


public class gridMealPlanFragment extends Fragment {
    private final String TAG ="gridMealPlanFragment";

    private Context mContext;
    private UserService mUserService;
    private List<MealPlan> mMealPlanList;

    private GridView mGridView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String mUsername;
    private ImageButton mButtonCart;
    private ImageButton mButtonMealPlan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = gridMealPlanFragment.this.getActivity();
        mUserService= new UserService(mContext);

        View view = inflater.inflate(R.layout.fragment_grid_meal_plan, container, false);
        mGridView = (GridView) view.findViewById(R.id.meal_plan_grid);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.meal_plan_swipe);
        mButtonCart = (ImageButton) view.findViewById(R.id.show_shopping_list_btn);
        mButtonMealPlan = (ImageButton) view.findViewById(R.id.show_meal_plan_btn);

        if (getArguments()!=null){
            mUsername = getArguments().getString("username");
            getMealPlan(mUsername);
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMealPlan(mUsername);
            }
        });
        return view;
    }

    private void getMealPlan(String username) {
        mUserService.getMealPlan(username, new NetworkCallback<List<MealPlan>>() {
            @Override
            public void onSuccess(List<MealPlan> result) {
                mMealPlanList = result;
                Log.d(TAG, "Tókst að ná í MealPlan");

                MealPlanAdapter adapter = new MealPlanAdapter(gridMealPlanFragment.this.getActivity(), mMealPlanList);
                mGridView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
                mGridView.setVisibility(View.VISIBLE);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                /* mButtonCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Ýtt er á körfu");

                    }
                });
                mButtonMealPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Ýtt MealPlan" );
                    }
                }); */

                if (mMealPlanList.size() == 0) {
                    // TODO : hvað á að sýna það notandi er ekki með mealPlan
                    Log.d(TAG, "Notandi er ekki með nein mealPlan vistuð");
                }
            }

            @Override
            public void onFailure(String errorString) {
                Log.e(TAG, "Ekki hægt að ná í MealPlan: " + errorString);
            }
        });
    }
}