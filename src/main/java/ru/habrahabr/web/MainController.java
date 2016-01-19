package ru.habrahabr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import ru.habrahabr.model.Contact;
import ru.habrahabr.services.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
public class MainController {
    @Autowired private ContactService contactService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("partials/all");
        modelAndView.addObject("contacts", contactService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showAddForm() {
        return new ModelAndView("partials/add_form", "contact", new Contact());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addContact(@ModelAttribute("contact") @Valid Contact contact, BindingResult result) {
        if (contact.getId() == null) {
            contactService.add(contact);
        }
        else {
            contactService.update(contact);
        }

        if (result.hasErrors()) {
            return new ModelAndView("partials/add_form", "numberError", result.getFieldError("number").getDefaultMessage());
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditForm(@RequestParam(required = true) int id) {
        return new ModelAndView("partials/add_form", "contact", contactService.get(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public int deleteContact(@RequestParam(required = true) int id) {
        contactService.remove(id);

        return id;
    }

    @RequestMapping(value = "/lang={lang}", method = RequestMethod.GET)
    public String welcome(HttpServletRequest request, HttpServletResponse response, @PathVariable("lang") String lang) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang));

        return lang;
    }

}
