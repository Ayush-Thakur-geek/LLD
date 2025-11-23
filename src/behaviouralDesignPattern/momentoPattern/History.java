package behaviouralDesignPattern.momentoPattern;


import java.util.Stack;

//Caretaker Class: Manage momentos (snapshots of the TextEditor state)
public class History {
    private final Stack<EditorMomento> history = new Stack<>();

    public void saveState(TextEditor textEditor) {
        history.push(textEditor.save());
    }

    public void undo(TextEditor textEditor) {
        if (!history.isEmpty()) {
            history.pop();
           textEditor.restore(history.peek());
        }
    }
}
