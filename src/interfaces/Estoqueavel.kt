package interfaces

interface Estoqueavel {
    fun diminuirEstoque(quantidade: Int): Boolean
    fun aumentarEstoque(quantidade: Int)
}