package team.mediasoft.mguseva.eduproject.comments;

public class CommentReader extends StringReader {
    public CommentReader() {
        super();

        this.setInputMessage("Введите ваш комментарий: ");
        this.setOutputMessage("Ваш комментарий: ");
    }
}
