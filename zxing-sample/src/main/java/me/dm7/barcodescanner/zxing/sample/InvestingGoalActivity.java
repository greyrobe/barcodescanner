package me.dm7.barcodescanner.zxing.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class InvestingGoalActivity extends AppCompatActivity {
    SeekBar currentAgeBar, retirementAgeBar;
    TextView monthlySavingsTxt, currentAgeTxt, retirementAgeTxt, totalRetirementSavingsTxt, totalRetirementBonusIncomeTxt;
    int currentAge, retirementAge, yearsUntilRetirement;
    public static double monthlySavings = 0;
    double annualYield;
    DecimalFormat demicalFormat = new DecimalFormat(".##");
    final static int COMPOUNDS_PER_YEAR = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investing_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        annualYield = .05;

        currentAgeBar = (SeekBar) findViewById(R.id.seekBar);
        retirementAgeBar = (SeekBar) findViewById(R.id.seekBar2);
        currentAgeTxt = (TextView) findViewById(R.id.textView);
        retirementAgeTxt = (TextView) findViewById(R.id.textView2);
        monthlySavingsTxt = (TextView) findViewById(R.id.textView14);
        totalRetirementSavingsTxt = (TextView) findViewById(R.id.textView4);
        totalRetirementBonusIncomeTxt = (TextView) findViewById(R.id.textView9);


        monthlySavingsTxt.setText("$" + monthlySavings);

        currentAge = currentAgeBar.getProgress();
        currentAgeTxt.setText("Current Age: " + String.valueOf(currentAge));

        retirementAge = retirementAgeBar.getProgress();
        retirementAgeTxt.setText("Retirement Age: " + String.valueOf(retirementAge));

        calculateRetirementMoney();

        currentAgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentAge = progress;
                currentAgeTxt.setText("Current Age: " + String.valueOf(currentAge));
                calculateRetirementMoney();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        retirementAgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                retirementAge = progress;
                retirementAgeTxt.setText("Retirement Age: " + String.valueOf(retirementAge));
                calculateRetirementMoney();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void calculateRetirementMoney() {
        yearsUntilRetirement = retirementAge - currentAge;
        double retirementTotalSavings = getRetirementSavingsTotal();
        double totalRetirementBonusIncome = getTotalRetirementBonusIncome();
        totalRetirementSavingsTxt.setText("$" + demicalFormat.format(retirementTotalSavings));
        totalRetirementBonusIncomeTxt.setText("$" + demicalFormat.format(totalRetirementBonusIncome));
    }

    public double getRetirementSavingsTotal() {
        return 12 * monthlySavings * yearsUntilRetirement;
    }

    public double getTotalRetirementBonusIncome() {
        return monthlySavings * ( ( Math.pow( (1 + (annualYield / COMPOUNDS_PER_YEAR)),yearsUntilRetirement * COMPOUNDS_PER_YEAR ) - 1 ) / (annualYield / COMPOUNDS_PER_YEAR) );
    }
}
