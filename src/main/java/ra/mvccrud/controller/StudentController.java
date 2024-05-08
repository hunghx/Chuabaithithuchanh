package ra.mvccrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.mvccrud.dto.request.StudentRequest;
import ra.mvccrud.service.IStudentService;
@Controller
@RequestMapping({"/student",""})
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;
    @GetMapping({"/list",""})
    public String list(Model model){
        model.addAttribute("students",studentService.findALl());
        return "list";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("student",new StudentRequest());
        return "add";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model){
        model.addAttribute("student",studentService.findById(id));
        return "edit";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id){
        studentService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String doAdd(@ModelAttribute("student") StudentRequest request){
        studentService.save(request);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute("student") StudentRequest request){
        studentService.save(request);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String search(@RequestParam String keyword,Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("students",studentService.findALlByName(keyword));
        return "list";
    }
}
