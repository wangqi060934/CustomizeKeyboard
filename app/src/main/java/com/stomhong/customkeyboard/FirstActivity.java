package com.stomhong.customkeyboard;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stomhong.library.KeyboardTouchListener;
import com.stomhong.library.KeyboardUtil;
import com.stomhong.library.TextViewUtil;

public class FirstActivity extends AppCompatActivity {

    private EditText normalEd;
    private EditText specialEd;
    private EditText specialEd2;
    private TextView otherText;
    private KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        normalEd = (EditText) findViewById(R.id.normal_ed);
        specialEd = (EditText) findViewById(R.id.special_ed);
        specialEd2 = (EditText) findViewById(R.id.special_ed2);
        otherText = (TextView) findViewById(R.id.other_text);
        otherText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specialEd2.requestFocus();
            }
        });

        initMoveKeyBoard();


    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        //处理返回键
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 ) {
//            if(keyboardUtil.isShow){
//                keyboardUtil.hideSystemKeyBoard();
//                keyboardUtil.hideAllKeyBoard();
//                keyboardUtil.hideKeyboardLayout();
//            }else {
//                return super.onKeyDown(keyCode, event);
//            }
//
//            return false;
//        } else
//            return super.onKeyDown(keyCode, event);
//    }

    private void initMoveKeyBoard() {
        keyboardUtil = new KeyboardUtil(this);
        keyboardUtil.setOtherEdittext(normalEd);
        // monitor the KeyBarod state
        keyboardUtil.setKeyBoardStateChangeListener(new KeyBoardStateListener());
        // monitor the finish or next Key
        keyboardUtil.setInputOverListener(new inputOverListener());
        specialEd.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC));
        specialEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    keyboardUtil.showKeyBoardLayout(specialEd, KeyboardUtil.INPUTTYPE_ABC);
                }
            }
        });
        specialEd2.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC));
        specialEd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    keyboardUtil.showKeyBoardLayout(specialEd2, KeyboardUtil.INPUTTYPE_ABC);
                }
            }
        });
    }

    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }
}
