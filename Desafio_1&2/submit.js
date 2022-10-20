
const dadosFormualario = document.getElementById("form")

let nomeInput = document.getElementById('name');
let emailInput = document.getElementById('email');
let cpfInput = document.getElementById('cpf');
let escolaridadeSelect = document.getElementById('escolaridade');
let telefoneInput = document.getElementById('telefone');
let enderecoInput = document.getElementById('endereço');
let gêneroSelect = document.getElementById('Gênero');
let data = document.getElementById('data');

let quantidadeDeFormulariosNaTabela = 0;
let quantidadeDeFormulariosEnviados = 0;
let vetorFormulario = [];
let eventListenerExcluir = 0;
let eventListenerEditar = 0;

dadosFormualario.addEventListener("submit", function(e) {
    e.preventDefault()

    //Criando um vetor de objeto para receber os dados do formulário
    let formData = new FormData(form)
    vetorFormulario[quantidadeDeFormulariosEnviados] = {
        nome : formData.get('name'), //Aqui pega o name e não o id
        email: formData.get('email'),
        cpf: formData.get('cpf'),
        endereço: formData.get('endereço'),
        telefone: formData.get('telefone'),
        escolaridade: formData.get('escolaridade'),
        data: formData.get('data'),
        gênero: formData.get('Gênero')
    }

    //Objeto dentro do vetorFormulario[quantidadeDeFormulariosEnviados] para armazenar os contatos seguintes
    vetorFormulario[quantidadeDeFormulariosEnviados].contatos = {
        nome1 : formData.get('contato-nome1'),
        telefone1 : formData.get('contato-telefone1'),
        email1 : formData.get('contato-email1')
        
        
    }

    vetorFormulario[quantidadeDeFormulariosEnviados].quantidadeDeContatos = {
        quantContatos : quantidadeDeContatos
    }

    //console.log(vetorFormulario[quantidadeDeFormulariosEnviados].quantidadeDeContatos.quantContatos)

    //Armazenando os contatos
    for( let i=1; i <= quantidadeDeContatos; i++)
    {
        vetorFormulario[quantidadeDeFormulariosEnviados].contatos[`nome${i}`] = formData.get(`contato-nome${quantidadeDeContatos}`)
        vetorFormulario[quantidadeDeFormulariosEnviados].contatos[`telefone${i}`] = formData.get(`contato-telefone${quantidadeDeContatos}`)
        vetorFormulario[quantidadeDeFormulariosEnviados].contatos[`email${quantidadeDeContatos}`] = formData.get(`contato-email${quantidadeDeContatos}`)
    }
      


    
    console.log(vetorFormulario[quantidadeDeFormulariosEnviados]);

    quantidadeDeFormulariosEnviados++;

    
    //Resetando o formulário
    form.reset();
    if (quantidadeDeContatos > 1) {
      for (let i = 2; i <= quantidadeDeContatos; i++) {
        let removerContato = document.getElementById(`contato-${i}`);
        removerContato.remove();
      }
      quantidadeDeContatos = 1;
    }

    //adicionando alguns dos dados informados na tabela
    adicionandoNaTabela();

    //remover e editar dados da tabela
    let botaoExcluir = document.querySelectorAll('.gridAcaoSubExcluir');
    let botaoEditar = document.querySelectorAll('.gridAcaoSubEditar');
    let botaoExcluirLength = botaoExcluir.length;
    eventListenerEditar++;
    eventListenerExcluir++;

    //Removendo dados da tabela
    const funçãoRemover = function (e) {
      eventListenerExcluir--;
      let i = Number(e.target.id)
      console.log(i);
      let remover = document.querySelectorAll(`.referencia${i}`);
      console.log(remover);
      remover[0].remove();
      remover[1].remove();
      remover[2].remove();
      remover[3].remove();

      vetorFormulario.splice(i - 1, 1);
      quantidadeDeFormulariosNaTabela--;
      quantidadeDeFormulariosEnviados--;

      //Corrigindo as classes
      let itensTabela = document.querySelectorAll('.itemGrid');
      for (let w = i * 4 - 4, k = 1; w < itensTabela.length; w++, k++) {
        let classes = itensTabela[w].className;
        let numeroReferencia = classes.match(/(\d+)/)[0];
        itensTabela[w].classList.remove(`referencia${numeroReferencia}`);
        itensTabela[w].classList.add(`referencia${numeroReferencia - 1}`);
        if (k === 4) {
          k = 0;
          itensTabela[w].innerHTML = `
          <button class="gridAcaoSubEditar" id="${numeroReferencia - 1}" >Editar</button>
          <button class="gridAcaoSubExcluir" id="${numeroReferencia - 1}" >Excluir</button><br><br>
          `;
        }
      }

      recolocarEventListeners(i);
    };

    //Editando dados da tabela
    const funçãoEditar = function (e) {
      eventListenerEditar--;
      let i = Number(e.target.id);
      console.log(i);
      let remover = document.querySelectorAll(`.referencia${i}`);
      console.log(remover);
      remover[0].remove();
      remover[1].remove();
      remover[2].remove();
      remover[3].remove();

      nomeInput.value = vetorFormulario[i - 1].nome;
      emailInput.value = vetorFormulario[i - 1].email;
      cpfInput.value = vetorFormulario[i - 1].cpf;
      escolaridadeSelect.value = vetorFormulario[i - 1].escolaridade;
      enderecoInput.value = vetorFormulario[i - 1].endereço;
      telefoneInput.value = vetorFormulario[i - 1].telefone;
      gêneroSelect.value = vetorFormulario[i - 1].gênero;
      data.value = vetorFormulario[i - 1].data;
      
      let j = vetorFormulario[i - 1].quantidadeDeContatos.quantContatos; //Aqui vai pegar a quantidade de contatos
      for (let k = 1; k < j; k++) {
        adicionarContatoBtn.click();
      }
      for (let p = 1; p <= j; p++) {

        if(p==1){
          let contatoNome1 = document.getElementById("contato-nome1");
          let contatoTelefone1 = document.getElementById("contato-telefone1");
          let contatoEmail1 = document.getElementById("contato-email1");
          
          contatoNome1.value = vetorFormulario[i - 1].contatos.nome1;
          contatoTelefone1.value = vetorFormulario[i - 1].contatos.telefone1;
          contatoEmail1.value = vetorFormulario[i - 1].contatos.email1;
        }
        else{
          let contatoNome = document.getElementById(`nome${p}`);
          let contatoTelefone = document.getElementById(`telefone${p}`);
          let contatoEmail = document.getElementById(`email${p}`);

          contatoNome.value = vetorFormulario[i - 1].contatos[`nome${p}`];
          contatoTelefone.value = vetorFormulario[i - 1].contatos[`telefone${p}`];
          contatoEmail.value = vetorFormulario[i - 1].contatos[`email${p}`];
        }
        
        
      }

      vetorFormulario.splice(i - 1, 1);
      quantidadeDeFormulariosEnviados--;
      quantidadeDeFormulariosNaTabela--;

      //Corrigindo as classes
      let itensTabela = document.querySelectorAll('.itemGrid');
      for (let w = i * 4 - 4, k = 1; w < itensTabela.length; w++, k++) {
        let classes = itensTabela[w].className;
        let numeroReferencia = classes.match(/(\d+)/)[0];
        itensTabela[w].classList.remove(`referencia${numeroReferencia}`);
        itensTabela[w].classList.add(`referencia${numeroReferencia - 1}`);
        if (k === 4) {
          k = 0;
          itensTabela[w].innerHTML = `
          <button class="gridAcaoSubEditar" id="${numeroReferencia - 1}" >Editar</button>
          <button class="gridAcaoSubExcluir" id="${numeroReferencia - 1}" >Excluir</button><br><br>
          `;
        }
      }

      recolocarEventListeners(i);
    };

    function recolocarEventListeners(i) {
        botaoExcluir = document.querySelectorAll('.gridAcaoSubExcluir');
        botaoEditar = document.querySelectorAll('.gridAcaoSubEditar');

        for (let a = i - 1; a < botaoExcluir.length; a++) {
            botaoExcluir[a].addEventListener('click', funçãoRemover);
            botaoEditar[a].addEventListener('click', funçãoEditar);
        }
    }

    botaoExcluir[botaoExcluir.length - 1].addEventListener('click', funçãoRemover);
    botaoEditar[botaoEditar.length - 1].addEventListener('click', funçãoEditar);

    
});

