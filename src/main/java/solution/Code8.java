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
    public static void main(String[] args) {
        Code8 code8 = new Code8();

        System.out.println(code8.myAtoi("2147483648"));

    }

    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < str.length(); i++) {
            automaton.get(str.charAt(i));
        }
        return automaton.ans * automaton.sign;
    }


    public static class Automaton {
        private MachineState curState = MachineState.START;
        private int sign = 1;
        private int ans = 0;
        private int ansRemain = 0;
        private int index = 0;
        private boolean start = false;

        public void get(char c) {
            curState = transitionTable[curState.getVal()][getCol(c)];
            if (curState == MachineState.SIGN) {
                sign = c == '+' ? 1 : -1;
            } else if (curState == MachineState.READ_NUMBER) {
                ans = ans * 10 + c - '0';

                if (start) {
                    index++;
                } else {
                    if (ans != 0) {
                        start = true;
                        index++;
                    }
                }
                if (index == 10) {
                    if (ans == Integer.MAX_VALUE / 10) {
                        ansRemain = ansRemain * 10 + c - '0';
                        if (ansRemain >= 8) {
                            setAnsMax();
                        } else {
                            ans = ans * 10 + ansRemain;
                        }
                    }
                    return;
                }
                if (index == 11) {
                    setAnsMax();
                    curState = MachineState.END;
                    return;
                }


            }
        }

        private void setAnsMax() {
            if (sign == -1) {
                ans = Integer.MIN_VALUE;
                sign = 1;
            } else {
                ans = Integer.MAX_VALUE;
            }
        }

        private final MachineState[][] transitionTable = new MachineState[][]
                {
                        {MachineState.START, MachineState.SIGN, MachineState.READ_NUMBER, MachineState.END},
                        {MachineState.END, MachineState.END, MachineState.READ_NUMBER, MachineState.END},
                        {MachineState.END, MachineState.END, MachineState.READ_NUMBER, MachineState.END},
                        {MachineState.END, MachineState.END, MachineState.END, MachineState.END},
                };

        /**
         * 获取转移表下标
         *
         * @param c 字符
         * @return 下标
         */
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
    public enum MachineState {
        /**
         * 开始
         */
        START(0),
        /**
         * 变量值
         */
        SIGN(1),
        /**
         * 读数据
         */
        READ_NUMBER(2),
        /**
         * 返回结果
         */
        END(3);

        MachineState(int val) {
            this.val = val;
        }

        public int val;

        public int getVal() {
            return val;
        }
    }
}
