package ee.itcollege.i377.team6.web;

import ee.itcollege.i377.team6.entities.VaeosaAlluvus;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "vaeosaalluvuses", formBackingObject = VaeosaAlluvus.class)
@RequestMapping("/vaeosaalluvuses")
@Controller
public class VaeosaAlluvusController {
}
