
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	FileConverter fileConverter = new FileConverter("..\\Files\\Initial\\movies.dat","..\\Files\\Movie_Critic_Rating.txt");
		//fileConverter.ConvertFile();
		
		FileConverter fileConverterForActor = new FileConverter("..\\Files\\Initial\\movie_actors.dat","..\\Files\\Movie_Actor_Ranking.txt");
		fileConverterForActor.ConvertActorsFile("..\\Files\\Initial\\actors_list.txt");
	}

}
