package services;

import model.dao.ExtraDAO;
import model.entities.Extra;
import model.motorsql.FactoryMotorSQL;
import model.motorsql.MotorSQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ExtraService implements InterfaceService {

    final String originalPath = "/extra";

    final String getAllPath = originalPath + "/getall";
    final String getByIdPath = originalPath + "/getbyid";
    //final String addPath = originalPath + "/add";

    @Override
    public String callNecesaryMethod(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getPathInfo();

        switch (path){
            case getAllPath:
                return getAll();
            case getByIdPath:
                int id = Integer.parseInt(request.getParameter("ID"));
                return getById(id);
            default:
                return "The method trying to access does not exist";
        }
    }

    String getAll(){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        motorSQL.connect();
        ExtraDAO extraDAO = new ExtraDAO(motorSQL);
        ArrayList<Extra> arrayList  = extraDAO.getAll();
        motorSQL.disconnect();

        return Extra.toJSON(arrayList);
    }

    String getById(int id){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        motorSQL.connect();
        ExtraDAO extraDAO = new ExtraDAO(motorSQL);
        ArrayList<Extra> arrayList = extraDAO.getById(id);
        motorSQL.disconnect();

        return Extra.toJSON(arrayList);
    }
}
