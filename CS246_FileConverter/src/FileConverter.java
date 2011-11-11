import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

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
		
		FileReader sourceFile;
		FileReader outputFile;
		//Check if input file exists
		try
		{
			sourceFile = new FileReader(m_InputFileName);
		}
		catch( FileNotFoundException e)
		{
			System.out.println("source file not found");
		}
		
		//Check if output file exists
		try
		{
			outputFile = new FileReader(m_OutputFileName);
		}
		catch( FileNotFoundException e)
		{
			System.out.println("output file not found");
		}
		
		//Open input file
		
		//Create object struct
		//While !eof of input read  the next data element and populate the struct
		
		//Once struct is populated, write to output file
		
		
		return retVal;
	}
}
