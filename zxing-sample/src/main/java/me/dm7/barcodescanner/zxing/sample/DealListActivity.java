package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DealListActivity extends AppCompatActivity {
    ListView dealsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_list);
        dealsList = (ListView) findViewById(R.id.dealList);
        ArrayAdapter<ProductDeal> adapter = new ArrayAdapter<ProductDeal>(this, android.R.layout.simple_list_item_1, DealList.products);
        dealsList.setAdapter(adapter);

        dealsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(DealListActivity.this, ProductActivity.class);
                ProductDeal deal = DealList.products.get(position);
                intent.putExtra("Deal", deal);
                startActivity(intent);
            }
        });
    }
}
