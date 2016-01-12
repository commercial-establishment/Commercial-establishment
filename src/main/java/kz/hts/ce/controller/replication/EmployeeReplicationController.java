package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Employee;
import kz.hts.ce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeReplicationController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/replication/employees", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void saveNewProvidersDataFromClient(@RequestBody List<Employee> employees) {
        for (Employee employee : employees) employeeService.save(employee);
    }
}
