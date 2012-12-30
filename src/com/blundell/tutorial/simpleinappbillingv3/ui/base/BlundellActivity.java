package com.blundell.tutorial.simpleinappbillingv3.ui.base;

import android.app.Activity;
import android.os.Bundle;

import com.blundell.tutorial.simpleinappbillingv3.ui.utils.Navigator;
import com.blundell.tutorial.simpleinappbillingv3.ui.utils.Toaster;

/**
 * Here we keep common functionality that will be used across multiple activities
 * making our life easier
 * 
 * @author Blundell
 * 
 */
public abstract class BlundellActivity extends Activity {

    private Navigator navigator;
    private Toaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigator = new Navigator(this);
        toaster = new Toaster(this);
    }

    protected Navigator navigate() {
        return navigator;
    }

    protected void popBurntToast(String msg) {
        toaster.popBurntToast(msg);
    }

    protected void popToast(String msg) {
        toaster.popToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigator = null;
        toaster = null;
    }
}
