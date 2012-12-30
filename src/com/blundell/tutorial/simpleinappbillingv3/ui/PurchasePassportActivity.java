package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.os.Bundle;

import com.android.vending.billing.util.IabResult;
import com.android.vending.billing.util.Purchase;
import com.blundell.tutorial.simpleinappbillingv3.domain.items.Passport;
import com.blundell.tutorial.simpleinappbillingv3.ui.base.PurchaseActivity;

public class PurchasePassportActivity extends PurchaseActivity {

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
        purchaseItem(Passport.SKU);
    }

    @Override
    protected void dealWithPurchaseFailed(IabResult result) {
        super.dealWithPurchaseFailed(result);
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void dealWithPurchaseSuccess(IabResult result, Purchase info) {
        super.dealWithPurchaseSuccess(result, info);
        setResult(RESULT_OK);
        finish();
    }

}
