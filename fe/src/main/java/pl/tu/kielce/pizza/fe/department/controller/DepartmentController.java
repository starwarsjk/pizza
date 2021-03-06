package pl.tu.kielce.pizza.fe.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

import javax.validation.Valid;
import java.util.List;

import static pl.tu.kielce.pizza.fe.util.FeFunctionsUtil.doSave;


@Controller
@RequestMapping("/manager/department")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private final UserService userService;

    @GetMapping("{departmentId}")
    public String getDepartment(
            Model model,
            @PathVariable("departmentId") Long departmentId) {

        DepartmentDto departmentDto = departmentService.findOne(departmentId);

        List<UserDto> usersInGivenDepartment = departmentService.findWorkersByDepartmentId(departmentId);

        model.addAttribute("departmentDto", departmentDto);
        model.addAttribute("usersInGivenDepartment", usersInGivenDepartment);

        return "department/show_department";
    }

    @GetMapping("/")
    public String departmentDefinition(Model model) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setActive(true);
        model.addAttribute("departmentDto", departmentDto);
        return "department/department_definition";
    }

    @PostMapping("/")
    public String addNewDepartment(@Valid DepartmentDto departmentDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "department/department_definition";
        }

        departmentDto.setMultiplier(departmentDto.getMultiplier() / 100);
        Long departmentId = doSave(departmentDto, departmentService::create);
        return "redirect:/manager/department/" + departmentId;
    }



    @GetMapping("/all")
    public String allDepartments(Model model) {
        List<DepartmentDto> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/department_search";
    }

    @ModelAttribute("freeManagers")
    public List<FreeManagerDto> freeManagers() {
        return userService.freeManagers();
    }

    @GetMapping("addEmployeesToDepartment")
    public String addEmployeesToDepartment(Model model) {

        AddUsersToDepartmentDto addUsersToDepartmentDto = new AddUsersToDepartmentDto();
        addUsersToDepartmentDto.setFreeUsers(userService.findAllFreeUsers());
        model.addAttribute("addUsersToDepartmentDto", addUsersToDepartmentDto);
        return "department/add_employee_to_department";
    }

    @PostMapping("addEmployeesToDepartment")
    public String addEmployeesToDepartmentPost(AddUsersToDepartmentDto addUsersToDepartmentDto) {
        departmentService.addEmployeesToDepartmentPost(addUsersToDepartmentDto.getFreeUsers());
        Long departmentId = UserUtils.departmentId();
        return "redirect:/manager/department/" + departmentId;
    }

}
