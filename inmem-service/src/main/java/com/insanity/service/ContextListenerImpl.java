package com.insanity.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.insanity.inmem.utils.FileManager;

public class ContextListenerImpl implements ServletContextListener
{

	Map<String, Set<String>> columnValueList = new HashMap<String, Set<String>>();

	@Override
	public void contextDestroyed( ServletContextEvent arg0 )
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized( ServletContextEvent sce )
	{
		ServletContext sc = sce.getServletContext();
		URI uri;
		try
		{
			uri = ContextListenerImpl.class.getResource( "/data.csv" ).toURI();
			FileManager.initialize( uri.getPath() );
			columnValueList = FileManager.getColumndistinctValues();
			sc.setAttribute( "mySpecialMap", columnValueList );
			System.out.println( "Loaded Up My Data on Server Start by " + uri );
		}
		catch ( URISyntaxException e )
		{
			e.printStackTrace();
		}
	}
}