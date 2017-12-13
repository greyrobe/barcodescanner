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
        if(DealList.products.isEmpty()) {
            DealList.products.add(new ProductDeal("028400009973", 5, "Miss Vickie's chips", 55.1849, 38.23898));
            DealList.products.add(new ProductDeal("611269163452", 10, "Red Bull", 55.1849, 38.23898));
            DealList.products.add(new ProductDeal("052000208306", 15, "Gatorade", 55.1849, 38.23898));
            DealList.products.add(new ProductDeal("072036726575", 20, "Harris Teeter water", 55.1849, 38.23898));
            DealList.products.add(new ProductDeal("815296020584", 25, "Krave Beef Jerky", 55.1849, 38.23898));
        }
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
