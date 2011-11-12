import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedReader;
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
			FileReader outputFile;
			//Check if input file exists
			sourceFile = new FileInputStream(m_InputFileName);
						
			
			//Check if output file exists
			outputFile = new FileReader(m_OutputFileName);
			
			
			//Open input file
			DataInputStream inDataStream= new DataInputStream(sourceFile);
			BufferedReader bufferedInDataStream = new BufferedReader(new InputStreamReader(inDataStream));
			//Read the file line by line
			String strLine;
			while ((strLine = bufferedInDataStream.readLine()) != null)   
			{
			  // Print the content on the console
				System.out.println(strLine);
			}
		}
		catch( FileNotFoundException e)
		{
			System.out.println("source file not found");
		}
		catch( Exception e)
		{
			System.out.println("Error creating dataInputStream");
		}
		
		
		
		return retVal;
	}
}
