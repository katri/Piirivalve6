package ee.itcollege.i377.team6.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import ee.itcollege.i377.team6.entities.Piiriloik;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RooWebScaffold(path = "piiriloiks", formBackingObject = Piiriloik.class)
@RequestMapping("/piiriloiks")
@Controller
public class PiiriloikController {
	
    @RequestMapping(value = "/{piiriloikId}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("piiriloikId") Long piiriloikId, Model uiModel) {
        uiModel.addAttribute("piiriloik", Piiriloik.findPiiriloik(piiriloikId));
        addDateTimeFormatPatterns(uiModel);
        return "piiriloiks/update";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Piiriloik piiriloik, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("piiriloik", piiriloik);
            addDateTimeFormatPatterns(uiModel);
            return "piiriloiks/update";
        }
        
        
        uiModel.asMap().clear();
        piiriloik.merge();
        return "redirect:/piiriloiks/" + encodeUrlPathSegment(piiriloik.getPiiriloikId().toString(), httpServletRequest);
    }
}
