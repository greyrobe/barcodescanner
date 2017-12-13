package me.dm7.barcodescanner.zxing.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NagivationActivity extends AppCompatActivity {
    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nagivation_acitivity);
        setupToolbar();
        if(DealList.products.isEmpty()) {
            DealList.products.add(new ProductDeal("028400009973", 5, "Miss Vickie's chips", 35.184075, -80.925547));
            DealList.products.add(new ProductDeal("611269163452", 10, "Red Bull", -80.925547, 35.184075));
            DealList.products.add(new ProductDeal("052000208306", 15, "Gatorade", 55.1849, 38.23898));
            DealList.products.add(new ProductDeal("072036726575", 20, "Harris Teeter water", 55.1849, 38.23898));
            DealList.products.add(new ProductDeal("815296020584", 25, "Krave Beef Jerky", 55.1849, 38.23898));
        }
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void launchScanActivity(View v) {
        launchActivity(SimpleScannerActivity.class);
    }

    public void launchGoalActivity(View v) {
        Intent intent = new Intent(this, InvestingGoalActivity.class);
        startActivity(intent);
    }

    public void launchDealsActivity(View v) {
        Intent intent = new Intent(this, DealListActivity.class);
        startActivity(intent);
    }

    public void launchMapActivity(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

}
