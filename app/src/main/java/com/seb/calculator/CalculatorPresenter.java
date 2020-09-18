package com.seb.calculator;


public class CalculatorPresenter implements CalculatorContract.ForwardInputInteractionToPresenter,
    CalculatorContract.ForwardDisplayInteractionToPresenter, CalculationModel.CalculationResult {

    private CalculatorContract.PublishToView publishResult;

    private CalculationModel calculationModel;

    public CalculatorPresenter (CalculatorContract.PublishToView publishResult) {
        this.publishResult = publishResult;
        this.calculationModel = new CalculationModel();

        calculationModel.setCalculationResultListener(this);
    }

    @Override
    public void onDeleteClickInterfaceMethod() {
        calculationModel.deleteChar();
    }

    @Override
    public void onClearClickInterfaceMethod() {
        calculationModel.deleteExpression();
    }

    @Override
    public void onNumberClickInterfaceMethod(int number) {
        calculationModel.appendNumber(Integer.toString(number));
    }

    @Override
    public void onDecimalClickInterfaceMethod() {
        calculationModel.appendDecimal();
    }

    @Override
    public void onEvaluateClickInterfaceMethod() {
        calculationModel.performEvaluation();
    }

    @Override
    public void onOperatorClickInterfaceMethod(String operator) {
        calculationModel.appendOperator(operator);
    }

    @Override
    public void onExpressionChangedLocalInterfaceMethod(String result, boolean isSuccessful) {
        if (isSuccessful) {

            publishResult.showResult(result);
        } else {
            publishResult.showError(result);
        }
    }
}
