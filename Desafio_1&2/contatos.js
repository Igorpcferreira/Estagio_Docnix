

//ADICIONAR CONTATOS
let quantidadeDeContatos = 1;
const adicionarContatoBtn = document.getElementById("add-contato");
const contato1 = document.getElementById("contato-1");

//Função para criar os contatos
function criarContato() {
   quantidadeDeContatos++;
   let contatoAserAdicionado = document.createElement("div");
   contatoAserAdicionado.setAttribute("id", `contato-${quantidadeDeContatos}`);
   const node = `<p id="${quantidadeDeContatos}°contato" class="nth-contato"><strong>Contato ${quantidadeDeContatos}:</p></strong>
  <div class="contato-nome-container flexbox">
    <label for="contato-nome" ><strong>Nome:</strong></label>
    <input type="text" name="contato-nome${quantidadeDeContatos}" id="nome${quantidadeDeContatos}" required placeholder="Insira o nome do contato" />
  </div>

  <div class="contato-telefone-container flexbox">
  <label for="contato-telefone" ><strong>Telefone:</strong></label>
    <input type="tel" name="contato-telefone${quantidadeDeContatos}" id="telefone${quantidadeDeContatos}" required placeholder="(xx)xxxxx-xxxx"/>
  </div>

  <div class="contato-email-container flexbox">
    <label for="contato-email" ><strong>E-mail:</strong></label>
    
    <input type="email" name="contato-email${quantidadeDeContatos}" id="email${quantidadeDeContatos}" required placeholder="Insira o email do contato" />
  </div>`;
   contatoAserAdicionado.innerHTML = node;

   return contatoAserAdicionado;
}

adicionarContatoBtn.addEventListener("click", function () {
   document.getElementById("div-referencia-pra-add-contatos").before(criarContato());
});

//removerContatos
const removerContatoBtn = document.getElementById("remove-contato");

function removerContato() {
   if (quantidadeDeContatos > 1) {
      const contatoAserRemovido = document.getElementById(
         `contato-${quantidadeDeContatos}`
      );
      contatoAserRemovido.parentNode.removeChild(contatoAserRemovido);
      quantidadeDeContatos--;
   }
}
removerContatoBtn.addEventListener("click", function () {
   removerContato();
})
