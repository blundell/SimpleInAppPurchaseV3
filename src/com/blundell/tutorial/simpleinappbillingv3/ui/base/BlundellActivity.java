package com.blundell.tutorial.simpleinappbillingv3.ui.base;

import android.app.Activity;
import android.widget.Toast;

import com.android.vending.billing.util.IabHelper;
import com.blundell.tutorial.simpleinappbillingv3.BillingConstants;

public class BlundellActivity extends Activity {

    private IabHelper billingHelper;

    protected IabHelper getBillingHelper() {
        if (billingHelper == null) {
            billingHelper = new IabHelper(this, BillingConstants.BASE_64_KEY);
        }
        return billingHelper;
    }

    protected void popBurntToast(String msg) {
        makeToast(msg, Toast.LENGTH_LONG).show();
    }

    protected void popToast(String msg) {
        makeToast(msg, Toast.LENGTH_SHORT).show();
    }

    private Toast makeToast(String msg, int length) {
        return Toast.makeText(this, msg, length);
    }
}
