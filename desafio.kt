enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(var nome: String) {
    val formacoes = mutableListOf<Formacao>()

    fun desmatricular(formacao: Formacao) {
        formacao.inscritos.remove(this)
        formacoes.remove(formacao)
    }
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        usuario.formacoes.add(this)
    }
}

fun main() {
    val usuario1 = Usuario("João")
    val usuario2 = Usuario("Maria")

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 90)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos em Kotlin", 120)
    val conteudo3 = ConteudoEducacional("Kotlin Avançado", 150)

    val formacao1 = Formacao("Formação Kotlin", listOf(conteudo1, conteudo2))
    val formacao2 = Formacao("Formação Avançada Kotlin", listOf(conteudo2, conteudo3))

    formacao1.matricular(usuario1)
    formacao1.matricular(usuario2)
    formacao2.matricular(usuario1)

    println("Inscritos na Formação Kotlin: ${formacao1.inscritos.map { it.nome }}")
    println("Inscritos na Formação Avançada Kotlin: ${formacao2.inscritos.map { it.nome }}")

    usuario1.desmatricular(formacao1)

    println("Inscritos na Formação Kotlin após desmatrícula do usuário 1: ${formacao1.inscritos.map { it.nome }}")
}