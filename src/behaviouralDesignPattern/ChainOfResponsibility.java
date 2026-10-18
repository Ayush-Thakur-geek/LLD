package behaviouralDesignPattern;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Approver supervisor = new Supervisor();
        Approver manager = new Manager();
        Approver director = new Director();
        supervisor.setNextApprover(manager);
        manager.setNextApprover(director);
        supervisor.processLeaveRequest(10);
    }
}

abstract class Approver {
    protected Approver nextApprover;
    // Set the next handler in the chain
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }
    // Abstract method to process the leave request
    public abstract void processLeaveRequest(int leaveDays);
}

class Supervisor extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 3) System.out.println("Leave approved");
        else if (this.nextApprover != null) {
            this.nextApprover.processLeaveRequest(leaveDays);
        } else System.out.println("Leave not approved");
    }
}

class Manager extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 10) System.out.println("Leave approved");
        else if (this.nextApprover != null) {
            this.nextApprover.processLeaveRequest(leaveDays);
        }
        else System.out.println("Leave not approved");
    }
}

class Director extends Approver {
    @Override
    public void processLeaveRequest(int leaveDays) {
        if (leaveDays <= 14) System.out.println("Leave approved");
        else if (this.nextApprover != null) {
            this.nextApprover.processLeaveRequest(leaveDays);
        } else System.out.println("Leave not approved");
    }
}