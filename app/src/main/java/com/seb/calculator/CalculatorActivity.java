package com.seb.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        DisplayFragment displayFragment = (DisplayFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_display);

        InputFragment inputFragment = (InputFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_input);

        InputOpsFragment inputOpsFragment = (InputOpsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_input_ops);

        CalculatorPresenter calculatorPresenter = new CalculatorPresenter(displayFragment);

        displayFragment.setPresenter(calculatorPresenter);
        inputFragment.setPresenter(calculatorPresenter);
        inputOpsFragment.setForwardInputInteractionToPresenter(calculatorPresenter);
    }

}
