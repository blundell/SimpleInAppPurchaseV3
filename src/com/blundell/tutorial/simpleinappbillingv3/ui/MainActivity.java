package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blundell.tutorial.simpleinappbillingv3.R;
import com.blundell.tutorial.simpleinappbillingv3.ui.base.BlundellActivity;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

public class MainActivity extends BlundellActivity implements MainMenu {

    private static final int REQUEST_PASSPORT_PURCHASE = 2012;

    private ImageView passportImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passportImage = (ImageView) findViewById(R.id.main_passport_image);
    }

    @Override
    public void onPurchaseItemClick(View v) {
        gotoPurchasePassportActivity();
    }

    private void gotoPurchasePassportActivity() {
        Intent intent = new Intent(this, PurchasePassportActivity.class);
        startActivityForResult(intent, REQUEST_PASSPORT_PURCHASE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_PASSPORT_PURCHASE == requestCode) {
            if (RESULT_OK == resultCode) {
                dealWithSuccessfulPurchase();
            } else {
                dealWithFailedPurchase();
            }
        }
    }

    private void dealWithSuccessfulPurchase() {
        Log.d("Passport purchased");
        passportImage.setVisibility(View.VISIBLE);
    }

    private void dealWithFailedPurchase() {
        Log.d("Passport purchase failed");
        popToast("Failed to purchase passport");
    }
}
