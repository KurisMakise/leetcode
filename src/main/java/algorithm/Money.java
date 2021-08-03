package algorithm;

import java.io.UnsupportedEncodingException;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/4/12 16:56
 */
public class Money {
    private static int[] mon = {
            10010,
            100000,
            1001,

            10000,
            100,
            101,
            11,
            110011001,
            1000000,
            10101010,
            101010010,
            1230144012
    };


    private static final String[] CHS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNIT = {"", "十", "百", "千"};

    public static void main(String[] args) throws UnsupportedEncodingException {
        Money money = new Money();
        for (int num : mon) {
            System.out.println(money.chs(num));
        }
    }

    int index;

    public String chs(Integer num) {
        index = 0;
        needZero = false;
        continueZero = false;

        String s = num.toString();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            stringBuilder.insert(0, toChs(s.charAt(i)));
            index++;
        }

        return stringBuilder.toString();
    }

    private boolean needZero;
    private boolean continueZero;

    public String toChs(char c) {
        String bigUnit = getBigUnit();
        if (index % 4 == 0) {
            needZero = false;
        }
        return getVal(c) + bigUnit;
    }

    public String getZero() {
        if (continueZero && needZero) {
            return "零";
        }
        return "";
    }

    public String getVal(char c) {
        char ZERO = '0';
        if (c == ZERO) {
            continueZero = true;
            return "";
        }
        String res = CHS[(c - ZERO)] + getUnit() + getZero();
        continueZero = false;
        needZero = true;
        return res;
    }

    public String getUnit() {
        return UNIT[index % 4];
    }

    public String getBigUnit() {
        if (index % 4 != 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if ((index) / 4 % 2 == 1) {
            stringBuilder.append("万");
        }
        int times = (index / 8);
        for (int i = 0; i < times; i++) {
            stringBuilder.append("亿");
        }
        if (continueZero && needZero) {
            stringBuilder.append("零");
        }
        return stringBuilder.toString();
    }
}
