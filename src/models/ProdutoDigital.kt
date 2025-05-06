package models

class ProdutoDigital(
    id: Long,
    nome: String,
    preco: Double,
    quantidadeEstoque: Int,
    val tamanhoArquivo: Long,
    val tipoMidia: String
) : Produto(id, nome, preco, quantidadeEstoque) {

    override fun calcularValorTotal(): Double {
        return preco * quantidadeEstoque * (1 + tamanhoArquivo / 1000000.0)
    }
}