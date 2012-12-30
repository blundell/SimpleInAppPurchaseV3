package com.blundell.tutorial.simpleinappbillingv3.ui.base;

import android.app.Activity;
import android.os.Bundle;

import com.blundell.tutorial.simpleinappbillingv3.ui.utils.Toaster;

public abstract class BlundellActivity extends Activity {

    private Toaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toaster = new Toaster(this);
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
        toaster = null;
    }
}
