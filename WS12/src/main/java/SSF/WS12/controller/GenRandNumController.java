package SSF.WS12.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SSF.WS12.models.Generate;

@Controller
@RequestMapping(path="/generate")
public class GenRandNumController {

    //Method to display form
    @GetMapping(path="/form")
    public String showForm(Model model){

        // Instantiate the generate object and set n to be 1
        Generate g = new Generate(1);
        model.addAttribute("randNumGenerator", g);
        return "form";
        
    }

    //Method to process form by calling method randomNum
    @PostMapping(path="/result")
    public String showRandNum(@RequestParam Integer n, Model model){
        randomNum(model, n);
        return "result";
    }

    /*Alt1 use HttpServletRequest

    @PostMapping(path="/result")
    public String showRandNum(HttpServletRequest request, Model model){
        Integer n = Integer.parseInt(request.getParameter("n"));
        randomNum(model, n);
        return "result";
    }
    */

    /* Alt2 use @ModelAttribute

    @PostMapping(path="/result")
    public String showRandNum(@ModelAttribute Generate g, Model model){
        randomNum(model, g.getN());
        return "result";
    }
    */

    @GetMapping(path="/result/{n}")
    public String generateRandNum(@PathVariable Integer n, Model model){
        randomNum(model, n);
        return "result";
    }

    //Method to create random number images
    private void randomNum(Model model, int n){

        //Store all the number images to the imgNumbers array 
        String[] imgNumbers = new String[31];
        for(int i =0 ; i<31; i++){
            imgNumbers[i] = "number" + i + ".jpg";
        }

        //Generate unique random number images and store in uniqueResult set
        List<String> selectedImg = new ArrayList<String>();

        Random rand = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>(); //set of random numbers
        while(uniqueResult.size() < n){
            Integer randNum = rand.nextInt(30);
            uniqueResult.add(randNum);
        }

        Iterator<Integer> it = uniqueResult.iterator(); //loop through uniqueResult
        while(it.hasNext()){
            selectedImg.add(imgNumbers[it.next()]);
        }

        model.addAttribute("noOfRandomNum", n);
        model.addAttribute("randNum", uniqueResult);
        model.addAttribute("randNumResult", selectedImg);

    }

}