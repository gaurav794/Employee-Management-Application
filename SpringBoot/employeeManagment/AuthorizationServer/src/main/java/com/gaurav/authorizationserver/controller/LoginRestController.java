//package com.gaurav.authorizationserver.controller;
//
//import com.gaurav.authorizationserver.controller.util.RestResponseStatus;
//import com.gaurav.authorizationserver.model.UserRole;
//import com.gaurav.authorizationserver.service.CustomAuthenticationProvider;
//import com.gaurav.authorizationserver.service.CustomUserDetailsService;
//import com.gaurav.authorizationserver.service.util.LoginUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
////TODO: Later Find the way to implement login method
//@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
//public class LoginRestController {
//    private final CustomAuthenticationProvider authenticationProvider;
//    private final CustomUserDetailsService userDetailsService;
//
//    public LoginRestController(CustomAuthenticationProvider authenticationProvider, CustomUserDetailsService userDetailsService) {
//        this.authenticationProvider = authenticationProvider;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @ResponseBody
//    public ResponseEntity login(@RequestBody UserRole user)
//    {
//        LoginUserDetails userDetails = (LoginUserDetails) userDetailsService.loadUserByUsername(user.getEmail_id());
//        //invalid user
//        if (userDetails == null) {
//            return new ResponseEntity(failureMessage(), HttpStatus.FORBIDDEN);
//        }
//        //check authentication
//        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),userDetails.getAuthorities()));
//        //Check authentication
//        boolean checkAuthentication = authentication.isAuthenticated();
//
//        //valid user
//        return checkAuthentication ? new ResponseEntity(successMessage(), HttpStatus.OK) : new ResponseEntity(failureMessage(), HttpStatus.UNAUTHORIZED);
//    }
//
//    private RestResponseStatus successMessage() {
//        return new RestResponseStatus("SUCCESS", "Valid User");
//    }
//
//    private RestResponseStatus failureMessage() {
//        return new RestResponseStatus("FAILURE", "Invalid Credentials. Please try again.");
//    }
//
//    //in case of maintenance
//    private RestResponseStatus errorMessage() {
//        return new RestResponseStatus("INTERNAL_SERVER_ERROR",
//                "Internal server error, please try again after sometime. If this problem continues, contact IT Department.");
//    }
//
//}
