package com.example.selling.control;

import com.example.selling.common.Response;
import com.example.selling.constants.Constants;
import com.example.selling.ultis.JwtUlti;
import com.example.selling.ultis.form.FormUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUlti jwtUlti;
    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public @ResponseBody
    Response createAuthenticationToken(@RequestBody FormUser formUser) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(formUser.getUsername(), formUser.getPassword())
            );
        } catch (AuthenticationException e) {
            return Response.warning(Constants.RESPONSE_CODE.Incorrect);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(formUser.getUsername());
        final String jwt = jwtUlti.generateToken(userDetails);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(jwt);
    }


}
