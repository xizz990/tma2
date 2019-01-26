package com.tasksmanagerapp.tma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/memory")
public class TrainMemoryController {

        @RequestMapping(value="/train", method=RequestMethod.GET)
        public String showTrainMemory(Model model) {

            return "train_memory_h";
        }

}
