package ee.itcollege.i377.team6.web;

import ee.itcollege.i377.team6.entities.PiiriloiguHaldaja;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "piiriloiguhaldajas", formBackingObject = PiiriloiguHaldaja.class)
@RequestMapping("/piiriloiguhaldajas")
@Controller
public class PiiriloiguHaldajaController {
}
