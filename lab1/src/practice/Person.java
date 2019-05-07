package practice;

public class Person implements Comparable<Person>{
    String name;
    public Person(String n){
        super();
        name = n;
    }
    public String getName(){
        return name;
    }
    public void setName (String n){
        name = n;
    }
    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

}

