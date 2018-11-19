package br.com.chat.profile;

public class Service {

    protected Dao dao;

    public Service(){
        this.dao = new Dao(false);
    }

}
