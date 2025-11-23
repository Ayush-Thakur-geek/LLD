package behaviouralDesignPattern.momentoPattern;


//Momento Class: Stores the internal state of the TextEditor
public class EditorMomento {
    private final String content;

    public EditorMomento(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}
