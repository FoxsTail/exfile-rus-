package ua.alice.controller;

import com.sun.glass.ui.Application;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.alice.config.security.SecurityUser;
import ua.alice.entity.*;
import ua.alice.repository.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/web")
public class MainController {

    public static final String SAVE_LOCATION = "D:\\work\\workin\\4й курс\\диплом\\exfile\\src\\main\\webapp\\resources\\document\\";

    @Autowired
    private ExFileJpaRepository exFileJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;
    @Autowired
    private SubdivisionJpaRepository subdivisionJpaRepository;
    @Autowired
    private CategoryJpaRepository categoryJpaRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/files")
    public ModelAndView files() {
        List<ExFile> files = exFileJpaRepository.findAll();
        return new ModelAndView("files", "files", files);
    }

    @RequestMapping("/files/sort")
    public ModelAndView sortCategory(@RequestParam("category") String category) {

        List<ExFile> allFiles = exFileJpaRepository.findAll();
        List<ExFile> sortedFiles = new ArrayList<>();

        for (ExFile file : allFiles) {
            for (Category cat : file.getGetter_category()) {
                if (cat.getName().equals(category)) {
                    sortedFiles.add(file);
                }
            }
        }
        return new ModelAndView("files", "files", sortedFiles);
    }

    @RequestMapping("/files/{id}")
    public ModelAndView filesPerID(@PathVariable("id") Long id) {
        ExFile file = exFileJpaRepository.findOne(id);
        return new ModelAndView("file", "file", file);
    }


    @RequestMapping("/users")
    public ModelAndView users() {
        Sort sort = new Sort("lastName", "firstName", "patronymic");
        List<User> users = userJpaRepository.findAll();
        return new ModelAndView("users", "users", users);
    }

