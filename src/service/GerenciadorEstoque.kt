package service

import models.Produto
import models.Fornecedor

class GerenciadorEstoque {
    private val produtos = mutableListOf<Produto>()
    private val fornecedores = mutableMapOf<Long, Fornecedor>()

    fun adicionarProduto(produto: Produto) {
        produtos.add(produto)
    }

    fun listarValoresTotais() {
        produtos.forEach { produto ->
            val valorTotal = produto.calcularValorTotal()
            println("Produto: ${produto.nome}")
            println("Valor Total: R$ $valorTotal")
            println("---")
        }
    }

    fun processarVenda(produtoId: Long, quantidade: Int): Boolean {
        val produto = produtos.find { it.id == produtoId } ?: return false
        return if (produto.diminuirEstoque(quantidade)) {
            println("Venda processada com sucesso")
            true
        } else {
            println("Estoque insuficiente")
            false
        }
    }

    fun adicionarFornecedor(fornecedor: Fornecedor) {
        fornecedores[fornecedor.id] = fornecedor
    }

    fun associarProdutoAFornecedor(produtoId: Long, fornecedorId: Long): Boolean {
        val produto = produtos.find { it.id == produtoId } ?: return false
        val fornecedor = fornecedores[fornecedorId] ?: return false
        return fornecedor.adicionarProduto(produto)
    }

    fun listarProdutosPorFornecedor(fornecedorId: Long): List<Produto>? {
        return fornecedores[fornecedorId]?.produtosFornecidos
    }

    fun getFornecedores (): MutableMap<Long, Fornecedor> {
        return fornecedores
    }
}