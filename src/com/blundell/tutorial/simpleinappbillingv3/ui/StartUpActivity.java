package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.content.Intent;
import android.os.Bundle;

import com.android.vending.billing.util.IabHelper.OnIabSetupFinishedListener;
import com.android.vending.billing.util.IabResult;
import com.blundell.tutorial.simpleinappbillingv3.ui.base.BlundellActivity;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

public class StartUpActivity extends BlundellActivity implements OnIabSetupFinishedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("App started");

        getBillingHelper().startSetup(this);
    }

    @Override
    public void onIabSetupFinished(IabResult result) {
        if (result.isSuccess()) {
            Log.d("In-app Billing set up" + result);
            dealWithIabSetupSuccess();
        } else {
            Log.d("Problem setting up In-app Billing: " + result);
            dealWithIabSetupFailure();
        }
    }

    private void dealWithIabSetupSuccess() {
        gotoMainActivity();
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void dealWithIabSetupFailure() {
        popBurntToast("Sorry In App Purchase isn't available on your device");
    }
}
