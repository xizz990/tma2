package com.tasksmanagerapp.tma.controller;

import java.util.Collections;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.tasksmanagerapp.tma.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.tasksmanagerapp.tma.service.TaskService;

@Controller
@RequestMapping(value="/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("task_list");
        List<Task> taskList = taskService.getAllTasks();
        ObjectComparator comparator = new ObjectComparator();
        Collections.sort(taskList, comparator);
        model.addObject("taskList", taskList);

        return model;
    }

    @RequestMapping(value="/addTask/", method=RequestMethod.GET)
    public ModelAndView addTask() {
        ModelAndView model = new ModelAndView();

        Task task = new Task();
        task.setDone(false);
        model.addObject("taskForm", task);
        model.setViewName("task_form");

        return model;
    }

    @RequestMapping(value="/updateTask/{id}", method=RequestMethod.GET)
    public ModelAndView editTask(@PathVariable long id) {
        ModelAndView model = new ModelAndView();

        Task task = taskService.getTaskById(id);
        model.addObject("taskForm", task);
        model.setViewName("task_form");

        return model;
    }

    @RequestMapping(value="/saveTask", method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("taskForm") Task task) {
        Date date = new Date();
        task.setDateCreated(new Timestamp(date.getTime()));
        taskService.saveOrUpdate(task);

        return new ModelAndView("redirect:/task/list");
    }

    @RequestMapping(value="/deleteTask/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        taskService.deleteTask(id);

        return new ModelAndView("redirect:/task/list");
    }

    @RequestMapping(value="/changeDone/{id}", method=RequestMethod.GET)
    public ModelAndView changeDone(@PathVariable long id) {
        ModelAndView model = new ModelAndView();

        Task task = taskService.getTaskById(id);
        task.setDone(!task.isDone());
        taskService.saveOrUpdate(task);

        return new ModelAndView("redirect:/task/list");
    }


}
