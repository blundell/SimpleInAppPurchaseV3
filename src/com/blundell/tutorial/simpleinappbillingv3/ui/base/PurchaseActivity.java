package com.blundell.tutorial.simpleinappbillingv3.ui.base;

import android.content.Intent;
import android.os.Bundle;

import com.android.vending.billing.util.*;
import com.android.vending.billing.util.IabHelper.OnIabPurchaseFinishedListener;
import com.android.vending.billing.util.IabHelper.OnIabSetupFinishedListener;
import com.blundell.tutorial.simpleinappbillingv3.AppProperties;
import com.blundell.tutorial.simpleinappbillingv3.domain.items.Passport;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

public abstract class PurchaseActivity extends BlundellActivity implements OnIabSetupFinishedListener, OnIabPurchaseFinishedListener {

    private IabHelper billingHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);

        billingHelper = new IabHelper(this, AppProperties.BASE_64_KEY);
        billingHelper.startSetup(this);
    }

    protected void purchaseItem(String sku) {
        billingHelper.launchPurchaseFlow(this, sku, 123, this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        billingHelper.handleActivityResult(requestCode, resultCode, data);
    }

    /**
     * Security Recommendation: When you receive the purchase response from Google Play, make sure to check the returned data
     * signature, the orderId, and the developerPayload string in the Purchase object to make sure that you are getting the
     * expected values. You should verify that the orderId is a unique value that you have not previously processed, and the
     * developerPayload string matches the token that you sent previously with the purchase request. As a further security
     * precaution, you should perform the verification on your own secure server.
     */
    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase info) {
        if (result.isFailure()) {
            dealWithPurchaseFailed(result);
        } else if (Passport.SKU.equals(info.getSku())) {
            dealWithPurchaseSuccess(result, info);
        }
        finish();
    }

    protected void dealWithPurchaseFailed(IabResult result) {
        Log.d("Error purchasing: " + result);
    }

    protected void dealWithPurchaseSuccess(IabResult result, Purchase info) {
        Log.d("Item purchased: " + result);
        // DEBUG XXX
        // We consume the item straight away so we can test multiple purchases
        billingHelper.consumeAsync(info, null);
        // END DEBUG
    }

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
