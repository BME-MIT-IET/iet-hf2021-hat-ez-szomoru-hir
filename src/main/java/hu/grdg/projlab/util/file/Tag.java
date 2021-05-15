package hu.grdg.projlab.util.file;

public abstract class Tag<T> {
    protected final String textData;

    public abstract T getData() throws GameLoadException;
    public abstract Tag<T> createFromData(T data);

    protected Tag(String textData) {
        this.textData = textData;
    }
}
