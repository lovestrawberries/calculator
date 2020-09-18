package com.seb.calculator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InputOpsFragment extends Fragment {

    private ScrollView mScrollView;
    private Button mButton;
    private int mYposition;

    CalculatorContract.ForwardInputInteractionToPresenter forwardInputInteractionToPresenter;

    public void setForwardInputInteractionToPresenter(
            CalculatorContract.ForwardInputInteractionToPresenter forwardInputInteractionToPresenter) {
        this.forwardInputInteractionToPresenter = forwardInputInteractionToPresenter;
    }

    @OnClick({R.id.btn_inputopsfrag_add, R.id.btn_inputopsfrag_divide, R.id.btn_inputopsfrag_multiply,
            R.id.btn_inputopsfrag_subtract, R.id.btn_inputopsfrag_percent, R.id.btn_inputopsfrag_square_root})
    public void onOperatorClick(Button view) {
        forwardInputInteractionToPresenter.onOperatorClickInterfaceMethod(view.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = layoutInflater.inflate(R.layout.fragment_input_ops, viewGroup, false);

        mScrollView = view.findViewById(R.id.inputopsfrag);
        mButton = view.findViewById(R.id.btn_inputopsfrag_square_root);
        ButterKnife.bind(this, view);

        ViewTreeObserver viewTreeObserver = mButton.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] coords = new int[2];
                mButton.getLocationInWindow(coords);
                int absoluteX = coords[0];
                mYposition = coords[1];
            }
        });

        mScrollView.scrollTo(0, 0);
        mScrollView.scrollTo(0, 2779);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

}
