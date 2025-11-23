package behaviouralDesignPattern.momentoPattern;

public class TextEditorMain {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        History history = new History();
        textEditor.write("Hello World");
        history.saveState(textEditor);
        textEditor.write("Hello World 2");
        history.saveState(textEditor);

        history.undo(textEditor);

        System.out.println(textEditor.getContent());
    }
}
