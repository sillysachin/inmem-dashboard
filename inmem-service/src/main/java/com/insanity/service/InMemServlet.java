package com.insanity.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InMemServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		// Set response content type
		response.setContentType( "text/html" );

		ObjectMapper mapper = new ObjectMapper();

		Enumeration<String> parameterNames = request.getParameterNames();

		ServletContext sc = request.getServletContext();

		PrintWriter out = response.getWriter();

		HashMap<String, Set<String>> values = ( HashMap<String, Set<String>> ) sc.getAttribute( "mySpecialMap" );
		String output = mapper.writeValueAsString( values );
		out.print( output );
	}
}
