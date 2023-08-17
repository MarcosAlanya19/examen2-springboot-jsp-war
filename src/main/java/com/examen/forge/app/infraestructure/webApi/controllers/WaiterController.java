package com.examen.forge.app.infraestructure.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.examen.forge.app.application.services.TableService;
import com.examen.forge.app.application.services.WaiterService;
import com.examen.forge.app.domain.entities.TableEntity;
import com.examen.forge.app.domain.entities.WaiterEntity;
import com.examen.forge.config.AppConfig;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class WaiterController {
  @Autowired
  WaiterService waiterService;

  @Autowired
  TableService tableService;

  // Redireccion a home
  @GetMapping({ AppConfig.ROUTE_HOME })
  public String pageHome(HttpSession session, Model model) {
    Long waiterId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    WaiterEntity waiter = waiterService.getById(waiterId);
    List<TableEntity> tableByWaiter = tableService.getByWaiter(waiter);

    model.addAttribute("tables", tableByWaiter);

    if (waiterId != null) {
      WaiterEntity user = waiterService.getById(waiterId);
      model.addAttribute(AppConfig.MA_USER, user);
    }

    return AppConfig.JSP_HOME;
  }

  // Otras mesas
  @GetMapping({ AppConfig.ROUTE_OTHER_TABLE })
  public String pageOthers(HttpSession session, Model model) {
    Long waiterId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    List<TableEntity> availableTables = tableService.getAvailableTables();

    model.addAttribute("tables", availableTables);

    if (waiterId != null) {
      WaiterEntity user = waiterService.getById(waiterId);
      model.addAttribute(AppConfig.MA_USER, user);
    }

    return AppConfig.JSP_OTHER_TABLE;
  }

  @PostMapping({ AppConfig.ROUTE_INDEX_TABLE + "/{id}/assign" })
  public String assignTable(@PathVariable Long id, HttpSession session) {
    Long waiterId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    WaiterEntity loggedInWaiter = (WaiterEntity) waiterService.getById(waiterId) ;
    if (loggedInWaiter != null) {
      tableService.assignTableToWaiter(id, loggedInWaiter);
    }
    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Registro de usuario
  @GetMapping({ AppConfig.ROUTE_REGISTRATION })
  public String pageRegister(@ModelAttribute(AppConfig.MA_USER) WaiterEntity user) {
    return AppConfig.JSP_REGISTRATION;
  }

  @PostMapping({ AppConfig.POST_CREATE_USER })
  public String newUser(
      @Valid @ModelAttribute(AppConfig.MA_USER) WaiterEntity user, BindingResult result,
      HttpSession session, Model model) {

    if (result.hasErrors()) {
      model.addAttribute("globalErrors", result.getGlobalErrors());
      return AppConfig.JSP_REGISTRATION;
    }

    if (waiterService.existsByEmail(user.getEmail())) {
      model.addAttribute("emailError", "El email ya está en uso");
      return AppConfig.JSP_REGISTRATION;
    }

    if (!user.getPassword().equals(user.getConfirm())) {
      model.addAttribute("confirmError", "Las contraseñas no coinciden");
      return AppConfig.JSP_REGISTRATION;
    }

    WaiterEntity newUser = waiterService.create(user);
    session.setAttribute(AppConfig.SESSION_WAITER, newUser.getId());
    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Logueo de usuario
  @GetMapping
  public String pageLogin() {

    return AppConfig.JSP_LOGIN;
  }

  @PostMapping({ AppConfig.POST_LOGIN_USER })
  public String loginUser(@ModelAttribute WaiterEntity user, Model model, HttpSession session) {
    String email = user.getEmail();
    String password = user.getPassword();

    if (email.trim().isEmpty()) {
      model.addAttribute("errorEmail", "El correo no puede estar vacia.");
      return AppConfig.JSP_LOGIN;
    }

    if (password.trim().isEmpty()) {
      model.addAttribute("errorPassword", "La contraseña no puede estar vacia.");
      return AppConfig.JSP_LOGIN;
    }

    if (waiterService.authenticateUser(email, password)) {
      WaiterEntity userByEmail = waiterService.getByEmail(email);
      session.setAttribute(AppConfig.SESSION_WAITER, userByEmail.getId());
      return "redirect:/" + AppConfig.ROUTE_HOME;
    } else {
      model.addAttribute("error", "Credenciales no válidas. Inténtalo de nuevo.");
      return AppConfig.JSP_LOGIN;
    }
  }

  // Eliminacion de session
  @GetMapping("logout")
  public String logoutUser(HttpSession session) {
    session.removeAttribute(AppConfig.SESSION_WAITER);
    return "redirect:/";
  }
}
