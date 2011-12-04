package ee.itcollege.i377.team6.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.itcollege.i377.team6.entities.Piiriloik;
import ee.itcollege.i377.team6.entities.Piiripunkt;


@RequestMapping("/piirivastutusearuanne/**")
@Controller
public class PiirivastutuseAruanne {

    @RequestMapping
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	modelMap.addAttribute("piiriloiks",Piiriloik.findAllPiiriloiks());
    	modelMap.addAttribute("piiripunkts",Piiripunkt.findAllPiiripunkts());
    
      
    
    	
    	
        return "piirivastutusearuanne/index";
    }
}
