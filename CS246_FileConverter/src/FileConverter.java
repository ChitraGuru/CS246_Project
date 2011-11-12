import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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
		}
		catch( Exception e)
		{
			System.out.println("Error");
		}
		
		
		
		return retVal;
	}
}
