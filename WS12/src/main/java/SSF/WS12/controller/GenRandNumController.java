package SSF.WS12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import SSF.WS12.models.Generate;

@Controller
@RequestMapping(path= "/form")
public class GenRandNumController {

    //Method to display form
    @GetMapping(path="/show")
    public String showRandForm(Model model){

        // Instantiate the generate object and set n to be 1
        Generate g = new Generate(1);
        // associate the bind var to the view/page
        model.addAttribute("generateObj", g);
        System.out.println(model);
        return "form";

    }

    //Method to process form
    
}