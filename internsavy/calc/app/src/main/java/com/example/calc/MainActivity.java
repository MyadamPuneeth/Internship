package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrOp, buttonBrCl;
    MaterialButton buttonDiv, buttonMul, buttonAdd, buttonSub, buttonEqu;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result);
        solutionTv = findViewById(R.id.solution);

        assignId(buttonC, R.id.button_C);
        assignId(buttonBrOp, R.id.button_op_br);
        assignId(buttonBrCl, R.id.button_cl_br);
        assignId(buttonDiv, R.id.button_div);
        assignId(buttonMul, R.id.button_mul);
        assignId(buttonAdd, R.id.button_add);
        assignId(buttonSub, R.id.button_min);
        assignId(buttonEqu, R.id.button_equ);
        assignId(button0, R.id.button_zer);
        assignId(button1, R.id.button_one);
        assignId(button2, R.id.button_two);
        assignId(button3, R.id.button_thr);
        assignId(button4, R.id.button_fou);
        assignId(button5, R.id.button_fiv);
        assignId(button6, R.id.button_six);
        assignId(button7, R.id.button_sev);
        assignId(button8, R.id.button_eig);
        assignId(button9, R.id.button_nin);
        assignId(buttonAC, R.id.button_alclr);
        assignId(buttonDot, R.id.button_dot);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionTv.setText((dataToCalculate));

        String final_ans = getResult(dataToCalculate);

        if(!final_ans.equals("Err")){
            resultTv.setText(final_ans);

        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String final_ans = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (final_ans.endsWith(".0")){
                final_ans = final_ans.replace(".0", "");
            }
            return final_ans;
        }
        catch (Exception e){
            return "Err";
        }
    }

}
