package ui

import models.ProdutoDigital
import models.ProdutoFisico
import models.Fornecedor
import models.Dimensao
import main.data.Endereco
import main.data.Contato
import service.GerenciadorEstoque
import java.util.Scanner

class MenuGerenciador {
    private val gerenciador = GerenciadorEstoque()
    private val scanner = Scanner(System.`in`)

    fun mostrarMenu() {
        while (true) {
            mostrarMenuPrincipal()
            when (scanner.nextLine()) {
                "1" -> mostrarMenuProdutos()
                "2" -> mostrarMenuFornecedores()
                "3" -> listarTodosProdutos()
                "4" -> listarTodosFornecedores()
                "5" -> processarVenda()
                "0" -> {
                    println("Sistema encerrado!")
                    return
                }
                else -> println("Opção inválida!")
            }
        }
    }

    private fun mostrarMenuPrincipal() {
        println("\n=== Sistema de Gerenciamento de Estoque ===")
        println("1. Gerenciar Produtos")
        println("2. Gerenciar Fornecedores")
        println("3. Listar Todos os Produtos")
        println("4. Listar Todos os Fornecedores")
        println("5. Processar Venda")
        println("0. Sair")
        print("Escolha uma opção: ")
    }

    private fun mostrarMenuProdutos() {
        println("\n=== Gerenciamento de Produtos ===")
        println("1. Cadastrar Produto Físico")
        println("2. Cadastrar Produto Digital")
        println("3. Voltar")
        print("Escolha uma opção: ")

        when (scanner.nextLine()) {
            "1" -> cadastrarProdutoFisico()
            "2" -> cadastrarProdutoDigital()
            "3" -> return
            else -> println("Opção inválida!")
        }
    }

    private fun cadastrarProdutoFisico() {
        println("\n=== Cadastro de Produto Físico ===")
        println("Digite o ID do produto:")
        val id = scanner.nextLine().toLong()
        println("Digite o nome do produto:")
        val nome = scanner.nextLine()
        println("Digite o preço do produto:")
        val preco = scanner.nextLine().toDouble()
        println("Digite a quantidade em estoque:")
        val quantidade = scanner.nextLine().toInt()
        println("Digite o peso do produto (kg):")
        val peso = scanner.nextLine().toDouble()
        println("Digite a largura do produto (cm):")
        val largura = scanner.nextLine().toDouble()
        println("Digite a altura do produto (cm):")
        val altura = scanner.nextLine().toDouble()
        println("Digite a profundidade do produto (cm):")
        val profundidade = scanner.nextLine().toDouble()

        val produto = ProdutoFisico(
            id = id,
            nome = nome,
            preco = preco,
            quantidadeEstoque = quantidade,
            peso = peso,
            dimensao = Dimensao(largura, altura, profundidade)
        )

        gerenciador.adicionarProduto(produto)
        println("Produto cadastrado com sucesso!")
    }

    private fun cadastrarProdutoDigital() {
        println("\n=== Cadastro de Produto Digital ===")
        println("Digite o ID do produto:")
        val id = scanner.nextLine().toLong()
        println("Digite o nome do produto:")
        val nome = scanner.nextLine()
        println("Digite o preço do produto:")
        val preco = scanner.nextLine().toDouble()
        println("Digite a quantidade em estoque:")
        val quantidade = scanner.nextLine().toInt()
        println("Digite o tamanho do arquivo (bytes):")
        val tamanhoArquivo = scanner.nextLine().toLong()
        println("Digite o tipo de mídia (PDF, MP3, etc):")
        val tipoMidia = scanner.nextLine()

        val produto = ProdutoDigital(
            id = id,
            nome = nome,
            preco = preco,
            quantidadeEstoque = quantidade,
            tamanhoArquivo = tamanhoArquivo,
            tipoMidia = tipoMidia
        )

        gerenciador.adicionarProduto(produto)
        println("Produto cadastrado com sucesso!")
    }

    private fun mostrarMenuFornecedores() {
        println("\n=== Gerenciamento de Fornecedores ===")
        println("1. Cadastrar Fornecedor")
        println("2. Associar Produto a Fornecedor")
        println("3. Voltar")
        print("Escolha uma opção: ")

        when (scanner.nextLine()) {
            "1" -> cadastrarFornecedor()
            "2" -> associarProdutoFornecedor()
            "3" -> return
            else -> println("Opção inválida!")
        }
    }

    private fun cadastrarFornecedor() {
        println("\n=== Cadastro de Fornecedor ===")
        println("Digite o ID do fornecedor:")
        val id = scanner.nextLine().toLong()
        println("Digite o nome do fornecedor:")
        val nome = scanner.nextLine()
        println("Digite o CNPJ do fornecedor:")
        val cnpj = scanner.nextLine()
        println("Digite o telefone do fornecedor:")
        val telefone = scanner.nextLine()
        println("Digite o email do fornecedor:")
        val email = scanner.nextLine()
        println("Digite o logradouro:")
        val logradouro = scanner.nextLine()
        println("Digite o número:")
        val numero = scanner.nextLine().toInt()
        println("Digite a cidade:")
        val cidade = scanner.nextLine()
        println("Digite o estado:")
        val estado = scanner.nextLine()

        val endereco = Endereco(logradouro, numero, cidade, estado)
        val contato = Contato(telefone, email, endereco)
        val fornecedor = Fornecedor(id, nome, cnpj, contato)

        gerenciador.adicionarFornecedor(fornecedor)
        println("Fornecedor cadastrado com sucesso!")
    }

    private fun associarProdutoFornecedor() {
        println("\n=== Associar Produto a Fornecedor ===")
        println("Digite o ID do produto:")
        val produtoId = scanner.nextLine().toLong()
        println("Digite o ID do fornecedor:")
        val fornecedorId = scanner.nextLine().toLong()

        if (gerenciador.associarProdutoAFornecedor(produtoId, fornecedorId)) {
            println("Produto associado ao fornecedor com sucesso!")
        } else {
            println("Erro na associação!")
        }
    }

    private fun listarTodosProdutos() {
        println("\n=== Lista de Produtos ===")
        gerenciador.listarValoresTotais()
    }

    private fun listarTodosFornecedores() {
        println("\n=== Lista de Fornecedores ===")
        gerenciador.getFornecedores().forEach { (_, fornecedor) ->
            println("\nID: ${fornecedor.id}")
            println("Nome: ${fornecedor.nome}")
            println("CNPJ: ${fornecedor.cnpj}")
            println("Produtos fornecidos: ${fornecedor.produtosFornecidos.size}")
        }
    }

    private fun processarVenda() {
        println("\n=== Processar Venda ===")
        println("Digite o ID do produto:")
        val produtoId = scanner.nextLine().toLong()
        println("Digite a quantidade:")
        val quantidade = scanner.nextLine().toInt()

        if (gerenciador.processarVenda(produtoId, quantidade)) {
            println("Venda processada com sucesso!")
        } else {
            println("Erro na venda!")
        }
    }
}