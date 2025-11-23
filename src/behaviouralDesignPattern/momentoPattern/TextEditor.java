package behaviouralDesignPattern.momentoPattern;

public class TextEditor {
    private String content;

    public void write(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public EditorMomento save() {
        return new EditorMomento(content);
    }

    public void restore(EditorMomento momento){
        content = momento.getContent();
    }
}
