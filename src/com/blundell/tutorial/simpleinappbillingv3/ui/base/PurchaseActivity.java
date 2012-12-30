package com.blundell.tutorial.simpleinappbillingv3.ui.base;

import android.os.Bundle;

import com.android.vending.billing.util.*;
import com.android.vending.billing.util.IabHelper.OnIabSetupFinishedListener;
import com.blundell.tutorial.simpleinappbillingv3.AppProperties;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

public abstract class PurchaseActivity extends BlundellActivity implements OnIabSetupFinishedListener {

    private IabHelper billingHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);

        getBillingHelper().startSetup(this);
    }

    protected IabHelper getBillingHelper() {
        if (billingHelper == null) {
            billingHelper = new IabHelper(this, AppProperties.BASE_64_KEY);
        }
        return billingHelper;
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

    protected abstract void dealWithIabSetupSuccess();

    protected abstract void dealWithIabSetupFailure();

    @Override
    protected void onDestroy() {
        disposeBillingHelper();
        super.onDestroy();
    }

    private void disposeBillingHelper() {
        if (billingHelper != null) {
            billingHelper.dispose();
        }
        billingHelper = null;
    }
}
