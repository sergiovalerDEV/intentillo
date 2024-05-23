package services;

import model.dao.ShoppingcartItemDAO;
import model.entities.ShoppingcartItem;
import model.motorsql.FactoryMotorSQL;
import model.motorsql.MotorSQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ShoppingcartItemService implements InterfaceService {


    final String originalPath = "/shoppingcartitem";

    final String getAllPath = originalPath + "/getall";
    final String getByShoppingcartPath = originalPath + "/getbyshoppingcart";
    final String addPath = originalPath + "/add";
    final String updatePath = originalPath + "/update";

    @Override
    public String callNecesaryMethod(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getPathInfo();

        int shoppingcart;
        int item;

        switch (path){
            case getAllPath:
                return getAll();
            case getByShoppingcartPath:
                shoppingcart = Integer.parseInt(request.getParameter("SHOPPINGCART"));
                return getByShoppingcart(shoppingcart);
            case addPath:
                shoppingcart = Integer.parseInt(request.getParameter("SHOPPINGCART"));
                item=  Integer.parseInt(request.getParameter("ITEM"));
                //int ammount = Integer.parseInt(request.getParameter("AMMOUNT"));
                ShoppingcartItem shoppingcartItem = new ShoppingcartItem(shoppingcart, item, 1);
                add(shoppingcartItem);
                return "";
            case updatePath:
                shoppingcart = Integer.parseInt(request.getParameter("SHOPPINGCART"));
                item = Integer.parseInt(request.getParameter("ITEM"));
                int ammount = Integer.parseInt(request.getParameter("AMMOUNT"));
                shoppingcartItem = new ShoppingcartItem(shoppingcart, item, ammount);
                update(shoppingcartItem);
                return "";
            default:
                System.out.println("The method trying to access not exists");
                return "";
        }
    }

    public String getAll(){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        ShoppingcartItemDAO shoppingcartItemDAO = new ShoppingcartItemDAO(motorSQL);
        ArrayList arrayList = shoppingcartItemDAO.getAll();

        return ShoppingcartItem.toJSON(arrayList);
    }

    public String getByShoppingcart(int shoppingcart) {
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        ShoppingcartItemDAO shoppingcartItemDAO = new ShoppingcartItemDAO(motorSQL);
        ArrayList arrayList = shoppingcartItemDAO.getByShoppingcart(shoppingcart);

        return  ShoppingcartItem.toJSON(arrayList);
    }

    void add(ShoppingcartItem shoppingcartItem) {
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        ShoppingcartItemDAO shoppingcartItemDAO = new ShoppingcartItemDAO(motorSQL);
        shoppingcartItemDAO.add(shoppingcartItem);
    }

    void update(ShoppingcartItem shoppingcartItem){
        MotorSQL motorSQL = FactoryMotorSQL.getInstance("MOTORPOSTGRE");
        ShoppingcartItemDAO shoppingcartItemDAO = new ShoppingcartItemDAO(motorSQL);
        shoppingcartItemDAO.update(shoppingcartItem);
    }
}
