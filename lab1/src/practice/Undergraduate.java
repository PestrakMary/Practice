package practice;

public class Undergraduate extends Student {
    Academic tutor;
    public Undergraduate(String n, String l, String e, String tutorName) {
        super(n, l, e);
        tutor = new Academic(tutorName);
    }

    public Academic getTutor() {
        return tutor;
    }

    public void setTutor(Academic tutor) {
        this.tutor = tutor;
    }
}
