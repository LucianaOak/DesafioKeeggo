package br.com.keeggo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileAlreadyExistsException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import br.com.keeggo.core.Driver;
import io.cucumber.java.Scenario;

public class WordUtils {

	
	public static XWPFDocument document;
	private static int fontSizeTitulo = 16;
	
	private static String fontFamilyPasso = "Calibri";
	private static int fontSizePasso = 16;
	private static int fontSizeTabela = 14;
	
	private static int qtErros;
	private static int numPassoAtual = 1;
	
	private static XWPFTable tableDados;
	private static String horaInicio;
	
	public static int getNumPassoAtual() {
		return numPassoAtual;
	}
	
	public static void setNumPassoAtual(int _numPassoAtual) {
		numPassoAtual = _numPassoAtual;
	}
	
	public static void adicionaTituloCenario(Scenario cenario, String projeto, String parceiro) throws Throwable{
		String[] data = addHora().split(" ");
		horaInicio = data[1];
		document = new XWPFDocument();
		horizontalPage();
		
		
		cabecalhoTabela(projeto);
		addTable(cenario, projeto, parceiro);
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER );
		
	}
	
	public static void horizontalPage() {
		CTBody body = document.getDocument().getBody();
		if(!body.isSetSectPr()) {
			body.addNewSectPr();
		}
		
		CTSectPr section = body.getSectPr();
		if(!section.isSetPgSz()) {
			section.addNewPgSz();
		}
		
		CTPageSz pageSize = section.getPgSz();
		pageSize.setOrient(STPageOrientation.LANDSCAPE);
		//A4 = 595x842 / multiply 20 since BigInteger represents 1/20 Point
		pageSize.setW(BigInteger.valueOf(16840));
		pageSize.setH(BigInteger.valueOf(11900));
	}
	
	public static void setSingleLineSpacing(XWPFParagraph para) {
		CTPPr ppr = para.getCTP().getPPr();
		if (ppr == null)
			ppr = para.getCTP().addNewPPr();
		CTSpacing spacing = ppr.isSetSpacing() ? ppr.getSpacing() : ppr.addNewSpacing();
		spacing.setAfter(BigInteger.valueOf(0));
		spacing.setBefore(BigInteger.valueOf(0));
		spacing.setLineRule(STLineSpacingRule.AUTO);
		spacing.setLine(BigInteger.valueOf(240));				
	}
	
	public static void cabecalhoTabela(String projeto) throws IOException, Throwable {
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
		
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
		
		paragraph = header.getParagraphArray(0);
		if (paragraph == null)
			paragraph = header.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		
		run = paragraph.createRun();
		
		addTableImagemCabecalho(run, header, paragraph, projeto);
	}
	
	public static void addTableImagemCabecalho(XWPFRun run, XWPFHeader header, XWPFParagraph paragraph, String Projeto)
	throws IOException, Throwable {
		
		XmlCursor cursor = paragraph.getCTP().newCursor();
		XWPFTable table = header.insertNewTbl(cursor);
		XWPFTableRow row = table.getRow(0);
		if (row == null)
			row = table.createRow();
		int twipsPerInch = 1500;
		
		for (int i = 0; i < 3; i++) {
			XWPFTableCell cell = row.getCell(i);
			if (cell == null)
				cell = row.createCell();
			CTTblWidth tblWidth = cell.getCTTc().addNewTcPr().addNewTcW();
			if(i == 0) {
				tblWidth.setW(BigInteger.valueOf(3 * twipsPerInch));
			}else if (i == 1) {
				tblWidth.setW(BigInteger.valueOf(5 * twipsPerInch));
			}else {
				tblWidth.setW(BigInteger.valueOf(1* twipsPerInch));
			}
			
			tblWidth.setType(STTblWidth.DXA);
			paragraph = cell.getParagraphs().get(0);
			
			run = paragraph.createRun();
			if (i == 0) {
				//addImagem(run, header, "imgs/santander.png");
			}else if (i ==1) {
				//run.setText("Testes automatizados - projeto" + projeto);
				run.setFontSize(fontSizeTitulo);
				cell.setVerticalAlignment(XWPFVertAlign.CENTER);
			}else {
				addImagem(run, header, "imgs/keeggo.png");
			}
			
		}
		
	}

	public static XWPFRun addTextoCelula(XWPFTable table, String texto, boolean negrito, int tamanho, int linha,
			int posicao) {
		XWPFRun run;
		table.removeBorders();
		XWPFTableRow tableRow = table.getRow(linha);
		if(tableRow == null) {
			tableRow = table.createRow();
		}
		XWPFTableCell celula = tableRow.getCell(posicao);
		if (celula == null) {
			celula = tableRow.addNewTableCell();
    	}
		
		celula.removeParagraph(0);
		
		CTTblWidth tblWidth = celula.getCTTc().addNewTcPr().addNewTcW();
		
		tblWidth.setW(BigInteger.valueOf(tamanho));
		
		run = celula.addParagraph().createRun();
		run.setText(texto);
		run.setFontSize(fontSizeTabela);
		run.setBold(negrito);
		return run;
	}
	
	@SuppressWarnings("deprecation")
	public static void addTable(Scenario cenario, String projeto, String parceiro) throws Throwable {
		addImage("imgs\\santander.png", 95, 35);
		XWPFRun runParag = addParagrafo("", 16);
		runParag = addParagrafo("Evidencia de teste", 20);
		runParag.setBold(true);
		runParag.getParagraph().setAlignment(ParagraphAlignment.CENTER);
		
		addParagrafo("Teste", fontSizeTabela);
		
		XWPFTable table = document.createTable();
		
		addTextoCelula(table, "Caso de teste", true, 2500, 0, 0);
		addTextoCelula(table,cenario.getName(), false, 10500, 0, 1);
		//addTextoCelula(table, "Resultado Esperado", true, 3500, 1, 0);
		//addTextoCelula(table, "Sucesso", false, 8500, 1, 1);
		table.setBottomBorder(XWPFBorderType.SINGLE, 2, 0, "000000");
		
		
		addParagrafo("Dados de Execução", fontSizeTabela);
		
		
		
		tableDados = document.createTable();
		
		addTextoCelula(tableDados, "Data do Teste", true, 3500, 0, 0);
		addTextoCelula(tableDados, addHora(), false, 9500, 0, 1);
		addTextoCelula(tableDados, "Tempo Decorrido:", true, 3500, 1, 0);
		
		addTextoCelula(tableDados, "Status Final da Execução", true, 3500, 2, 0);
		
		
		
		
		addParagrafo("Condições de Teste", fontSizeTabela);
		table = document.createTable();
		addTextoCelula(table, "Usuario Executor:", true, 4500, 0, 0);
		addTextoCelula(table, System.getProperty("user.name"), false, 8500, 0, 1);
		addTextoCelula(table, "Versão Framework de automação:", true, 4500, 1, 0);
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader("pom.xml"));
		
		
		
		addTextoCelula(table, model.getVersion(), false, 8500, 1, 1);
		table.setBottomBorder(XWPFBorderType.SINGLE, 2, 0,"000000");
	
	}
	
	public static void addImagem(XWPFRun run, XWPFHeader header, String imgFile)
			throws Throwable, FileAlreadyExistsException, IOException {
		
		XWPFPicture picture = run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile,
				Units.toEMU(95), Units.toEMU(35));
		
		String blipID = "";
		for (XWPFPictureData pictureData : header.getAllPackagePictures()) {
			blipID = header.getRelationId(pictureData);
		}
		picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
	}
	
	public static void adicionaTituloPassoExecutado(String tituloPasso) {
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.LEFT);
		
		XWPFRun titleRun = title.createRun();
		titleRun.setText(tituloPasso);
		titleRun.setBold(true);
		titleRun.setFontFamily(fontFamilyPasso);
		titleRun.setFontSize(fontSizePasso);
	}
	
	public static void adicionaTituloPrint(String tituloPasso) {
		XWPFParagraph title = document.createParagraph();
		title.setPageBreak(true);
		title.setAlignment(ParagraphAlignment.LEFT);
		
		XWPFRun titleRun = title.createRun();
		titleRun.setText(tituloPasso);
		titleRun.setBold(true);
		titleRun.setFontFamily(fontFamilyPasso);
		titleRun.setFontSize(fontSizePasso);
		
		String print = Driver.printScreen();
		addImage(print, 675, 337);
		
	}
	
	public static XWPFRun addParagrafo(String paragrafo, int fontSize) {
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.LEFT);
		
		XWPFRun titleRun = title.createRun();
		titleRun.setText(paragrafo);
		titleRun.setBold(false);
		titleRun.setFontFamily(fontFamilyPasso);
		titleRun.setFontSize(fontSize);
		
		return titleRun;
	}
	
	public static void addImage(String imgPath, int largura, int altura) {
		XWPFParagraph para = document.createParagraph();
		para.setAlignment(ParagraphAlignment.CENTER);
		
		XWPFRun run = para.createRun();
		
		try {
			run.addPicture(new FileInputStream(imgPath), XWPFDocument.PICTURE_TYPE_PNG, imgPath, Units.toEMU(largura), Units.toEMU(altura));
		} catch (Exception e) {
			
		}
	}
	
	public static List<String> getListaJson(String json){
		json = json.replace("\n", "");
		String jsonQuebra = json.replace("{", "\n{\n");
		jsonQuebra = jsonQuebra.replace("}", "\n}\n");
		jsonQuebra = jsonQuebra.replace(",",",\n");
		jsonQuebra = jsonQuebra.replace("\n\n", "\n");
		
		List<String> items = Arrays.asList(jsonQuebra.split("\n"));
		return items;
	}
	
	public static void addErroCampoNaoEncontrado(String campo, String retornoResponse) {
		XWPFParagraph para = document.createParagraph();
		XWPFRun paraRun;
		para.setAlignment(ParagraphAlignment.LEFT);
		
		paraRun = para.createRun();
		paraRun.setBold(true);
		paraRun.setText("Campo não encontrado!");
		
		para = document.createParagraph();
		para.setAlignment(ParagraphAlignment.LEFT);
		
		paraRun = para.createRun();
		paraRun.setText("Campo "+ campo + "não foi encontrado, retorno do response: "+ retornoResponse);
	}
	
	public static String addErroGenerico(String erro) {
		XWPFParagraph para;
		XWPFRun paraRun;
		para = document.createParagraph();
		para.setAlignment(ParagraphAlignment.LEFT);
		
		para = document.createParagraph();
		para.setAlignment(ParagraphAlignment.LEFT);
		
		paraRun = para.createRun();
		paraRun.setBold(true);
		paraRun.setText(erro);
		return erro;
	}
	
	public static String addErroJunit(String campo, String esperado, String encontrado, boolean erroEncontrado) {
		XWPFParagraph para;
		XWPFRun paraRun;
		XWPFTable table;
		if (!erroEncontrado) {
			para = document.createParagraph();
			para.setAlignment(ParagraphAlignment.LEFT);
			
			paraRun = para.createRun();
			paraRun.setBold(true);
			paraRun.setText("Erros Encotrados");
			erroEncontrado = true;
			qtErros = 0;
			
			table = document.createTable();
			
			addTextoCelula(table, "#", true, 500, 0, 0);
			addTextoCelula(table, "Campo", true, 2500, 0, 1);
			
			addTextoCelula(table, "Valor Esperado", true, 2500, 0, 3);
			addTextoCelula(table, "Valor Encontrado", true, 2500, 0, 4);
		}
		qtErros += 1;
		
		table = document.createTable();
		
		addTextoCelula(table, "" + qtErros, false, 500, 0, 0);
		addTextoCelula(table, campo, false, 2500, 0, 1);
		
		addTextoCelula(table, esperado, false, 2500, 0, 3);
		addTextoCelula(table, encontrado, false, 2500, 0, 4);
		
		return "Campo" + campo + "- Valor esperado: <[" + esperado +"]> valor encontrado <[" + encontrado + "]>\n"; 
	}
	
	public static void closeWord(String cenario, boolean erroEncontrado) throws IOException {
		if (!erroEncontrado) {
			XWPFParagraph para;
			XWPFRun paraRun;
			para = document.createParagraph();
			para.setAlignment(ParagraphAlignment.LEFT);
			
			paraRun = para .createRun();
			paraRun.setBold(true);
			paraRun.setText("Teste executado sem erros!");
		}
		
		FileOutputStream out = new FileOutputStream("evidencia/" + cenario.replace("", "").replace("/", "") + ".docx");
		document.write(out);
		out.close();
		document.close();
		
		numPassoAtual = 1;
	}
	
	public static void closeWord(String cenario, boolean erroEncontrado, String funcionalidade) throws Throwable {
		if (!erroEncontrado) {
			XWPFParagraph para;
			XWPFRun paraRun;
			para = document.createParagraph();
			para.setAlignment(ParagraphAlignment.LEFT);
			
			paraRun = para.createRun();
			paraRun.setBold(true);
			paraRun.setText("Teste executado sem erros!");
			addTextoCelula(tableDados, "SUCESSO", false, 9500, 2, 1);
		}else {
			addTextoCelula(tableDados, "FALHA", false, 9500, 2, 1).setColor("FF0000");
			
		}
		addTextoCelula(tableDados, subtraiHora(), false, 9500, 1, 1);
		
		tableDados.setBottomBorder(XWPFBorderType.SINGLE, 2, 0, "000000");
		
		File diretorio = new File("evidencia/" + funcionalidade);
		if (!diretorio.exists()) {
				diretorio.mkdirs();
		}
		
		FileOutputStream out = new FileOutputStream( 
				"evidencia/" + funcionalidade + "/" + cenario.replace(" ", "") + ".docx");
		
		document.write(out);
		out.close();
		document.close();
		
		numPassoAtual = 1;
	}
	
	public static String addHora() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date).toString();
		
	}
		public static String subtraiHora() {
			String horario1 = "";
			String horas[] = horaInicio.split(":");
			
			if(Integer.valueOf(horas[0]) < 10) {
				horario1 += "0";
			}
			horario1 += Integer.valueOf(horas[0])+":";
			if (Integer.valueOf(horas[1]) <10) {
				horario1 += "0";
			}
			horario1 += Integer.valueOf(horas[1])+ ":";
			if (Integer.valueOf(horas[2])<10) {
				horario1 += "0";
			}
			horario1 += Integer.valueOf(horas[2]);
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			
			String horario2 = dateFormat.format(date).toString();
			// tambem tem outros construtores para utilizar numeros
			LocalTime lt1 = LocalTime.parse(horario1);
			LocalTime lt2 = LocalTime.parse(horario2);
			// diferenca
			long emSegundos = lt1.until(lt2, ChronoUnit.SECONDS);
			
			long horast = emSegundos / 3600;
			long minutos = (emSegundos - (horast * 3600)) / 60;
			long segundos = (emSegundos - (horast * 3600)) - (minutos * 6);
			return ""+horast+":"+minutos+":" +segundos;
			
		}	
}
