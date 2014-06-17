package com.insanity.inmem.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class FileManager
{

	public static BufferedReader br = null;

	public static Map<Integer, Map<String, HashSet<Integer>>> res = new HashMap<Integer, Map<String, HashSet<Integer>>>();

	public static Map<String, Set<String>> getColumndistinctValues()
	{

		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		for ( Integer index : res.keySet() )
		{
			map.put( index + "", res.get( index ).keySet() );
		}
		return map;
	}

	public static void initialize( String fileName )
	{

		try
		{
			String sCurrentLine;
			StringTokenizer st2;

			br = new BufferedReader( new FileReader( fileName ) );
			int fileIndex = -1;
			while ( ( sCurrentLine = br.readLine() ) != null )
			{

				st2 = new StringTokenizer( sCurrentLine, "," );
				Integer counter = 0;
				String columnValue;
				HashSet<Integer> indexList;
				++fileIndex;
				// per row
				while ( st2.hasMoreElements() )
				{

					columnValue = st2.nextElement().toString();
					if ( res.get( counter ) == null )
					{
						HashMap<String, HashSet<Integer>> map = new HashMap<String, HashSet<Integer>>();
						indexList = new HashSet<Integer>();
						indexList.add( fileIndex );
						map.put( columnValue, indexList );
						res.put( counter, map );
					}
					else
					{
						if ( res.get( counter ).containsKey( columnValue ) )
						{
							indexList = res.get( counter ).get( columnValue );
							indexList.add( fileIndex );
							res.get( counter ).put( columnValue, indexList );
						}
						else
						{
							indexList = new HashSet<Integer>();
							indexList.add( fileIndex );
							res.get( counter ).put( columnValue, indexList );
						}
					}

					counter++;
				}

			}

		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( br != null )
					br.close();
			}
			catch ( IOException ex )
			{
				ex.printStackTrace();
			}
		}
	}

	public static void printFilteredColumn( int sourceColumnIndex, String sourceColumnValue, int targetColumnIndex )
	{
		Map<String, HashSet<Integer>> sourceColumn = res.get( sourceColumnIndex );
		Map<String, HashSet<Integer>> targetColumn = res.get( targetColumnIndex );

		HashSet<Integer> sourceColumnListForSelectedValue = sourceColumn.get( sourceColumnValue );

		if ( sourceColumnListForSelectedValue != null )
		{
			HashSet<Integer> targetIndexList = new HashSet<Integer>();
			for ( String targetColumnValue : targetColumn.keySet() )
			{
				targetIndexList = targetColumn.get( targetColumnValue );

				System.out.println( "Searching if " + targetColumnValue + " matches " + sourceColumnValue );

				if ( !Collections.disjoint( sourceColumnListForSelectedValue, targetIndexList ) )
				{
					System.out.println( targetColumnValue + " matches " + sourceColumnValue );
				}
			}
		}
		else
		{
			System.out.println( "No Results found matching " + sourceColumnValue );
		}
	}
}