package org.slavick.dailydozen.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import org.slavick.dailydozen.R;
import org.slavick.dailydozen.model.Date;
import org.slavick.dailydozen.model.Food;
import org.slavick.dailydozen.widget.FoodServings;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main_form)
    protected ViewGroup form;

    @Bind(R.id.date)
    protected TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createFoodServingsWidgets();
    }

    private void createFoodServingsWidgets() {
        final String[] names = getResources().getStringArray(R.array.food_names);
        final int[] quantities = getResources().getIntArray(R.array.food_quantities);

        Food.ensureAllFoodsExistInDatabase(names, quantities);

        final Date today = Date.today();
        date.setText(today.toString());

        for (String name : names) {
            form.addView(new FoodServings(this, today, Food.getByName(name)));
        }
    }
}