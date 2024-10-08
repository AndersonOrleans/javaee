package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })

public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();

	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();
		// System.out.println(action);

		if (action.equals("/main")) {
			/* Se o conteudo da variável action for igual ao conteudo de /main: */
			contatos(request, response);

		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		}

		else if (action.equals("/select")) {
			listarContato(request, response);
		}

		else if (action.equals("/update")) {
			editarContato(request, response);
		}

		else if (action.equals("/delete")) {
			removerContato(request, response);
		}

		else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		}

		else {
			response.sendRedirect("idex.html");
		}

		// Teste de conexão:
		dao.testeConexao();
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos:
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp"); */

		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();

		/*
		 * // Teste de recebimento da lista: for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getFone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

		// Encaminhar a lista ao documento agenda.jsp:
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo contato:
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de recebimento dos dados do formulário:
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));

		// Setar as variáveis JavaBeans:
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Invocar o método inserirContato passando o objeto contato
		dao.inserirContatos(contato);

		// Redirecionar para o documento agenda.jsp:
		response.sendRedirect("main");
	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar contato:
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado:
		// String idcon = request.getParameter("idcon");
		// System.out.println(idcon);

		// Setar a variável JavaBeans:
		contato.setIdcon(request.getParameter("idcon"));

		// Executar o método selecionarContato (DAO):
		dao.selecionarContato(contato);

		// Setar os atributos do formumário com o conteúdo JavaBeans:
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar so documento editar.jasp:
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

		/*
		 * // Teste de recebimento: System.out.println(contato.getIdcon());
		 * System.out.println(contato.getNome()); System.out.println(contato.getFone());
		 * System.out.println(contato.getEmail());
		 */
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de recebimento:
		/*
		 * System.out.println(request.getParameter("idcon"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// Setar as variáveis JavaBeans:
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Execultar alterar contato:
		dao.alterarContato(contato);

		// Redirecionar para o documento agenda.jsp (atualizando as alterações):
		response.sendRedirect("main");
	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover contato:
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String idcon = request.getParameter("idcon");
		// System.out.println(idcon);

		// Setar a variável idcon a JavaBeans:
		contato.setIdcon(request.getParameter("idcon"));

		// Executar o método deletarContato (DAO) passando o objeto contato como
		// parametro:
		dao.deletarContato(contato);

		// Redirecionar para o documento agenda.jsp (atualizando as alterações):
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatório em PDF:
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();

		try {
			// Tipo de conteúdo:
			response.reset();
			response.setContentType("Application/pdf");

			// Nome do documento
			response.addHeader("Content-Disposition", "inline filename" + "contatos.pdf");

			// Criar o documento:
			PdfWriter.getInstance(documento, response.getOutputStream());

			// Abrir o documento do PDF:
			documento.open();
			documento.add(new Paragraph("Lista de contatos: "));
			documento.add(new Paragraph(" "));

			// Criar uma tabela:
			PdfPTable tabela = new PdfPTable(3); // Temos que importar.

			// Cabeçalho:
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome")); // Temos que importar.
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			// Popular a tabela com os contatos:
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}

			documento.add(tabela);
			documento.close();

		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}


