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
	
	private static final String[] validExtensions = {".avi", ".mkv", ".mp4"} ;
	
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
                    System.out.println(">> \""+ fileName + "\" has changed!");
                    this.performAllMovies(absolutePath(key,fileName));

                }
                else if(kind == ENTRY_CREATE) {
                	System.out.println(">> \""+ fileName + "\" has been created!");
                	this.performAllMovies(absolutePath(key,fileName));
                	
                }
                else if(kind == ENTRY_DELETE) {
                	System.out.println(">> \""+ fileName + "\" has been deleted!");
                	this.manager.performFileDeleted(absolutePath(key,fileName));
                	
                	if(Paths.get(absolutePath(key,fileName)).toFile().isFile()){
                		System.out.println(">> \""+ fileName + "\" is a File");
                		//this.manager.performFileDeleted(absolutePath(key,fileName));
                	}
                	else {
                		System.out.println(">> \""+ fileName + "\" is a Directory");
                	}
                }
            }
             
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
	}
	
	private void initAll() {
		// Get all movies in that new directory
		List<Path> movies = this.getAllMovieFiles(rootPath);
		if(movies!= null) {
			List<String> filesToRemove = new ArrayList<String>();
			for(String str : index) {
				boolean found = false; 
				for(Path p : movies) {
					if(str.equals(p.toAbsolutePath().toString())) {
						found=true;
					}
				}
				if(!found){
					filesToRemove.add(str);
				}
			}
			for(String str : filesToRemove) {
				this.removeFromIndex(str);
			}
			
			
			for(Path str : movies) {
	   			manager.performFileCreated(str.toString(), str.getFileName().toString());
	   		}
		}
	}
	
	private void performAllMovies(String dir) {
		// Get all movies in that new directory
		List<Path> movies = this.getAllMovieFiles(rootPath);
		if(movies!= null) {
			for(Path str : movies) {
	   			manager.performFileCreated(str.toString(), str.getFileName().toString());
	   		}
		}
	}
	
	private String absolutePath(WatchKey key, Path path) {
		return keys.get(key)+"/"+path.getFileName().toString() ;
	}
	
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
	
	private void register(String dir) {
		register(Paths.get(dir));
	}
	
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
	
	private List<Path> getAllMovieFiles(String path) {
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
							results.add(f.toPath());
							index.add(f.getPath());
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
	
	public void addToIndex(MovieFile m) {
		if(!index.contains(m.getNameWithAbsolutPath())) {
			logger.logInfo("Add to Index: {0}", m.getNameWithAbsolutPath());
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(indexPath, true)))) {
			    out.println(m.getNameWithAbsolutPath());
			    out.close();
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	}
	
	public void removeFromIndex(String m) {
		for(int i=0; i<index.size(); i++){
			if(index.get(i).startsWith(m)) {
				logger.logInfo("Remove from Index: {0}", m);
				index.remove(i);
				updateIndex();
			}
		}
	}
	
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
