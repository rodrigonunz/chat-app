package br.com.chat.profile;

import br.com.chat.profile.model.Profile;
import br.com.chat.profile.model.Status;

import java.util.Date;
import java.util.List;

public class MainClass {

    public static void main(String[] args){
        Dao dao = new Dao(true);

        List<Status> statusByProfileId = dao.getStatusByProfileId("23423", 3);

        Status status = new Status();
        status.setText("Domingo !!!!" + new Date().toString());
        dao.add("5e262b9a-a7dc-48cb-b95b-a7fd3c082bf9", status);

       /* Profile profile = new Profile();
        dao.add(profile);*/
    }


}
