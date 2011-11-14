import java.io.FileReader;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class FileConverter {

	private String m_InputFileName;
	private String m_OutputFileName;
	
	//Constructor
	// Input : File to convert, File to write to
	FileConverter( String iInputFile, String iOutputFile )
	{
		m_InputFileName = iInputFile;
		m_OutputFileName = iOutputFile;
	}
	
	//This is for getting movie and rating in a single file
	//Format:
	//movieID	CriticsRating
	public boolean ConvertFile()
	{
		boolean retVal = true;
		
		try
		{
			FileInputStream sourceFile;

			//Check if input file exists
			sourceFile = new FileInputStream(m_InputFileName);
						
			
			//Open input file
			DataInputStream inDataStream= new DataInputStream(sourceFile);
			BufferedReader bufferedInDataStream = new BufferedReader(new InputStreamReader(inDataStream));
			
			//Output file writer
			BufferedWriter bufferedOutDataStream = new BufferedWriter(new FileWriter(m_OutputFileName));
		    
			//Read the file line by line
			String strLine;
			while ((strLine = bufferedInDataStream.readLine()) != null)   
			{
				//Split the line by the delimiter
				String[] result = strLine.split("\\t");
			
				//0 is ID
				int movieID = Integer.parseInt(result[0]);
			    //7 is Critics rating
				try
				{
					Double criticsID = Double.parseDouble(result[7]);
				
					//8 is Number of reviews
				    if( Integer.parseInt( result[8]) == 0 )
				    	criticsID = -1.0;				    	
				    
				    String strToWrite = Integer.toString(movieID);
				    strToWrite = strToWrite.concat("\t");
				    strToWrite =strToWrite.concat(Double.toString(criticsID));
				    
				    System.out.println(strToWrite);
				    
				    bufferedOutDataStream.write(strToWrite);
				}
				catch( Exception e )
				{
					 String strToWrite = Integer.toString(movieID);
					 strToWrite = strToWrite.concat("\t");
					 strToWrite = strToWrite.concat(Double.toString(-1.0));
					 System.out.println(strToWrite);
				     bufferedOutDataStream.write(strToWrite);
					
				}
			    bufferedOutDataStream.newLine();
			}
			 if (bufferedOutDataStream != null) 
			 {
				 bufferedOutDataStream.flush();
				 bufferedOutDataStream.close();
             }
		}
		catch( FileNotFoundException e)
		{
			System.out.println("source file not found");
			retVal = false;
		}
		catch( Exception e)
		{
			System.out.println("Error");
			retVal = false;
		}
		
		
		
		return retVal;
	}
	
	public boolean ConvertActorsFile( String iActorsList )
	{
		boolean retVal = true;
		
		try
		{
			
			//First process the list of actors and create an array of all the actors
		/*	FileInputStream actorsFile = new FileInputStream( iActorsList );
			DataInputStream inActorsDataStream= new DataInputStream(actorsFile);
			BufferedReader bufferedInActorsDataStream = new BufferedReader(new InputStreamReader(inActorsDataStream));
			
			String[] actorsList = new String[96000];
			String actorName;
			int i = 0;
			String oldActorName = " ";
			while ((actorName = bufferedInActorsDataStream.readLine()) != null)   
			{			
				if( 0 != actorName.compareTo(oldActorName) )
				{
					actorsList[i++] = actorName;
					oldActorName = actorName;
				}
			}
			
			*/
			
			//Do the actual reading of the actors list now
			FileInputStream sourceFile;

			//Check if input file exists
			sourceFile = new FileInputStream(m_InputFileName);
						
			
			//Open input file
			DataInputStream inDataStream= new DataInputStream(sourceFile);
			BufferedReader bufferedInDataStream = new BufferedReader(new InputStreamReader(inDataStream));
			
			//Output file writer
			BufferedWriter bufferedOutDataStream = new BufferedWriter(new FileWriter(m_OutputFileName));
		    
			
			//Read the file line by line
			String strLine;
			boolean nextLine = true;
			int oldMovieID = 1;
			int actorIndex = 0;
			String strToWrite = "";
			HashMap hashMap = new HashMap();
			int maxActors = -1;
			while ((strLine = bufferedInDataStream.readLine()) != null )   
			{
				//Split the line by the delimiter
				String[] result = strLine.split("\\t");
			
				//0 is ID
				//1 is actor ID
				//2 is	actor Name
				//3 is ranking
				int movieID = Integer.parseInt(result[0]);
				String actorID = result[1];
				int actorRanking = Integer.parseInt(result[3]);
				
				//Are we starting a new movie here?
				if( oldMovieID != movieID )
				{

					//First process the data
					Iterator iterator = hashMap.keySet().iterator();
				       
				       
			        while(iterator. hasNext()  )
			        {        

			            strToWrite = strToWrite.concat(Integer.toString(oldMovieID));
			            strToWrite = strToWrite.concat(",");
			            
			            Object key = iterator.next();
			            strToWrite = strToWrite.concat((String) key);
			            strToWrite = strToWrite.concat(",");
			            
			            int actorRank = (maxActors +1 -(Integer)(hashMap.get(key)));
			            double NormalizedRank = (double)actorRank/(double)maxActors;
			            		
			            strToWrite = strToWrite.concat(Double.toString(NormalizedRank));

			            strToWrite = strToWrite.concat("\n");
			        }
					
					hashMap.clear();
					System.out.println(strToWrite);
					bufferedOutDataStream.write(strToWrite);
				    nextLine = true;
				    actorIndex = 0;
				    strToWrite = "";
				    oldMovieID = movieID;
				}
				hashMap.put(actorID, actorRanking);
				maxActors = maxActors>actorRanking?maxActors:actorRanking;
			}
			 if (bufferedOutDataStream != null) 
			 {
				 bufferedOutDataStream.flush();
				 bufferedOutDataStream.close();
             }
		}
		catch( FileNotFoundException e)
		{
			System.out.println("source file not found");
			retVal = false;
		}
		catch( Exception e)
		{
			System.out.println("Error");
			retVal = false;
		}
		
		
		
		return retVal;
	}
}
