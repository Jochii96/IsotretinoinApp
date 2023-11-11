package com.example.isotretinoin2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Random;


public class TipsTricks extends AppCompatDialogFragment {
/** TipsTricks class, which extends AppCompatDialogFragment. It represents a dialog fragment that displays a random tip or trick. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.TipsTheme);

        //// Array of tips and tricks
        String[] tipsTricks = {"Follow your dermatologist's instructions: Take isotretinoin exactly as prescribed and do not alter the dosage or frequency without consulting your doctor.",

                "Take it with food: Isotretinoin is best absorbed when taken with a meal or snack that contains some fat. This helps improve its effectiveness.",

                "Stay hydrated: Drink plenty of water throughout the day to help prevent dryness, as isotretinoin can cause dehydration. Aim for at least eight glasses of water daily.",

                "Moisturize regularly: Use a gentle, fragrance-free moisturizer to combat dryness, especially on your face, lips, and body. Apply it multiple times a day as needed.",

                "Protect your skin from the sun: Isotretinoin can make your skin more sensitive to sunlight, so always wear sunscreen with at least SPF 30 and seek shade when the sun is strongest.",

                "Use gentle skincare products: Avoid harsh or abrasive cleansers, exfoliants, and skincare products while on isotretinoin. Opt for gentle formulations that are suitable for sensitive skin.",

                "Be patient: Results from isotretinoin treatment may take several weeks or even months to become noticeable. Stay committed to the medication regimen and trust the process.",

                "Avoid waxing and cosmetic procedures: Refrain from waxing or undergoing cosmetic procedures, such as dermabrasion or laser treatments, as isotretinoin can make your skin more fragile and prone to damage.",

                "Report any side effects: If you experience any concerning side effects, such as severe mood changes, depression, or signs of allergic reactions, contact your dermatologist right away.",

                "Use effective contraception: Isotretinoin can cause severe birth defects if taken during pregnancy. Practice two reliable forms of contraception, such as birth control pills and condoms, to prevent pregnancy during treatment."};

        // Generate a random index to select a tip or trick
        Random rand = new Random();
        int Rand_item = rand.nextInt(tipsTricks.length);

        // Set up the dialog builder
        builder.setTitle("Tips and Tricks")

                .setMessage(tipsTricks[Rand_item])

                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Perform any necessary action when the OK button is clicked, empty, just goes back to the MainScreen activity
                    }
                });

        return builder.create();
    }

}

