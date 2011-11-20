package ee.itcollege.i377.team6.web;

import ee.itcollege.i377.team6.entities.VahtkonndPiiriloigul;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "vahtkonndpiiriloiguls", formBackingObject = VahtkonndPiiriloigul.class)
@RequestMapping("/vahtkonndpiiriloiguls")
@Controller
public class VahtkonndPiiriloigulController {
}
