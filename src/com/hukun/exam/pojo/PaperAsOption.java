package com.hukun.exam.pojo;

public class PaperAsOption {

    /**
     * id : 29
     * examid : 34
     * title : 1+1
     * options_ : {"A":"1","B":"2","C":"3","D":"4"}
     * answer : B
     */

    private int id;
    private int examid;
    private String title;
    private OptionsBean options_;
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OptionsBean getOptions_() {
        return options_;
    }

    public void setOptions_(OptionsBean options_) {
        this.options_ = options_;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static class OptionsBean {
        /**
         * A : 1
         * B : 2
         * C : 3
         * D : 4
         */

        private String A;
        private String B;
        private String C;
        private String D;

        public String getA() {
            return A;
        }

        public void setA(String A) {
            this.A = A;
        }

        public String getB() {
            return B;
        }

        public void setB(String B) {
            this.B = B;
        }

        public String getC() {
            return C;
        }

        public void setC(String C) {
            this.C = C;
        }

        public String getD() {
            return D;
        }

        public void setD(String D) {
            this.D = D;
        }
    }
}
