<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<!-- Define o título da página que aparece na aba do navegador -->

<link rel="icon" href="imagens/telefone.png">
<!-- Define o ícone que será exibido na aba do navegador -->

<link rel="stylesheet" href="style.css">
<!-- Conecta o arquivo CSS externo para aplicar estilos à página -->

</head>
<!-- Fim da seção de cabeçalho -->

<body>
	<!-- Início da seção de corpo do documento -->

	<h1>Editar contato</h1>
	<!-- Exibe o título principal da página -->

	<form name="frmContato" action="update">
		<!-- Inicia um formulário chamado "frmContato" com uma ação vazia -->

		<table>
			<!-- Inicia uma tabela para organizar os campos do formulário -->

			<tr>
				<!-- Inicia uma linha da tabela -->

				<td><input type="text" name="idcon" id="Caixa3" readonly value="<%out.print(request.getAttribute("idcon")); %>"></td>
				<!-- Adiciona uma célula de tabela com um campo de texto para o nome do contato -->

			</tr>

			<tr>
				<!-- Inicia uma linha da tabela -->

				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome")); %>"></td>
				<!-- Adiciona uma célula de tabela com um campo de texto para o nome do contato -->

			</tr>

			<tr>
				<!-- Inicia outra linha da tabela -->

				<td><input type="text" name="fone" class="Caixa2" value="<%out.print(request.getAttribute("fone")); %>"></td>
				<!-- Adiciona uma célula de tabela com um campo de texto para o telefone do contato -->

			</tr>

			<tr>
				<!-- Inicia outra linha da tabela -->

				<td><input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email")); %>"></td>
				<!-- Adiciona uma célula de tabela com um campo de texto para o e-mail do contato -->

			</tr>

		</table>
		<!-- Fim da tabela -->

		<input type="button" value="Salvar" class="botao1" onclick="validar()">
		<!-- Adiciona um botão que, quando clicado, não executa nenhuma ação definida (não tem atributo "onclick") -->

	</form>
	<!-- Fim do formulário -->
	<script src="scripts/validador.js"></script>

</body>
<!-- Fim da seção de corpo do documento -->

</html>
<!-- Fim do documento HTML -->