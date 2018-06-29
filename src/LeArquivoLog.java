import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LeArquivoLog {

	public static void main(String[] args) {

		try {
//			long count = Files.lines(Paths.get("testfile.txt")).filter(s -> s.contains("particularString")).count();
			Stream<String> texto = Files.lines(Paths.get("C:\\Desenvolvimento\\SIPLD\\LeArquivoLog\\sipld-prd.log.2018-05-23.txt"), StandardCharsets.ISO_8859_1)
											.filter(s -> s.contains("03002303000131"));

			texto.forEach(System.out::println);

			
			texto.close();
//			for (String string : texto.collect(Collectors.toSet())) {
//				System.out.println(string);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void buscarTexto(){
		Scanner txtscan = new Scanner("");
		try {
			String stringBusca = "03002303000131";
			int line = 0;
			String txt = null;
			Pattern pattern = Pattern.compile("(?m)^.*\\Q" + stringBusca + "\\E.*$");
//			Pattern pattern = Pattern.compile("03002303000131");   
			              
//			txtscan = new Scanner(new File("C:\\Desenvolvimento\\SIPLD\\leArquivoLog\\sipld-prd.log.2018-05-23"));
			txtscan = new Scanner(new File("C:\\Desenvolvimento\\SIPLD\\leArquivoLog\\sipld-prd.log.2018-05-23"));

			while ((txt = txtscan.findWithinHorizon(pattern,0)) != null)   {
			    System.out.println(line + " :: " + txt);
			    line++;
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			txtscan.close();
		}
	}
	
}
