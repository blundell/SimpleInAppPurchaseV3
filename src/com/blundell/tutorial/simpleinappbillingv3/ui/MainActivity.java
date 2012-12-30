package com.blundell.tutorial.simpleinappbillingv3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blundell.tutorial.simpleinappbillingv3.R;
import com.blundell.tutorial.simpleinappbillingv3.ui.base.BlundellActivity;
import com.blundell.tutorial.simpleinappbillingv3.ui.utils.Navigator;
import com.blundell.tutorial.simpleinappbillingv3.util.Log;

/**
 * This activity holds the button to purchase a passport
 * when the result is received if the passport was successfully purchase it shows the picture
 * 
 * @author Blundell
 * 
 */
public class MainActivity extends BlundellActivity implements MainMenu {

    private ImageView passportImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passportImage = (ImageView) findViewById(R.id.main_passport_image);
    }

    @Override
    public void onPurchaseItemClick(View v) {
        navigate().toPurchasePassportActivityForResult();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Navigator.REQUEST_PASSPORT_PURCHASE == requestCode) {
            if (RESULT_OK == resultCode) {
                dealWithSuccessfulPurchase();
            } else {
                dealWithFailedPurchase();
            }
        }
    }

    private void dealWithSuccessfulPurchase() {
        Log.d("Passport purchased");
        popToast("Passport purchased");
        passportImage.setVisibility(View.VISIBLE);
    }

    private void dealWithFailedPurchase() {
        Log.d("Passport purchase failed");
        popToast("Failed to purchase passport");
    }
}
