package com.examen.forge.config;

import org.springframework.stereotype.Component;

@Component
public class AppConfig {
  /* ----------------------------- Rutas creadas ----------------------------- */
  public static final String ROUTE_INDEX_TABLE = "table";
  public static final String ROUTE_HOME = "home";
  public static final String ROUTE_LOGOUT_USER = "logout";
  public static final String ROUTE_REGISTRATION = "registration";
  public static final String ROUTE_ADD_TABLE = "table/new";
  public static final String ROUTE_OTHER_TABLE = "table/other";
    public static final String ROUTE_ALL_TABLE = "table/all";

  /* ------------------------------ Archivos JSP ----------------------------- */
  public static final String JSP_LOGIN = "index.jsp";
  public static final String JSP_HOME = "home-page.jsp";
  public static final String JSP_REGISTRATION = "new-waiter-page.jsp";

  public static final String JSP_ADD_TABLE = "new-table-page.jsp";
  public static final String JSP_EDIT_TABLE = "edit-table-page.jsp";
  public static final String JSP_DETAIL_TABLE = "detail-table-page.jsp";
  public static final String JSP_OTHER_TABLE = "others-table-page.jsp";
    public static final String JSP_ALL_TABLE = "all-table-page.jsp";


  /* ----------------------------- Creacion POST ----------------------------- */
  /* USER */
  public static final String POST_LOGIN_USER = "user/login";
  public static final String POST_CREATE_USER = "user/create";

  /* SONG */
  public static final String POST_INDEX_TABLE = "table";
  public static final String POST_CREATE_TABLE = "table/new";

  /* ------------------------ ModelAttributes creados ------------------------ */
  public static final String MA_USER = "user";
  public static final String MA_TABLE = "table";

  /* ------------------------------- SESSION --------------------------------- */
  public static final String SESSION_WAITER = "userId";
}
