public class FileSystem {
    protected String type;
    protected Folder root;
    protected NameFormatter nameFormatter;

    public FileSystem(String type) {
        this.type = type;
        this.root = new Folder();
    }

    public String getType() {
        return type;
    }

    public Folder getRoot() {
        return root;
    }

    public void setNameFormatter(NameFormatter nameFormatter) {
        this.nameFormatter = nameFormatter;
        this.root.setNameFormatter(nameFormatter);
    }
}
