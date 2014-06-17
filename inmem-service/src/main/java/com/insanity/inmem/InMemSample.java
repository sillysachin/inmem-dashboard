package com.insanity.inmem;

import java.net.URI;
import java.net.URISyntaxException;

import com.insanity.inmem.utils.FileManager;

public class InMemSample
{

	public static void main( String[] args ) throws URISyntaxException
	{
		long startTime = System.currentTimeMillis();
		URI uri = InMemSample.class.getResource( "/data.csv" ).toURI();
		FileManager.initialize( uri.getPath() );
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println( duration / 1000 + " in sec" );
		long startTimes = System.currentTimeMillis();
		FileManager.printFilteredColumn( 3, "Manager", 0 );
		long endTimes = System.currentTimeMillis();
		long durations = endTimes - startTimes;
		System.out.println( durations / 1000 + " in sec" );
	}
}
