package com.example.server;



import com.example.service.MatrixServiceImpl;

import jakarta.xml.ws.Endpoint;


public class MatrixServer {
    public static void main(String[] args) {
;
        String url = "http://localhost:8080/services/MatrixService";
		 Endpoint.publish( url , new MatrixServiceImpl () ) ;
		 System.out.println(" Vous pouvez consulter le WSDL de ce service sur :"+ url + "?wsdl") ;   
    }
}