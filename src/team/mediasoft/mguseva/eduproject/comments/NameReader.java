package team.mediasoft.mguseva.eduproject.comments;

public class NameReader extends StringReader {

    public NameReader() {
        super();

        this.setInputMessage("Введите своё имя: ");
        this.setOutputMessage("Ваше имя: ");
    }
}
