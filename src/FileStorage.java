import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileStorage {
	
	private static String filePath;
	private static String pathDir;
	private static String pathName;
	
	public FileStorage(){
		pathDir = System.getProperty("user.home") + "\\VODO_Path";
		pathName = "\\path.txt";
		filePath = currFilePath();

	}

	public void add(String text){
		write(text, filePath, true, true);
	}
	
	public void setFilePath(String newPath){
		write(newPath, pathDir + pathName, false, false);
		
		File newFile = new File(newPath);
		createFile(newFile);
		copyFile(newPath);
		filePath = newPath;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void copyFile(String newPath){
		
		File oldFile = new File(filePath);
		File newFile = new File(newPath);
		try {
			Files.copy(oldFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Copy File Fail");			
		}
	}
	
	public ArrayList<String> display(){
		
		ArrayList<String> list = readFile();
		return list;
	}
	
	public ArrayList<String> search(String keyword){
		ArrayList<String> list = searchFile(keyword);
		
		return list;
	}
	
	public void delete(int num, ArrayList<String> dataList){
		
	}
	

	
	
	//Private class
	//--------------------------------------------------------------------------------------------
	
	private static String currFilePath(){
		
		String defaultDataPath = System.getProperty("user.home") + "\\VODO\\data.txt";
		
		File pathFile = new File(pathDir + pathName);
		File dataFile = new File(defaultDataPath);
		String filePath;
		
		
		
		if(!pathFile.exists()){				
			createFile(pathFile);
			createFile(dataFile);
			hideFolder(pathDir);
			write(defaultDataPath, pathDir + pathName, false, false);
					
		}
	
		filePath = readPath(pathDir + pathName);
		
		return filePath;
	}
	
	private static void createFile(File file){
		if(!file.exists()){	 
		     file.getParentFile().mkdirs();
		     try {
				file.createNewFile();			
		     }catch (IOException e) {
		    	 System.out.println("Create File Fail");
		     }
		  }
	}
	
	private static void hideFolder(String hiddenPath){
		String hiddenFolder[] = {"attrib","+h",hiddenPath};
	
		try {
			Runtime.getRuntime().exec(hiddenFolder);
		} catch (IOException e) {
			System.out.println("hideFile function error");
		}
	}
	
	private static void write(String text, String pathName, boolean isAppend, boolean isNewLine){
		File file = new File(pathName);
		
		if(!file.exists()){		
			createFile(file);	
		}
		
		
		try {
			FileWriter fileWriter = new FileWriter(file, isAppend);
			BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
			
			/*
			if(isFileEmpty(file)){
				bufferedWriter.write("");
			}
			*/
			
			bufferedWriter.write(text);
			
			if(isNewLine == true){
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
		}catch(IOException ex){
			System.out.println("Error writing to file '"+ filePath + "'");
		}
	}
	
	private static String readPath(String fileName){
	
		String path = null;
		
		try {
			path = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("readPath function error");
		}
       
        return path;
	}
	
	private static ArrayList<String> readFile(){
		
		String line = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
        	
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			
			while((line = bufferedReader.readLine()) != null) {
				list.add(line);
			}			
            bufferedReader.close();   
            
            
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");                   
        }
        
        return list;
	}
	//Private methods
	
	private static ArrayList<String> searchFile(String keyword){
		String line = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
        	
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			
			while((line = bufferedReader.readLine()) != null) {
				if(line.contains(keyword)){
					list.add(line);
				}
			}			
            bufferedReader.close();   
            
            
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");                
        }catch(IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");                   
        }
        
		return list;
	}
	
	private static boolean isFileEmpty(File fileName){
		File file = fileName;

		if(file.length() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}


