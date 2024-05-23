package services;

import model.dao.ItemDAO;
import model.entities.Item;
import model.motorsql.FactoryMotorSQL;
import model.motorsql.MotorSQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ItemService implements InterfaceService {


    final String originalPath = "/item";

    final String getAllPath = originalPath + "/getall";
    final String getByCategoryPath = originalPath + "/getbycategory";
    final String getByIdPath = originalPath + "/getbyid";
    //final String addPath = originalPath + "/add";

    @Override
    public String callNecesaryMethod(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getPathInfo();

        switch (path){
            case getAllPath:
                return getAll();
            case getByCategoryPath:
                int category = Integer.parseInt(request.getParameter("CATEGORY"));
                return getByCategory(category);
            case getByIdPath:
                int id = Integer.parseInt(request.getParameter("ID"));
                return getById(id);
            default:
                return "The method trying to access not exists";
        }
    }

    String getAll(){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        motorSQL.connect();
        ItemDAO itemDAO = new ItemDAO(motorSQL);
        ArrayList arrayList  = itemDAO.getAll();
        motorSQL.disconnect();

        return Item.toJSON(arrayList);
    }

    String getByCategory(int category){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        motorSQL.connect();
        ItemDAO itemDAO = new ItemDAO(motorSQL);
        ArrayList arrayList  = itemDAO.getByCategory(category);
        motorSQL.disconnect();

        return Item.toJSON(arrayList);
    }

    String getById(int id){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        motorSQL.connect();
        ItemDAO itemDAO = new ItemDAO(motorSQL);
        ArrayList arrayList = itemDAO.getById(id);
        motorSQL.disconnect();

        return Item.toJSON(arrayList);
    }
}
