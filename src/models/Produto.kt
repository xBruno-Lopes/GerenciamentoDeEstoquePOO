package models

import interfaces.Calculavel
import interfaces.Estoqueavel

abstract class Produto(
    val id: Long,
    val nome: String,
    protected var _preco: Double,
    protected var _quantidadeEstoque: Int
) : Estoqueavel, Calculavel {

    var preco: Double
        get() = _preco
        protected set(value) {
            require(value >= 0) { "Preço não pode ser negativo" }
            _preco = value
        }

    var quantidadeEstoque: Int
        get() = _quantidadeEstoque
        protected set(value) {
            require(value >= 0) { "Quantidade em estoque não pode ser negativa" }
            _quantidadeEstoque = value
        }

    override fun diminuirEstoque(quantidade: Int): Boolean {
        require(quantidade > 0) { "Quantidade deve ser positiva" }
        if (_quantidadeEstoque < quantidade) return false
        _quantidadeEstoque -= quantidade
        return true
    }

    override fun aumentarEstoque(quantidade: Int) {
        require(quantidade > 0) { "Quantidade deve ser positiva" }
        _quantidadeEstoque += quantidade
    }

    abstract override fun calcularValorTotal(): Double
}