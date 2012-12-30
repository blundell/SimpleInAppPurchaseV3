package com.blundell.tutorial.simpleinappbillingv3.ui.utils;

import android.app.Activity;
import android.content.Intent;

import com.blundell.tutorial.simpleinappbillingv3.ui.MainActivity;
import com.blundell.tutorial.simpleinappbillingv3.ui.PurchasePassportActivity;

/**
 * A wrapper around the Android Intent mechanism
 * 
 * @author Blundell
 * 
 */
public class Navigator {

    public static final int REQUEST_PASSPORT_PURCHASE = 2012;

    private final Activity activity;

    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public void toMainActivity() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public void toPurchasePassportActivityForResult() {
        Intent intent = new Intent(activity, PurchasePassportActivity.class);
        activity.startActivityForResult(intent, REQUEST_PASSPORT_PURCHASE);
    }

}
