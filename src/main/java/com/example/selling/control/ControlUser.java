package com.example.selling.control;


import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.constants.StatusRegisterUserEnum;
import com.example.selling.data.service.UserService;
import com.example.selling.ultis.form.FormUser;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class ControlUser {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public @ResponseBody
    Response register(@RequestBody FormUser formUser) {
        String result = String.valueOf(userService.registerUser(formUser));
        if (result.equalsIgnoreCase(StatusRegisterUserEnum.Existed_Username.toString())) {
            return Response.warning(Constants.RESPONSE_CODE.WARNING, Constants.RESPONSE_CODE.Existed_Username);
        }
        if (result.equalsIgnoreCase(StatusRegisterUserEnum.Existed_Email.toString())) {
            return Response.warning(Constants.RESPONSE_CODE.Existed_Email);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS);
    }

    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }
}
