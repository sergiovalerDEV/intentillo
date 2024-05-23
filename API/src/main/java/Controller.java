import services.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="Controller", urlPatterns = {"/controller/*"})
public class Controller extends HttpServlet {
    final String USER = "user";
    final String ITEM = "item";
    final String CATEGORY = "category";
    final String ITEMCATEGORY = "itemcategory";
    final String SHOPPINGCART = "shoppingcart";
    final String SHOPPINGCARTITEM = "shoppingcartitem";
    final String SHOPPINGCARTITEMEXTRA = "shoppingcartitemextra";
    final String EXTRA = "extra"; // Nuevo servicio

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/plain;charset=UTF-8");

        String startWith = request.getPathInfo().split("/")[1];

        System.out.println("Path: " + request.getPathInfo() + ", starts with: " + startWith);

        PrintWriter out = response.getWriter();

        String resp;

        switch (startWith) {
            case USER:
                UserService userService = new UserService();
                resp = userService.callNecesaryMethod(request, response);
                out.println(resp);
                break;
            case ITEM:
                ItemService itemService = new ItemService();
                resp = itemService.callNecesaryMethod(request, response);
                out.println(resp);
                break;
            case CATEGORY:
                CategoryService categoryService = new CategoryService();
                resp = categoryService.callNecesaryMethod(request, response);
                out.println(resp);
                break;
            case ITEMCATEGORY:
                ItemCategoryService itemCategoryService = new ItemCategoryService();
                resp = itemCategoryService.callNecesaryMethod(request, response);
                out.println(resp);
                break;
            case SHOPPINGCART:
                ShoppingcartService shoppingcartService = new ShoppingcartService();
                resp = shoppingcartService.callNecesaryMethod(request, response);
                out.println(resp);
                break;
            case SHOPPINGCARTITEM:
                ShoppingcartItemService shoppingcartItemService = new ShoppingcartItemService();
                resp = shoppingcartItemService.callNecesaryMethod(request, response);
                out.println(resp);
                break;
            case SHOPPINGCARTITEMEXTRA:
                ShoppingcartItemExtraService shoppingcartItemExtraService = new ShoppingcartItemExtraService(); // Nuevo servicio
                resp = shoppingcartItemExtraService.callNecesaryMethod(request, response); // Nuevo servicio
                out.println(resp); // Nuevo servicio
                break; // Nuevo servicio
            case EXTRA:
                ExtraService extraService = new ExtraService(); // Nuevo servicio
                resp = extraService.callNecesaryMethod(request, response); // Nuevo servicio
                out.println(resp); // Nuevo servicio
                break; // Nuevo servicio
            default:
                out.println("The method trying to access does not exist");
        }
    }
}
