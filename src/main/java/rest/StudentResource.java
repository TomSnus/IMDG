package rest;

import Server.ServerRest;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.mysql.fabric.Server;
import db.DbHelper;
import de.othr.vs.data.entity.Adresse;
import de.othr.vs.data.entity.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by stt44293 on 20.06.2017.
 */
@Path("studentaffairs")
public class StudentResource {

    private DbHelper dbHelper = new DbHelper();

    /**
     *
     * DB Operations
     *
     */


    /**
     * Select Student from Database by ID
     * @param id
     * @return Student Object
     */
    @GET
    @Path("studentdb/{id}")
    public Student getStudentById(@PathParam("id") int id) {
        Student returnStudent = null;
        if(ServerRest.mapStudents.containsKey(id)) {
            System.out.println("Student found in Cache");
            return ServerRest.mapStudents.get(id);
        }


        try {

            Statement stmt = dbHelper.connection.createStatement();
            String query = "SELECT matrikelNr, vorname, nachname, ects, strasse, ort FROM Student WHERE matrikelNr = " + id;
            ResultSet r = stmt.executeQuery(query);
            r.first();
            returnStudent
                    = new Student(id,
                    r.getString("vorname") , r.getString("nachname"),
                    r.getInt("ects"),
                    new Adresse(r.getString("strasse"), r.getString("ort")));
            dbHelper.connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }



        ServerRest.mapStudents.put(id, returnStudent);
        System.out.println(ServerRest.mapStudents.get(id));
        return  returnStudent;
    }
}
