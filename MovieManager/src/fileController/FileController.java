package fileController;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.MovieFile;


public class FileController {
	
	private final FileReader reader ;
	private final FileWriter writer;
	public static final String moviePath="src/";
	public static final String unknownMoviePath="src/unknownMovies";
	public static final String movieInfoPath="src/movieInfo/";
	public static final String movieGenrePath="src/movieGenre/";
	
	
	public FileController(){
		reader = new FileReader();
		writer = new FileWriter();
		init();
	}
	
	protected void init (){
		writer.createFile(moviePath);
		writer.createFile(unknownMoviePath);
		writer.createFile(movieInfoPath);
	}
			
	public MovieFile performRetrieveInfoFile(String path){
		return this.reader.retrieveInfosFile(path);
	}
	
	public void performFileWrite (MovieFile f){
		try {
			writer.fileWriter(f.toJSON(),movieInfoPath+f.getTxtPath()+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		new FileController();
		
		
	}
	
	public void performUpdateMovie(MovieFile f){
		
		Runtime R = Runtime.getRuntime();
		//Renommage des films avec le nom title+SEPARATOR+year+extension(MovieFile)
		try {
			R.exec("mv "+f.getNameWithAbsolutPath()+" "+f.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Cr�ation des dossiers GENRE et cr�e les liens qui vont avec
		for (String str: f.getSymbolicLinks()){
			writer.createFile(str);	
			try {
				R.exec("ln -s "+moviePath+f.getTitle()+" "+movieGenrePath+f.getTitle());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	//D�place un film non vers le unknownMoviePath
	public void performUnknownMovie(MovieFile f){
		
		try {
			writer.renameFile(unknownMoviePath+"/"+f.getTitle()+f.getExtension(), f.getNameWithAbsolutPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
