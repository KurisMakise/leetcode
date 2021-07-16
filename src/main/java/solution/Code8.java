package solution;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2019/4/28 19:53
 */
public class Code8 {
    private static final char POSITIVE = '+';
    private static final char NEGATIVE = '-';
    private static final int MAX = (int) Math.pow(2, 31);
    private static final int MIN = -MAX;

    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        char sign;
        if (s.charAt(0) == POSITIVE) {
            sign = POSITIVE;
            s = s.substring(1);
        } else if (s.charAt(0) == NEGATIVE) {
            sign = NEGATIVE;
            s = s.substring(1);
        } else {
            sign = POSITIVE;
        }

        int[] position = getNumPosition(s);
        if (position[0] == -1) {
            return 0;
        }
        return combine(sign, s.substring(position[0], position[1] + 1));
//        Automaton automaton = new Automaton();
//        for (int i = 0; i < s.length(); i++) {
//            automaton.add(s.charAt(i));
//        }
//        return automaton.getAnswer();
    }

    public static void main(String[] args) {
        Code8 code8 = new Code8();
        System.out.println(code8.myAtoi("91283472332"));
        System.out.println(code8.myAtoi("-91283472332"));
    }


    public int combine(int sign, String number) {
        int num;
        try {
            num = Integer.parseInt(number);
        } catch (Exception e) {
            if (sign == POSITIVE) {
                return MAX;
            } else {
                return MIN - 1;
            }
        }

        if (sign == POSITIVE) {
            return num;
        } else {
            return -num;
        }
    }

    public int[] getNumPosition(String s) {
        char tmp;
        int start = -1, end = -1;
        for (int i = 0; i < s.length(); i++) {
            tmp = s.charAt(i);
            if (isNumber(tmp)) {
                if (start == -1) {
                    start = i;
                }
                end = i;
            } else {
                break;
            }
        }

        return new int[]{start, end};
    }

    public boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 自动机解法
     */
    class Automaton {
        private int ans;
        private int sign = 1;
        private MachineState curState = MachineState.START;


        private void add(char c) {
            curState = transitionState[curState.val][getCol(c)];
            if (curState == MachineState.READ_NUMBER) {
                if (sign == -1 && (ans * -1 < (Integer.MIN_VALUE + c - '0') / 10)) {
                    ans = Integer.MIN_VALUE;
                    sign = 1;
                    curState = MachineState.END;
                    return;
                } else if (sign == 1 && ans > (Integer.MAX_VALUE - c + '0') / 10) {
                    ans = Integer.MAX_VALUE;
                    curState = MachineState.END;
                    return;
                }
                ans = ans * 10 + c - '0';
            } else if (curState == MachineState.SIGN) {
                if (c == '-') {
                    sign = -1;
                }
            }
        }

        public int getAnswer() {
            return ans * sign;
        }

        private final MachineState[][] transitionState = new MachineState[][]{
                {MachineState.START, MachineState.SIGN, MachineState.READ_NUMBER, MachineState.END},
                {MachineState.END, MachineState.END, MachineState.READ_NUMBER, MachineState.END},
                {MachineState.END, MachineState.END, MachineState.READ_NUMBER, MachineState.END},
                {MachineState.END, MachineState.END, MachineState.END, MachineState.END}
        };

        private int getCol(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '-' || c == '+') {
                return 1;
            } else if (c >= '0' && c <= '9') {
                return 2;
            } else {
                return 3;
            }
        }

    }

    /**
     * 状态机
     */
    enum MachineState {
        /**
         * 开始
         */
        START(0),
        /**
         * 符号
         */
        SIGN(1),
        /**
         * 读数
         */
        READ_NUMBER(2),
        /**
         * 结束
         */
        END(3);

        private int val;

        MachineState(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

    }

}
