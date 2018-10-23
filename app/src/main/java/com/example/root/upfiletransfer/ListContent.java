package com.example.root.upfiletransfer;

public class ListContent {
    private String name ;
    private String filename ;

    public String getName(){
        return name ;
    }
    public String getFilename(){
        return filename ;
    }
    public void setName(String s){
        this.name = s ;
    }
    public void setFilename(String s){
        this.filename = s ;
    }

    @Override
    public String toString() {
        return "sender = " + getName() + " & " + "filename = " + getFilename();
    }
}
