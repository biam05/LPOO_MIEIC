import java.util.ArrayList;

public class Folder extends Node{
    protected ArrayList<Folder> folders;
    protected ArrayList<File> files;
    protected NameFormatter nameFormatter;

    public Folder() {
        super();
        this.folders = new ArrayList<>();
        this.files = new ArrayList<>();
        resetNumbering(getNumber()+1);
    }

    public Folder(Folder parent, String name) throws DuplicateNameException {
        super(parent, name);
        this.folders = new ArrayList<>();
        this.files = new ArrayList<>();
        for(Folder folder : folders){
            if(folder.getName() == name && folder.getParent() == parent)
                throw new DuplicateNameException();
        }
        for(File file : files){
            if(file.getName() == name && file.getParent() == parent)
                throw new DuplicateNameException();
        }
        this.parent.addFolder(this);
        resetNumbering(getNumber()+1);
    }

    @Override
    public int getSize() {
        int total = 0;
        Node[] children = getChild();
        for(Node node : children){
            total += node.getSize();
        }
        return total;
    }


    public void addFolder(Folder f){
        folders.add(f);
    }

    public void addFiles(File f){
        files.add(f);
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public Node[] getChild() {
        Node[] nodes = new Node[folders.size() + files.size()];
        int i = 0;
        for(Folder folder : folders){
            nodes[i] = folder;
            i++;
        }
        for(File file : files){
            nodes[i] = file;
            i++;
        }
        return nodes;
    }

    public Node getChildByName(String name) {
        Node[] children = getChild();
        for(Node node : children){
            if(node.getName() == name)
                return node;
        }
        return null;
    }

    public NameFormatter getNameFormatter() {
        return nameFormatter;
    }

    public void setNameFormatter(NameFormatter nameFormatter) {
        this.nameFormatter = nameFormatter;
    }

}
