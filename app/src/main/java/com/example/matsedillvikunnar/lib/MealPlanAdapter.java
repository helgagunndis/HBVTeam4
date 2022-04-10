package com.example.matsedillvikunnar.lib;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.R;
import java.util.List;

public class MealPlanAdapter extends ArrayAdapter<MealPlan> {
    private final String TAG ="MealPlanAdapter";
    private Context context;
    private List<MealPlan> items;
    private LayoutInflater vi;
    private ImageButton mButtonCart;
    private ImageButton mButtonMealPlan;

    public MealPlanAdapter(Context context, List<MealPlan> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final MealPlan mealPlan = items.get(position);
        if (mealPlan != null) {
            v = vi.inflate(R.layout.cardview_meal_plan, null);
            ((TextView)v.findViewById(R.id.meal_plan_date)).setText("Þetta er mealPlan frá ");

            Log.d("TAG", "Þetta er position" + mealPlan.getMpLists().get(0).getRecipe().getRecipeTitle() );
        }
        return v;
    }
}
