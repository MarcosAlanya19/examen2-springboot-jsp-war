package com.examen.forge.app.infraestructure.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    // if (result.hasErrors()) {
    // model.addAttribute("globalErrors", result.getGlobalErrors());
    // return AppConfig.JSP_ADD_TABLE;
    // }

    // if (songService.getByTitle(song.getTitle()) != null) {
    // model.addAttribute("errorMessage", "El nombre ya está en uso.");
    // return AppConfig.JSP_ADD_TABLE;
    // }

    Long userId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);
    WaiterEntity user = waiterService.getById(userId);

    if (user != null) {
      table.setWaiter(user);
      tableService.create(table);
    }
    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Detalle de cancion por id
  // @GetMapping({ AppConfig.ROUTE_INDEX_SONG + "/{id}/detail" })
  // public String songDetail(
  // @PathVariable Long id, Model model, HttpSession session) {

  // TableEntity song = songService.getById(id);
  // model.addAttribute("song", song);
  // Long userId = (Long) session.getAttribute(AppConfig.SESSION_USER);

  // boolean isRegistration = userId != null;
  // model.addAttribute("isRegistration", isRegistration);

  // if (song != null) {
  // UserEntity creator = song.getCreator();
  // model.addAttribute("creator", creator);

  // if (userId != null) {
  // model.addAttribute("userIdInSession", userId);
  // }
  // }

  // return AppConfig.JSP_DETAIL_SONG;
  // }

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
  public String songUpdate(
      @Valid @PathVariable Long id, @ModelAttribute(AppConfig.MA_TABLE) TableEntity updatedSong,
      HttpSession session, BindingResult result, Model model) {

    if (result.hasErrors()) {
      return AppConfig.ROUTE_INDEX_TABLE + "/" + id + "/edit";
    }

    TableEntity table = tableService.getById(id);
    Long waiterId = (Long) session.getAttribute(AppConfig.SESSION_WAITER);

    if (table != null) {
      table.setName(table.getName());
      table.setNumber(updatedSong.getNumber());
      table.setNotes(updatedSong.getNotes());
      tableService.create(table);
    }

    return "redirect:/" + AppConfig.ROUTE_HOME;
  }

  // Eliminar cancion
  @PostMapping({ AppConfig.POST_INDEX_TABLE + "/{id}/delete" })
  public String releaseAndDeleteWaiter(@PathVariable Long id) {
    tableService.releaseTable(id);
    return "redirect:/" + AppConfig.ROUTE_HOME;
  }
}
