package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.app.Activity;
import android.os.Bundle;

import com.android.vending.billing.util.*;
import com.android.vending.billing.util.IabHelper.OnIabSetupFinishedListener;
import com.blundell.tutorial.simpleinappbillingv3.BillingConstants;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

public class StartUpActivity extends Activity implements OnIabSetupFinishedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("App started");

        IabHelper billingHelper = new IabHelper(this, BillingConstants.BASE_64_KEY);

        billingHelper.startSetup(this);
    }

    @Override
    public void onIabSetupFinished(IabResult result) {
        if (result.isSuccess()) {
            Log.d("In-app Billing set up" + result);
        } else {
            Log.d("Problem setting up In-app Billing: " + result);
        }
    }
}
