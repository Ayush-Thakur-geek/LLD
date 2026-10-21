package behaviouralDesignPattern;

public class VisitorDP {
    public static void main(String[] args) {
        Visitor me = new Me();
        Friends f = new Friends();
        f.hostTheVisitor(me);
    }
}

interface Visitor {
    public void visit(Friends friends);
    public void visit(Family family);
}

interface Visited {
    public void hostTheVisitor(Visitor visitor);
}

class Me implements Visitor {

    @Override
    public void visit(Friends friends) {
        System.out.println("Visited to my friends");
    }

    @Override
    public void visit(Family family) {
        System.out.println("Visited to my family");
    }

}

class Friends implements Visited {

    @Override
    public void hostTheVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}

class Family implements Visited {
    @Override
    public void hostTheVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}