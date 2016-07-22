package directory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import manager.MovieManager;
import model.MovieFile;
import utils.Log;
import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {
	
	private WatchService watcher ;
    
    private Map<WatchKey, Path> keys ;
    
    private List<String> index ;
    
    private final MovieManager manager ;
    
    private final String rootPath ;
    
    private final String indexPath = "src/index.txt" ;
    
	private static final boolean verbose = true;
	
	private static final Log logger = new Log("Watcher", verbose);
	
	private static final String[] validExtensions = {".avi", ".mkv", ".mp4", ".mov", ".mpg", ".mpa", ".wma", ".wmv"} ;
	
	
	/**
	 * Constructor
	 * @param manager: Movie Manager
	 * @param path: Path to watch
	 */
	public Watcher(MovieManager manager, String path) {
		this.manager = manager;
		this.rootPath = path;
		this.keys = new HashMap<WatchKey, Path>();
		this.index = new ArrayList<String>();
		loadIndex();
		logger.logInfo("Index: {0}", index);
		
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
			register(Paths.get(path));
		} catch (IOException e) {
			logger.logSevere("Failed to create Watcher.",e);
		}
	}
	
	/**
	 * Method to call to start the watching
	 */
	public void start() {
		this.initAll();
		while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                return;
            }
             
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                
                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                
                if (kind == ENTRY_MODIFY) {
                    this.performAllNewMovies(absolutePath(key,fileName));

                }
                else if(kind == ENTRY_CREATE) {
                	this.performAllNewMovies(absolutePath(key,fileName));
                	
                }
                else if(kind == ENTRY_DELETE) {
                	this.manager.performFileDeleted(absolutePath(key,fileName));
                }
            }
             
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
	}
	
	/**
	 * Method to initialize the list of known movies
	 */
	private void initAll() {
		System.out.println("[InitAll] Start");
		// Get all movies in that new directory
		List<Path> movies = this.getAllMovieFiles(rootPath);
	
		System.out.println("Movies: " +movies);
		System.out.println("Index: " +this.index);
		List<String> filesToRemove = new ArrayList<String>();
		List<Path> moviesToRemove = new ArrayList<Path>();
		for(String str : this.index) {
			System.out.println("Entry in index: " +str);
			boolean found = false; 
			for(Path p : movies) {
				System.out.println("Path of movies: " +p.toAbsolutePath().toString());
				if(str.equals(p.toAbsolutePath().toString())) {
					System.out.println("FOUND!");
					moviesToRemove.add(p);
					found=true;
				}
			}
			if(!found){
				filesToRemove.add(str);
			}
		}
		for(String str : filesToRemove) {
			this.removeFromIndex(str);
			manager.performFileDeleted(str);
		}
		for(Path str : moviesToRemove) {
			movies.remove(str);
		}
		
		for(Path str : movies) {
   			manager.performFileCreated(str.toString(), str.getFileName().toString());
   		}
		this.updateIndex();
	}
	
	/**
	 * Method to find all new movies and to perform the query
	 * @param dir
	 */
	private void performAllNewMovies(String dir) {
		// Get all movies in that new directory
		List<Path> movies = this.getAllNewMovieFiles(rootPath);
		if(movies!= null) {
			for(Path str : movies) {
	   			manager.performFileCreated(str.toString(), str.getFileName().toString());
	   		}
		}
	}
	
	/**
	 * Method to get absolute path 
	 * @param key
	 * @param path
	 * @return
	 */
	private String absolutePath(WatchKey key, Path path) {
		return keys.get(key)+"/"+path.getFileName().toString() ;
	}
	
	
	/**
	 * Method to register a new directory
	 * @param dir
	 */
	private void register(Path dir) {
		WatchKey key;
		try {
			key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			this.keys.put(key, dir);
			logger.logInfo("Watch Service registered for dir: {0}", dir.getFileName());
		} catch (IOException e) {
			logger.logInfo("Failed to register dir: {0} - {1}", new Object[] {dir.getFileName(), e.toString()});
		}
	}
	
	/**
	 * Method to register a new directory
	 * @param dir
	 */
	private void register(String dir) {
		register(Paths.get(dir));
	}
	
	
	/**
	 * Method to verify if a path is a valid file
	 * @param path
	 * @return
	 */
	private boolean isValidFile(String path) {
		if(path != null) {
			for(String str : validExtensions) {
				if(path.endsWith(str)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method to get all movie files in path
	 * @param path
	 * @return
	 */
	private List<Path> getAllMovieFiles(String path) {
		File dir = new File(path);
		if(dir.isDirectory()) {
			Collection<File> files = FileUtils.listFilesAndDirs(dir, TrueFileFilter.INSTANCE, DirectoryFileFilter.DIRECTORY) ;
			List<Path> results = new ArrayList<Path>() ; 
			for(File f : files) {
				if (f.isFile()) {
					if(isValidFile(f.getName())) {
						// Add to the list
						if(!results.contains(f.toPath())) {
								results.add(f.toPath());
						}
					}
				}
				else if(f.isDirectory() && !f.equals(dir)) {
					register(f.getAbsolutePath());
				}
			}
			return results;
		}
		return null;
	}
	
	/**
	 * Method to get all new movie files in path
	 * @param path
	 * @return
	 */
	private List<Path> getAllNewMovieFiles(String path) {
		File dir = new File(path);
		if(dir.isDirectory()) {
			Collection<File> files = FileUtils.listFilesAndDirs(dir, TrueFileFilter.INSTANCE, DirectoryFileFilter.DIRECTORY) ;
			List<Path> results = new ArrayList<Path>() ; 
			for(File f : files) {
				if (f.isFile()) {
					if(isValidFile(f.getName())) {
						// Check if the movie is already known
						if(!this.index.contains(new String(f.getAbsolutePath().toString()))) {
							// Add to the list
							if(!results.contains(f.toPath())) {
								results.add(f.toPath());
						}
						if(!index.contains(f.getPath())) {
							index.add(f.getPath());
						}
						}
					}
				}
				else if(f.isDirectory() && !f.equals(dir)) {
					register(f.getAbsolutePath());
				}
			}
			return results;
		}
		return null;
	}
	
	
	/**
	 * Method to load the index into the runtime
	 */
	private void loadIndex() {
		File index = new File(indexPath);
		if(index.exists()) {
			FileInputStream fstream;
			try {
				fstream = new FileInputStream(indexPath);
				try(BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
				    for(String line; (line = br.readLine()) != null; ) {
				    	this.index.add(line);
				    }
				} catch (IOException e) {
					logger.logSevere("Failed to read in Index: {0}", e.toString());
				}
			} catch (FileNotFoundException e) {
				logger.logSevere("File not found. Should never happen.");
			}
		}
		else {
			try {
				index.createNewFile();
			} catch (IOException e) {
				logger.logSevere("Failed to create Index: {0}", e.toString());
			}
		}
	}
	
	/**
	 * Method to add a movie file into the index
	 * @param m
	 */
	public void addToIndex(MovieFile m) {
		if(!index.contains(m.getFileNameWithAbsolutePath())) {
			index.add(m.getFileNameWithAbsolutePath());
			logger.logInfo("Add to Index: {0}", m.getFileNameWithAbsolutePath());
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(indexPath, true)))) {
			    out.println(m.getFileNameWithAbsolutePath());
			    out.close();
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
			updateIndex();
		}
	}
	
	/**
	 * Method to remove a movie file from the index
	 * @param m
	 */
	public void removeFromIndex(MovieFile m) {
		this.removeFromIndex(m.getFileNameWithAbsolutePath());
		
	}
	
	/**
	 * Method to remove a movie file from the index
	 * @param str
	 */
	public void removeFromIndex(String str) {
		for(int i=0; i<index.size(); i++){
			if(index.get(i).startsWith(str)) {
				logger.logInfo("Remove from Index: {0}", str);
				index.remove(i);
				updateIndex();
			}
		}
	}
	
	/**
	 * Method to update the index
	 */
	private void updateIndex() {
		File index = new File(indexPath);
		if(index.exists()) {
			index.delete();
			try {
				index.createNewFile();
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(indexPath, true)))) {
					for(String str: this.index){
						out.println(str);
					}
					out.close();
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
			} catch (IOException e) {
				
			}
		}
	}
	
}
