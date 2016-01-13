package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Employee;
import kz.hts.ce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeReplicationController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/replication/employees", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void saveNewEmployeesDataFromClient(@RequestBody List<Employee> employees) {
        for (Employee employee : employees) employeeService.save(employee);
    }

    @RequestMapping(value = "/replication/employees/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> sendNewEmployeesDataToClient(@PathVariable long time) {
        List<Employee> employees;
        if (time == 0) employees = employeeService.findAll();
        else employees = employeeService.getHistory(time);
        return employees;
    }
}
