package com.exemple.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends  BaseDAO<Client> {
    public ClientDAO() throws SQLException {

        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Client object) throws SQLException {

        String req = "insert into client (nom , telephone) values (? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelepehone());


        this.preparedStatement.execute();

    }

    @Override
    public void update(Client object) throws SQLException {
        String req = "UPDATE client SET nom = ? WHERE id_client = ? AND telephone = ?;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setLong(2, object.getId_client());
        this.preparedStatement.setString(3, object.getTelepehone());
        this.preparedStatement.execute();
        this.preparedStatement.close();
    }




    @Override
    public void delete(Client object) throws SQLException {
        String req = "DELETE FROM client WHERE id_client = ? AND telephone = ?;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getId_client());
        this.preparedStatement.setString(2, object.getTelepehone());
        this.preparedStatement.execute();
    }

    @Override
    public Client getOne(Long id) throws SQLException {
        return null;
    }


    // mapping relation --> objet
    @Override
    public List<Client> getAll() throws SQLException{

        List<Client> mylist = new ArrayList<Client>();
        String req = " select * from client" ;


        this.statement = this.connection.createStatement();

       this.resultSet =  this.statement.executeQuery(req);

       while (this.resultSet.next()){

           mylist.add( new Client(this.resultSet.getLong(1) , this.resultSet.getString(2),
                   this.resultSet.getString(3)));


       }

        return mylist;
    }

}
