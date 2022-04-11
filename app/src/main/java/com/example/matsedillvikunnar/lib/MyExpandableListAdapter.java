package com.example.matsedillvikunnar.lib;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matsedillvikunnar.EntityClass.MealPlan;
import com.example.matsedillvikunnar.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<MealPlan> mealPlan;
    public LayoutInflater inflater;
    public Activity activity;

    public MyExpandableListAdapter(Activity act, List<MealPlan> mealPlan) {
        activity = act;
        this.mealPlan = mealPlan;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mealPlan.get(groupPosition).getMpLists().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        System.out.println("click on child view");
        String recipe = mealPlan.get(groupPosition).getMpLists().get(childPosition).getRecipe().getRecipeTitle();
        String img = mealPlan.get(groupPosition).getMpLists().get(childPosition).getRecipe().getRecipeImage();

        System.out.println(img);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details_meal_plan, null);
        }
        ImageView imgView = (ImageView) convertView.findViewById(R.id.recipe_img);
        Picasso.get().load(img).into(imgView);
        text = (TextView) convertView.findViewById(R.id.textView_Recipe);
        text.setText(recipe);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, recipe,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mealPlan.get(groupPosition).getMpLists().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mealPlan.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        System.out.println("PRETNA ÞETTA");
        System.out.println(mealPlan.size());
        return mealPlan.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group_meal_plan, null);
        }
        Timestamp created = mealPlan.get(groupPosition).getCreated();
        Date date = new Date();
        date.setTime(created.getTime());
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        ((CheckedTextView) convertView).setText(formattedDate);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}