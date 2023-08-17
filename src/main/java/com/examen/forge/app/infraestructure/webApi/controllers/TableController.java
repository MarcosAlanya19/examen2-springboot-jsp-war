package com.examen.forge.app.infraestructure.webApi.controllers;

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
public class TableController {
  @Autowired
  TableService tableService;

  @Autowired
  WaiterService waiterService;

  // Registro de mesa
  @GetMapping({ AppConfig.ROUTE_ADD_TABLE })
  public String pageAddTable(
      @ModelAttribute(AppConfig.MA_TABLE) TableEntity table, HttpSession session) {
    if (session.getAttribute(AppConfig.SESSION_WAITER) == null) {
      return "redirect:/";
    }
    return AppConfig.JSP_ADD_TABLE;
  }

  @PostMapping({ AppConfig.POST_CREATE_TABLE })
  public String newTable(
      @Valid @ModelAttribute(AppConfig.MA_TABLE) TableEntity table, BindingResult result,
      HttpSession session, Model model) {

    if (table.getName() != null) {
      table.setName(table.getName().trim());
      table.setNotes(table.getNotes().trim());
    }

    if (result.hasErrors()) {
      model.addAttribute("globalErrors", result.getGlobalErrors());
      return AppConfig.JSP_ADD_TABLE;
    }

    if (tableService.getByName(table.getName()) != null) {
      model.addAttribute("errorMessage", "El nombre ya está en uso.");
      return AppConfig.JSP_ADD_TABLE;
    }

    Long userId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    WaiterEntity user = waiterService.getById(userId);

    if (user != null) {
      table.setWaiter(user);
      tableService.create(table);
    }
    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Editar mesa
  @GetMapping({ AppConfig.ROUTE_INDEX_TABLE + "/{id}/edit" })
  public String editSong(
      @PathVariable Long id, Model model, HttpSession session) {
    TableEntity table = tableService.getById(id);
    model.addAttribute(AppConfig.MA_TABLE, table);
    Long userId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);

    boolean isCreator = userId != null && userId == table.getWaiter().getId();
    model.addAttribute("isCreator", isCreator);

    if (userId == null && !isCreator) {
      return "redirect:/";
    }

    return AppConfig.JSP_EDIT_TABLE;
  }

  @PostMapping({ AppConfig.POST_INDEX_TABLE + "/{id}/edit" })
  public String edit(
      @ModelAttribute(AppConfig.MA_TABLE) TableEntity updatedTable, @PathVariable Long id,
      HttpSession session, Model model, BindingResult result) {

    // Validar el formulario manualmente
    if (updatedTable.getName() == null || updatedTable.getName().length() < 2) {
      result.rejectValue("name", "table.name.invalid", "El nombre debe tener al menos 2 caracteres");
    }

    if (updatedTable.getNumber() == null || updatedTable.getNumber() < 1 || updatedTable.getNumber() > 10) {
      result.rejectValue("number", "table.number.invalid", "El número debe estar entre 1 y 10");
    }

    if (tableService.getByName(updatedTable.getName()) != null) {
      // Verificar si el nombre está en uso, pero no es el nombre actual de esta mesa
      TableEntity existingTable = tableService.getByName(updatedTable.getName());
      if (!existingTable.getId().equals(id)) {
        result.rejectValue("name", "table.name.duplicate", "El nombre ya está en uso.");
      }
    }

    if (result.hasErrors()) {
      // Manejar errores de validación
      return AppConfig.JSP_EDIT_TABLE;
    }

    TableEntity table = tableService.getById(id);
    Long userId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    boolean isCreator = userId != null && userId == table.getWaiter().getId();

    if (!isCreator) {
      return "redirect:/";
    }

    if (table != null) {
      table.setName(updatedTable.getName().trim());
      table.setNumber(updatedTable.getNumber());
      table.setNotes(updatedTable.getNotes().trim());
      tableService.create(table);
    }

    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Eliminar cancion
  @PostMapping({ AppConfig.POST_INDEX_TABLE + "/{id}/delete" })
  public String releaseAndDeleteWaiter(@PathVariable Long id, HttpSession session) {
    Long userId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    boolean isRegistration = userId != null;

    if (!isRegistration) {
      return "redirect:/";
    }

    tableService.releaseTable(id);
    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Detalle de cancion por id
  @GetMapping({ AppConfig.ROUTE_INDEX_TABLE + "/{id}/detail" })
  public String songDetail(
      @PathVariable Long id, Model model, HttpSession session) {

    Long waiterId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    boolean isRegistration = waiterId != null;

    if (!isRegistration) {
      return "redirect:/";
    }

    TableEntity table = tableService.getById(id);
    model.addAttribute("table", table);
    model.addAttribute("isRegistration", isRegistration);

    if (waiterId != null) {
      model.addAttribute("userIdInSession", waiterId);
    }

    return AppConfig.JSP_DETAIL_TABLE;
  }
}
