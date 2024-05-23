package services;

import model.dao.CategoryDAO;
import model.entities.Category;
import model.motorsql.FactoryMotorSQL;
import model.motorsql.MotorSQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class CategoryService implements InterfaceService {
    final String originalPath = "/category";

    final String getAllPath = originalPath + "/getall";

    @Override
    public String callNecesaryMethod(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getPathInfo();

        switch (path){
            case getAllPath:
                return getAll();
            default:
                return "The method trying to access not exists";
        }
    }

    public String getAll(){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        CategoryDAO categoryDAO = new CategoryDAO(motorSQL);
        ArrayList arrayList = categoryDAO.getAll();

        return Category.toJSON(arrayList);
    }
}
