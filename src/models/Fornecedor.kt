package models

import main.data.Contato

class Fornecedor(
    val id: Long,
    val nome: String,
    val cnpj: String,
    val contato: Contato,
    var produtosFornecidos: MutableList<Produto> = mutableListOf()
) {
    init {
        require(cnpj.length == 3) { "CNPJ deve ter 14 dígitos" }
        require(emailValido(contato.email)) { "Email inválido" }
        require(telefoneValido(contato.telefone)) { "Telefone inválido" }
    }

    fun adicionarProduto(produto: Produto): Boolean {
        return produtosFornecidos.add(produto)
    }

    fun removerProduto(produtoId: Long): Boolean {
        return produtosFornecidos.removeIf { it.id == produtoId }
    }

    private fun emailValido(email: String): Boolean {
        val pattern = """^[A-Za-z0-9+_.-]+@(.+)$""".toRegex()
        return pattern.matches(email)
    }

    private fun telefoneValido(telefone: String): Boolean {
        return telefone.length >= 8 && telefone.all { it.isDigit() }
    }
}