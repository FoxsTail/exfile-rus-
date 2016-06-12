package ua.alice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.alice.entity.*;
import ua.alice.repository.DepartmentJpaRepository;
import ua.alice.repository.SubdivisionJpaRepository;
import ua.alice.repository.UserJpaRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Лис on 26.05.2016.
 */
@Controller
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;
    @Autowired
    private SubdivisionJpaRepository subdivisionJpaRepository;

    @RequestMapping(value = "/login/register", method = RequestMethod.POST)
    public ModelAndView registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/registration");
        }

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String psw = encoder.encodePassword(user.getPassword(), null);
        user.setPassword(psw);


        Department department = departmentJpaRepository.findOne(Integer.parseInt(user.getDepartment_trans()));
        Subdivision subdivision = subdivisionJpaRepository.findOne(Integer.parseInt(user.getSubdivision_trans()));

        department.addUser(user);
        subdivision.addUser(user);

        user.setDepartment(department);
        user.setSubdivision(subdivision);

        user.setRole(Role.USER);
        userJpaRepository.save(user);

        return new ModelAndView("userSuccess", "user", user);
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/login/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("registration");

        List<Subdivision> subdivisions = subdivisionJpaRepository.findAll();
        Map<Integer, String> subMap = new HashMap<>();
        for (Subdivision s : subdivisions) {
            subMap.put(s.getIds(), s.getName());
        }


        List<Department> departments = departmentJpaRepository.findAll();
        Map<Integer, String> depMap = new HashMap<>();
        for (Department d : departments) {
            depMap.put(d.getIdd(), d.getName());
        }

      /* вместо стринга ключем лучше кинуть интедж
        Map<Integer, String> trans = new HashMap<>();
        Map<String, Map<Integer, String>> subDep = new HashMap<>();
        for (Subdivision sub : subdivisions) {
            List<Department> dep = sub.getDepartments();
            for (Department d : dep) {
                trans.put(d.getIdd(), d.getName());
            }
            subDep.put(sub.getName(), trans);
            trans = new HashMap<>();
        }
*/
        //modelAndView.addObject("test", subPerDep);
        modelAndView.addObject("sub", subMap);
        modelAndView.addObject("dep", depMap);

        modelAndView.addObject("user", new User());
        return modelAndView;
    }

}
