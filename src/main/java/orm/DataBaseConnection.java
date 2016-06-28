package orm;

import javafx.collections.ObservableList;
import model.*;
import org.hibernate.Session;

import java.util.Collection;


/**
 * Created by ildar on 28.06.2016.
 */
public enum  DataBaseConnection {
    INSTANCE;

    public Collection getUsersData(){
        try (Session session = HibernateSessionFactory.getSession()){
            return session.createCriteria(tableUsersEntity.class).list();
        }
    }



}
