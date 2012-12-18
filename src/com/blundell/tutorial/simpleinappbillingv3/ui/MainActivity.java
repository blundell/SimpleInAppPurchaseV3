package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blundell.tutorial.simpleinappbillingv3.R;

public class MainActivity extends Activity implements MainMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPurchaseItemClick(View v){
        Intent intent = new Intent(this, PurchaseActivity.class);
        startActivity(intent);
    }

}