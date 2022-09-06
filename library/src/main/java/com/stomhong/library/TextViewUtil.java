package com.stomhong.library;

import android.os.Build;
import android.text.InputFilter;
import android.widget.EditText;

import java.lang.reflect.Field;

public class TextViewUtil {
    /**
     * 获取TextView最大长度限制
     */
    public static int getTextViewMaxLength(EditText ed) {
        if (ed == null || ed.getFilters() == null) {
            return -1;
        }
        for (InputFilter filter : ed.getFilters()) {
            if (filter instanceof InputFilter.LengthFilter) {
                InputFilter.LengthFilter lengthFilter = (InputFilter.LengthFilter) filter;
                return getLnegthFilterMaxLimit(lengthFilter);
            }
        }
        return -1;
    }

    private static int getLnegthFilterMaxLimit(InputFilter.LengthFilter lengthFilter) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return lengthFilter.getMax();
        } else {
            try {
                Class clz = Class.forName("android.text.InputFilter.LengthFilter");
                Field field = clz.getDeclaredField("mMax");
                field.setAccessible(true);
                return (int) field.get(lengthFilter);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }

    }
}
