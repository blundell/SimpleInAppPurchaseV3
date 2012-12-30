package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.os.Bundle;

import com.android.vending.billing.util.IabHelper.OnIabPurchaseFinishedListener;
import com.android.vending.billing.util.*;
import com.blundell.tutorial.simpleinappbillingv3.domain.items.Passport;
import com.blundell.tutorial.simpleinappbillingv3.ui.base.PurchaseActivity;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

public class PurchasePassportActivity extends PurchaseActivity implements OnIabPurchaseFinishedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the result as cancelled in case anything fails before we purchase the item
        setResult(RESULT_CANCELED);
        // Then wait for the callback if we have successfully setup in app billing or not
    }

    @Override
    protected void dealWithIabSetupFailure() {
        popBurntToast("Sorry buying a passport is not available at this current time");
        finish();
    }

    @Override
    protected void dealWithIabSetupSuccess() {
        getBillingHelper().launchPurchaseFlow(this, Passport.SKU, 123, this);
    }

    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase info) {
        if (result.isFailure()) {
            Log.d("Error purchasing: " + result);
            setResult(RESULT_CANCELED);
        } else if (Passport.SKU.equals(info.getSku())) {
            setResult(RESULT_OK);
        }
        finish();
    }
}
