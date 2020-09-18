package com.seb.calculator;


public interface CalculatorContract {

    interface PublishToView {

        void showResult(String result);

        void showError(String message);
    }


    interface ForwardDisplayInteractionToPresenter {

        void onDeleteClickInterfaceMethod();

        void onClearClickInterfaceMethod();

    }

    interface ForwardInputInteractionToPresenter {

        void onNumberClickInterfaceMethod(int number);

        void onDecimalClickInterfaceMethod();

        void onEvaluateClickInterfaceMethod();

        void onOperatorClickInterfaceMethod(String operator);

    }

}
