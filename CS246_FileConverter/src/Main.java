
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileConverter fileConverter = new FileConverter("..\\Files\\Initial\\movies.dat","..\\Files\\Movie_Critic_Rating.txt");
		fileConverter.ConvertFile();
	}

}
