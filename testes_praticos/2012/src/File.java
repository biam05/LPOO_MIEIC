import java.util.ArrayList;

public class File extends Node {

    public File(Folder parent, String name) throws DuplicateNameException {
        super(parent, name);
        for(Folder folder : parent.getFolders()){
            if(folder.getName() == name && folder.getParent() == parent)
                throw new DuplicateNameException();
        }
        for(File file : parent.getFiles()){
            if(file.getName() == name && file.getParent() == parent)
                throw new DuplicateNameException();
        }
        this.parent.addFiles(this);
        resetNumbering(getNumber()+1);
    }

    public File(Folder parent, String name, int size) throws DuplicateNameException {
        super(parent, name, size);
        for(Folder folder : parent.getFolders()){
            if(folder.getName() == name && folder.getParent() == parent)
                throw new DuplicateNameException();
        }
        for(File file : parent.getFiles()){
            if(file.getName() == name && file.getParent() == parent)
                throw new DuplicateNameException();
        }
        this.parent.addFiles(this);
        resetNumbering(getNumber()+1);
    }

    @Override
    public int getSize() {
        return size;
    }

    public String getPath() {
        String str = "";
        Folder path = getParent();
        ArrayList<Folder> pathFolders = new ArrayList<>();
        while(path.getParent() != null){
            pathFolders.add(path);
            path = path.getParent();
        }
        NameFormatter nameFormatter = path.getNameFormatter();
        for(Folder f : pathFolders)
            str+=nameFormatter.getSeparator()+f.getName();
        str+=nameFormatter.getSeparator() + name;
        return str;
    }

}
