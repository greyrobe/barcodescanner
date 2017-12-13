package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static me.dm7.barcodescanner.zxing.sample.DealList.products;

public class SimpleScannerActivity extends BaseScannerActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public String uidFound;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(this, "Contents = " + rawResult.getText() +
                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        uidFound = rawResult.getText();
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
                findDeal(uidFound);
            }
        }, 2000);
    }

    public void findDeal(String uid) {
        int dealIndex =  getDealIndex(uid);
        if(-1 != dealIndex) {
            ProductDeal deal = DealList.products.get(dealIndex);
            InvestingGoalActivity.monthlySavings += deal.savings;
            Intent i = new Intent(this, ProductActivity.class);
            i.putExtra("Deal", deal);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "No deals found for this item.", Toast.LENGTH_SHORT).show();
        }
    }

    public int getDealIndex(String uid) {
        List<ProductDeal> productDeals = DealList.products;
        for(int i = 0; i < productDeals.size(); i++) {
            if(uid.equals(productDeals.get(i).getUid())) {
                return i;
            }
        }
        return -1;
    }
}
