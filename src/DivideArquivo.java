import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Classe para divisao de arquivo.
 * 
 * Divide em arquivos menores parametrizado pela quantidade de linhas.
 * 
 * Java 8
 * @author felipe.nmatos
 *
 */

public class DivideArquivo {

	private static final String DIRETORIO_ARQUIVO_ENTRADA = "C:\\Desenvolvimento\\SIPLD\\LeArquivoLog\\";
	private static final String NOME_ARQUIVO_ENTRADA = "sipld-prd.log.2018-05-23.txt";
	private static final String DIRETORIO_ARQUIVO_SAIDA = "C:\\Desenvolvimento\\SIPLD\\LeArquivoLog\\";
	private static final String NOME_ARQUIVO_SAIDA = "sipld-prd.log.2018-05-23_";
	private static final long QUANTIDADE_LINHA_DIVISAO = 200000;

	public static void main(String[] args) throws IOException {
	    try (
	        MyWriter writer = new MyWriter(QUANTIDADE_LINHA_DIVISAO);
	        Stream<String> lines = Files.lines(Paths.get(DIRETORIO_ARQUIVO_ENTRADA + NOME_ARQUIVO_ENTRADA), StandardCharsets.ISO_8859_1);
	    ) {
	        lines.map(l -> /* Transforma aqui */ l).forEach(writer::write);
	    }
	}

	private static class MyWriter implements AutoCloseable {

	    private long count = 0, currentFile = 1, maxLines = QUANTIDADE_LINHA_DIVISAO;
	    private BufferedWriter bw = null;

	    public MyWriter(long maxLines) {
	        this.maxLines = maxLines;
	    }

	    public void write(String line) {
	        try {
	            if (count % maxLines == 0) {
	                close();
	                bw = Files.newBufferedWriter(Paths.get(DIRETORIO_ARQUIVO_SAIDA + NOME_ARQUIVO_SAIDA + currentFile++ + ".txt"));
	            }
	            bw.write(line);
	            bw.newLine();
	            count++;
	        } catch (IOException e) {
	            throw new UncheckedIOException(e);
	        }
	    }

	    @Override
	    public void close() throws IOException {
	        if (bw != null) bw.close();
	    }
	}
}