    @RequestMapping("/users/sort")
    public ModelAndView sortSurname(@RequestParam("surname") String surname) {

        List<User> allUsers = userJpaRepository.findAll();
        List<User> sortedUser = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getSurname().equals(surname)) {
                sortedUser.add(user);
            }
        }
        return new ModelAndView("users", "users", sortedUser);
    }

    @RequestMapping("/users/{id}")
    public ModelAndView userGetProfile(@PathVariable("id") Long id) {
        User user = userJpaRepository.findOne(id);
        return new ModelAndView("user", "user", user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public ModelAndView userUpdate(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("users");
        }
        ModelAndView modelAndView = new ModelAndView("user");

        User actual = userJpaRepository.findOne(id);
        user.setRole(actual.getRole());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        userDetails.setEmail(user.getEmail());

        userJpaRepository.save(user);

        modelAndView.addObject("message", "Выш профиль был обновлен удачно!=)");
        modelAndView.addObject("user", user);
        return modelAndView;

    }

    @RequestMapping(value = "/users/{id}/delete")
    public ModelAndView userUpdate(@PathVariable("id") Long id) {
        User user = userJpaRepository.findOne(id);
        userJpaRepository.delete(user);
        return new ModelAndView("redirect:/web/deleted");

    }

    @RequestMapping(value = "/deleted")
    public ModelAndView deleted() {
        return new ModelAndView("deleted", "message", "Профиль был успешно удален!");
    }

    @RequestMapping("/profile")
    public ModelAndView profile() {
        User user = getCurrentUser();
        return new ModelAndView("user", "user", user);
    }

    @RequestMapping(value = "/downloadPerDate", method = RequestMethod.POST)
    public ModelAndView loadPerDate(@RequestParam("date") String dateString) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd\\MM\\yyyy");
        String currentDate = format1.format(new Date());

        if (dateString.isEmpty()) {
            dateString = currentDate;
        }
        List<ExFile> filesAll = exFileJpaRepository.findAll();

        List<ExFile> filesDate = new ArrayList<>();


        for (ExFile exf : filesAll) {
            if (exf.getDate().equals(dateString)) {
                filesDate.add(exf);
            }
        }

        List<ExFile> filesSort = sortFilesPerUser(filesDate);
        return new ModelAndView("download", "files", filesSort);
    }

    @RequestMapping("/download")
    public ModelAndView uploadFile() {
        List<ExFile> filesIn = exFileJpaRepository.findAll();
        List<ExFile> files = sortFilesPerUser(filesIn);

        return new ModelAndView("download", "files", files);
    }

    @RequestMapping("/load/{id}")
    public void loadFromPage(@PathVariable("id") Long id, HttpServletResponse response) {
        ExFile exFile = exFileJpaRepository.findOne(id);

        ApplicationContext context = new FileSystemXmlApplicationContext();
        Resource resource = context.getResource("file:" + exFile.getPath());

        File file = new File(exFile.getPath());
        try {
            response.setHeader("Content-Disposition", "attachment; filename="
                    + file.getName());
            InputStream is = resource.getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            //response.flushBuffer(); - подумать на этой бородой на досуге и постичь дзен

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/sendF", method = RequestMethod.POST)
    public ModelAndView sendF(@Valid @ModelAttribute("uploadForm") ExFile exFile, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("sendF");
        }

        MultipartFile file = exFile.getMultipartFilefile();

        if (file.getOriginalFilename().contains("/")) {
            return new ModelAndView("someErrors");
        }

        if (!file.isEmpty()) {
//---------------//
            exFile.setName(file.getOriginalFilename());
            String name = file.getOriginalFilename();
            exFile.setSize(file.getSize());
            String location = SAVE_LOCATION;
            File pathFile = new File(location);
            if (!pathFile.exists()) {
                pathFile.mkdir();
            }
            pathFile = new File(location + file.getOriginalFilename());
            try {
                file.transferTo(pathFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            exFile.setPath(location + file.getOriginalFilename());

            SimpleDateFormat format1 = new SimpleDateFormat("dd\\MM\\yyyy");
            exFile.setDate(format1.format(new Date()));

//-----------//
            Category category;
            for (String s : exFile.getValue_categories()) {
                category = categoryJpaRepository.findOne(Integer.parseInt(s));
                exFile.addGetterCategory(category);
            }

            Department department;
            for (String s : exFile.getValue_departments()) {
                department = departmentJpaRepository.findOne(Integer.parseInt(s));
                exFile.addGetterDepartment(department);
            }

            Subdivision subdivision;
            for (String s : exFile.getValue_subdivisions()) {
                subdivision = subdivisionJpaRepository.findOne(Integer.parseInt(s));
                exFile.addGetterSubdivision(subdivision);
            }

//------финт ушами----//
            User user = getCurrentUser();
            exFile.setUser(user);
//----//
            Department dep1 = departmentJpaRepository.findOne(exFile.getUser().getDepartment().getIdd());
            Subdivision sub1 = subdivisionJpaRepository.findOne(exFile.getUser().getSubdivision().getIds());

            exFile.setSender_subdivision(sub1);
            exFile.setSender_department(dep1);

            exFileJpaRepository.save(exFile);
            return new ModelAndView("okpage", "files", exFile);

        } else {
            return new ModelAndView("someErrors", "message", "Упс, вы забыли добавить файл, хотите вернуться и попробовать еще раз?");
        }


    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ModelAndView provideUploadInfo() {

        User user = getCurrentUser();
        user.addExFile(new ExFile());

        ModelAndView modelAndView = new ModelAndView("sendF");
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

        List<Category> categories = categoryJpaRepository.findAll();
        Map<Integer, String> catMap = new HashMap<>();
        for (Category c : categories) {
            catMap.put(c.getIdc(), c.getName());
        }

        modelAndView.addObject("sub", subMap);
        modelAndView.addObject("dep", depMap);
        modelAndView.addObject("cat", catMap);
        modelAndView.addObject("uploadForm", user.getLastAddedFile());

        return modelAndView;

    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("byepage");
    }


    public List<ExFile> sortFilesPerUser(List<ExFile> filesIn) {
        List<ExFile> files = new ArrayList<>();
        User user = getCurrentUser();
        Subdivision subdivision = user.getSubdivision();
        Department department = user.getDepartment();

        for (ExFile ef : filesIn) {
            for (Subdivision s : ef.getGetter_subdivisions()) {
                if (s == subdivision) {
                    for (Department d : ef.getGetter_departments()) {
                        if (d == department) {
                            files.add(ef);
                        }
                    }
                }
            }

        }
        return files;
    }

    public User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userJpaRepository.findUserByLogin(user.getLogin());

        return user;
    }
}
