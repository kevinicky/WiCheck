package id.rendesvouz.wicheckapps.Model;

public class Questions {
    private int color;
    private String pertayaan;
    private String jawabanYes;
    private String jawabanNo;
    private String answerYes;
    private String answerNo;
    private int step;

    public String getAnswerYes() {
        return answerYes;
    }

    public void setAnswerYes(String answerYes) {
        this.answerYes = answerYes;
    }

    public String getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(String answerNo) {
        this.answerNo = answerNo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPertayaan() {
        return pertayaan;
    }

    public void setPertayaan(String pertayaan) {
        this.pertayaan = pertayaan;
    }

    public String getJawabanYes() {
        return jawabanYes;
    }

    public void setJawabanYes(String jawabanYes) {
        this.jawabanYes = jawabanYes;
    }

    public String getJawabanNo() {
        return jawabanNo;
    }

    public void setJawabanNo(String jawabanNo) {
        this.jawabanNo = jawabanNo;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
