package hcmute.edu.vn.calculator_02;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.MathContext;

public class MainActivity extends AppCompatActivity {

    private final int MUL = 1;
    private final int SUM = 2;
    private final int SUB = 3;
    private final int DIV = 4;
    private final int NULL_OP = 0;
    private final String NAN = "#NAN";
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8,btn9, btn0, btn_mul, btn_div, btn_sum, btn_sub, btn_C, btn_equal, btn_dot;
    private EditText editTextNum;
    private BigDecimal currentValue;
    private int currentOperator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentValue = BigDecimal.valueOf(0);
        currentOperator = NULL_OP;
        AnhXa();

        setEvent(btn1);
        setEvent(btn2);
        setEvent(btn3);
        setEvent(btn4);
        setEvent(btn5);
        setEvent(btn6);
        setEvent(btn7);
        setEvent(btn8);
        setEvent(btn9);
        setEvent(btn0);
        setEventC(btn_C);
        setEventDot(btn_dot);
        setEventSum();
        setEventSub();
        setEventDiv();
        setEventMul();
        setEventEqual();
    }

    protected void AnhXa(){
        btn0 = findViewById(R.id.N0);
        btn1 = findViewById(R.id.N1);
        btn2 = findViewById(R.id.N2);
        btn3 = findViewById(R.id.N3);
        btn4 = findViewById(R.id.N4);
        btn5 = findViewById(R.id.N5);
        btn6 = findViewById(R.id.N6);
        btn7 = findViewById(R.id.N7);
        btn8 = findViewById(R.id.N8);
        btn9 = findViewById(R.id.N9);
        btn_C = findViewById(R.id.NC);
        btn_div = findViewById(R.id.N_div);
        btn_mul = findViewById(R.id.N_mul);
        btn_sub = findViewById(R.id.N_sub);
        btn_sum = findViewById(R.id.N_sum);
        btn_dot = findViewById(R.id.N_dot);
        btn_equal = findViewById(R.id.N_equal);
        editTextNum = findViewById(R.id.editTextNum);
    }
    protected void setEvent(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String num = btn.getText().toString().trim();
                editTextNum.setText(editTextNum.getText().toString()+num);
            }
        });
    }
    protected void setEventC(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNum.setText("");
                currentValue = BigDecimal.valueOf(0);
                currentOperator = NULL_OP;
            }
        });
    }
    protected void setEventDot(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String numCurrent = editTextNum.getText().toString();
                if(!numCurrent.contains(btn.getText().toString())){
                    editTextNum.setText(editTextNum.getText().toString()+btn.getText().toString());
                }
            }
        });
    }
    protected void setEventSum(){
        btn_sum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MakeResult();
                String numCurrentString = editTextNum.getText().toString().trim();
                if(numCurrentString.equals("")){
                    currentValue = BigDecimal.valueOf(0);
                }
                else{
                    currentValue = BigDecimal.valueOf(Double.parseDouble(numCurrentString));
                }
                editTextNum.setText("");
                currentOperator = SUM;
            }
        });
    }
    protected void setEventSub(){
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numCurrentString = editTextNum.getText().toString().trim();
                if(numCurrentString.equals("") && currentOperator == NULL_OP){
                    currentValue = BigDecimal.valueOf(0);
                    editTextNum.setText("-");
                }
                else{
                    MakeResult();
                    numCurrentString = editTextNum.getText().toString().trim();
                    currentValue = BigDecimal.valueOf(Double.parseDouble(numCurrentString));
                    editTextNum.setText("");
                    currentOperator = SUB;
                }
            }
        });
    }
    protected void setEventMul(){
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeResult();
                String numCurrentString = editTextNum.getText().toString().trim();
                if(numCurrentString.equals("")){
                    currentValue = BigDecimal.valueOf(0);
                }
                else{
                    currentValue = BigDecimal.valueOf(Double.parseDouble(numCurrentString));
                }
                editTextNum.setText("");
                currentOperator = MUL;
            }
        });
    }
    protected void setEventDiv(){
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeResult();
                String numCurrentString = editTextNum.getText().toString().trim();
                if(numCurrentString.equals("")){
                    currentValue = BigDecimal.valueOf(0);
                }
                else{
                    currentValue = BigDecimal.valueOf(Double.parseDouble(numCurrentString));
                }
                editTextNum.setText("");
                currentOperator = DIV;
            }
        });
    }
    protected void MakeResult(){
        String numCurrentString = editTextNum.getText().toString().trim();
        BigDecimal newValue ;
        if(numCurrentString.equals("") && (currentOperator == MUL || currentOperator == DIV)){
            newValue = BigDecimal.valueOf(1);
        }
        else if(numCurrentString.equals("")){
            newValue = BigDecimal.valueOf(0);
        }
        else if(!numCurrentString.equals(NAN)){
            newValue = BigDecimal.valueOf(Double.parseDouble(numCurrentString));
        }
        else{
            newValue = BigDecimal.valueOf(0);
        }
        if(currentOperator == SUM){
            currentValue = currentValue.add(newValue);
        }
        else if(currentOperator == MUL){
            currentValue = currentValue.multiply(newValue);
        }
        else if(currentOperator == DIV){
            try {
                currentValue = currentValue.divide(newValue, MathContext.DECIMAL64);
            }catch (Exception e){
                editTextNum.setText(NAN);
                currentOperator = NULL_OP;
                currentValue = BigDecimal.valueOf(0);
                return;
            }
        }
        else if(currentOperator == SUB){
            currentValue = currentValue.subtract(newValue);
        }
        else
            currentValue = newValue;
        String newNumString = String.valueOf(currentValue);
        if(newNumString.contains(".")){
            while ( newNumString.endsWith("0")){
                newNumString = (String) newNumString.subSequence(0, newNumString.length()-1);
            }
        }
        if(newNumString.endsWith("."))
            newNumString = (String) newNumString.subSequence(0, newNumString.length()-1);
        editTextNum.setText(newNumString);
        currentOperator = NULL_OP;
        currentValue = BigDecimal.valueOf(0);
    }

    protected void setEventEqual(){
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numCurrentString = editTextNum.getText().toString().trim();
                BigDecimal newValue ;
                if(numCurrentString.equals("") && (currentOperator == MUL || currentOperator == DIV)){
                    newValue = BigDecimal.valueOf(1);
                }
                else if(numCurrentString.equals("")){
                    newValue = BigDecimal.valueOf(0);
                }
                else if(!numCurrentString.equals(NAN)){
                    newValue = BigDecimal.valueOf(Double.parseDouble(numCurrentString));
                }
                else{
                    newValue = BigDecimal.valueOf(0);
                }
                if(currentOperator == SUM){
                    currentValue = currentValue.add(newValue);
                }
                else if(currentOperator == MUL){
                    currentValue = currentValue.multiply(newValue);
                }
                else if(currentOperator == DIV){
                    try {
                        currentValue = currentValue.divide(newValue, MathContext.DECIMAL64);
                    }catch (Exception e){
                        editTextNum.setText(NAN);
                        currentOperator = NULL_OP;
                        currentValue = BigDecimal.valueOf(0);
                        return;
                    }
                }
                else if(currentOperator == SUB){
                    currentValue = currentValue.subtract(newValue);
                }
                else
                    currentValue = newValue;
                String newNumString = String.valueOf(currentValue);
                if(newNumString.contains(".")){
                    while ( newNumString.endsWith("0")){
                        newNumString = (String) newNumString.subSequence(0, newNumString.length()-1);
                    }
                }
                if(newNumString.endsWith("."))
                    newNumString = (String) newNumString.subSequence(0, newNumString.length()-1);
                editTextNum.setText(newNumString);
                currentOperator = NULL_OP;
                currentValue = BigDecimal.valueOf(0);
            }
        });
    }
}