package org.example.web;

import org.example.data.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
  @Autowired
  private PersonRepository personRepository;

  @RequestMapping("/")
  public String home(final Model model)
  {
    model.addAttribute("persons", personRepository.findAll());

    return "home";
  }
}
