package models

class ProdutoFisico(
    id: Long,
    nome: String,
    preco: Double,
    quantidadeEstoque: Int,
    val peso: Double,
    val dimensao: Dimensao
) : Produto(id, nome, preco, quantidadeEstoque) {

    override fun calcularValorTotal(): Double {
        val volume = dimensao.largura * dimensao.altura * dimensao.profundidade
        return preco * quantidadeEstoque * (1 + volume * 0.01 + peso * 0.05)
    }
}