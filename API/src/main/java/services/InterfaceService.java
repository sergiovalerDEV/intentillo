package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterfaceService {
    String callNecesaryMethod(HttpServletRequest request, HttpServletResponse response);
}