//Função para adicionar os dados na tabela
function adicionandoNaTabela()
{
    quantidadeDeFormulariosNaTabela++;

    let nomeParaAdicionar = document.createElement('div');
    let emailParaAdicionar = document.createElement('div');
    let escolaridadeParaAdicionar = document.createElement('div');
    let botoesParaAdicionar = document.createElement('div');
    
    nomeParaAdicionar.textContent = vetorFormulario[quantidadeDeFormulariosEnviados-1].nome;

    nomeParaAdicionar.setAttribute("class",`itemGrid referencia${quantidadeDeFormulariosEnviados}`);

    emailParaAdicionar.textContent = vetorFormulario[quantidadeDeFormulariosEnviados-1].email;

    emailParaAdicionar.setAttribute("class", `itemGrid referencia${quantidadeDeFormulariosEnviados} `);

    escolaridadeParaAdicionar.textContent = vetorFormulario[quantidadeDeFormulariosEnviados-1].escolaridade;

    escolaridadeParaAdicionar.setAttribute("class",`itemGrid referencia${quantidadeDeFormulariosEnviados}`);

    botoesParaAdicionar.setAttribute("class",`itemGrid referencia${quantidadeDeFormulariosEnviados}`);

    botoesParaAdicionar.innerHTML = `
        <button class="gridAcaoSubEditar" id="${quantidadeDeFormulariosNaTabela}">Editar</button>
        <button class="gridAcaoSubExcluir" id="${quantidadeDeFormulariosNaTabela}">Excluir</button><br><br>
    `;

    let divParaInserirNome = document.querySelector('.gridRefAdicionar');
    let divParaInserirEmail = document.querySelector('.gridRefAdicionar');
    let divParaInserirEscolaridade = document.querySelector('.gridRefAdicionar');
    let divParaInserirBotoes = document.querySelector('.gridRefAdicionar');

    divParaInserirNome.before(nomeParaAdicionar);
    divParaInserirEmail.before(emailParaAdicionar);
    divParaInserirEscolaridade.before(escolaridadeParaAdicionar);
    divParaInserirBotoes.before(botoesParaAdicionar);
    
    
}