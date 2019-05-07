package practice;

public class Postgraduate extends Student {
    Academic supervisor;
    public Postgraduate(String n, String l, String e, String supervisorName) {
        super(n, l, e);
        supervisor = new Academic(supervisorName);
    }

    public Academic getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Academic supervisor) {
        this.supervisor = supervisor;
    }

}
